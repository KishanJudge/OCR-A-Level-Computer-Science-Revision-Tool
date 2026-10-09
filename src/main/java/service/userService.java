package service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.user;
import models.returnMessage;
import repositories.userRepository;
public class userService {

    /*make an account
        - set username
        - set password
        - hash the password
        - insert user into db
    */


    private userRepository userRepo = new userRepository();

    public returnMessage checkGivenUsername(String givenUsername) {
        /*
            min chars: 1
            max chars: 53
            illegal chars: £$"%^&*+={}[];:/<>\|`¬
            legal chars: -_()#@'!?.
        */


        String[] illegalChars = {"£", "$", "%", "^", "&", "*", "+", "=", "{", "}", "[", "]", ";", ":", "/", "<", ">", "|", "`", "¬"};

        // '\' caused an error: find a fix, for now exclude it from the illegal chars list

        
        try {

            if ((userRepo.getUsersByUsername(givenUsername) == null)) {
                return (new returnMessage("duplicate username exists", false));
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return (new returnMessage("error fetching users", false));
        }
        

        if (givenUsername.length() < 1 || givenUsername.length() > 53) {
            return (new returnMessage("username must be between 1 and 53 characters", false));
        } 
        else {
            for (int i = 0; i < illegalChars.length; i++) {
                if (givenUsername.contains(illegalChars[i])) {
                    return (new returnMessage("given username contains invalid characters", false));
                }
            }
        }

        return new returnMessage("valid username entered", true);
    }

    public returnMessage insertUser(String givenUsername, String givenHashedPasscode) {


        try {
            userRepo.addNewUser(givenUsername, givenHashedPasscode);
            return (new returnMessage("user successfuly added", true));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return (new returnMessage("error adding user", false));
        }

    }

    public returnMessage editUser(int givenUserID, String givenUsername, 
        String givenHashedPasscode) {
        

        try {
            userRepo.updateUser(givenUsername, givenHashedPasscode, givenUserID);
            return (new returnMessage("user information successfully updated", true));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return (new returnMessage("user information update failed", false));
        }


    }
    
    public returnMessage deleteUser(int givenUserID) {

        try {
            userRepo.deleteUser(givenUserID);
            return (new returnMessage("user successfully deleted", true));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return (new returnMessage("error deleting user from table", false));
        }
    }
    
    public List<Object> getUser(int givenUserID) {

        List<Object> returnMult = new ArrayList<>();
        try {
            user fetchedUser = userRepo.getUser(givenUserID);
            returnMult.add(fetchedUser);
            returnMult.add(new returnMessage("successfully retrieved user information", true));
            return returnMult;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
    } 


    public returnMessage checkGivenPasscode(String givenTypedPasscode) {
        /*
        passcode rules:
            - min chars: 8
            - max chars: 64
            - must contain nums, letters, and special chars
        */

        int validCheck = 0;

        //check passcode length
        if (givenTypedPasscode.length() < 8 || givenTypedPasscode.length() > 64) {
            return (new returnMessage("password must be between 8 and 64 chars", false));
        }

        //check contains special chars, letters, and digits. there is a probably a way to make this more efficient
        // figure that out when you have more time to think
        for (char ch1 : givenTypedPasscode.toCharArray()) {
            if (Character.isDigit(ch1)) {
                validCheck ++;
                break;
            }
        }
        for (char ch2 : givenTypedPasscode.toCharArray()) {
            if (Character.isAlphabetic(ch2)) {
                validCheck++;
                break;
            }
        }
        for (char ch3 : givenTypedPasscode.toCharArray()) {
            if (!(Character.isLetterOrDigit(ch3))) {
                validCheck++;
                break;
            }
        }
        
        if (validCheck != 3) {
            return (new returnMessage("password must contain digits, letters, and special characters", false));
        }

        return (new returnMessage("given password is valid", true));
    }

    //hashing the passcode given by the user
    /*'We need to be aware that the MessageDigest is not thread-safe. 
     Consequently, we should use a new instance for every thread.'
    */

    //hashes the user's inputted passcode after is has passed all validation checks
    //must throw noSuchAlgorithmException because 
    public static String hashPasscode(String givenPasscode) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digestedPasscode = md.digest(givenPasscode.getBytes(StandardCharsets.UTF_8));
        
        StringBuilder hexPasscode = new StringBuilder(2 * digestedPasscode.length);
        for (int i = 0; i < digestedPasscode.length; i++) {
            String hex = Integer.toHexString(0xff & digestedPasscode[i]);
            if (hex.length() == 1) {
                hexPasscode.append('0');
            }
            hexPasscode.append('0');
        }

        return hexPasscode.toString();
    }
}
