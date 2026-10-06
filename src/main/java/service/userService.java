package service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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

   public void messageReturner(String givenMessage, boolean givenResult) {
    System.out.println(givenResult + ":" + givenMessage);
   }
    private userRepository userRepo = new userRepository();

    public returnMessage checkUsername(String givenUsername) {
        /*
            min chars: 1
            max chars: 53
            illegal chars: £$"%^&*+={}[];:/<>\|`¬
            legal chars: -_()#@'!?.
        */
        returnMessage returnMessage = new returnMessage();
        String[] illegalChars = {"£", "$", "%", "^", "&", "*", "+", "=", "{", "}", "[", "]", ";", ":", "/", "<", ">", "|", "`", "¬"};

        // '\' caused an error: find a fix, for now exclude it from the illegal chars list

        if (givenUsername.length() < 1 || givenUsername.length() > 53) {
            returnMessage.setMessage("username must be between 1 and 50 Characters");
            returnMessage.setResult(false);
            return returnMessage;
        } 
        else {
            for (int i = 0; i < illegalChars.length; i++) {
                if (givenUsername.contains(illegalChars[i])) {
                    returnMessage.setMessage("given username contains invalid characters");
                    returnMessage.setResult(false);
                    return returnMessage;
                }
            }
        }

        returnMessage.setMessage("valid username entered");
        returnMessage.setResult(true);
        return returnMessage;
    }

    public returnMessage insertUser(String givenUsername, String givenHashedPasscode) {
   
        returnMessage returnMessage = new returnMessage();

        try {
            userRepo.addNewUser(givenUsername, givenHashedPasscode);
            returnMessage.setMessage("user successfully added");
            returnMessage.setResult(true);
            return returnMessage;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            returnMessage.setMessage("error adding user");
            returnMessage.setResult(false);
            return returnMessage;
        }

    }

    public returnMessage editUser(int givenUserID, String givenUsername, 
        String givenHashedPasscode, int givenIsActive) {
        
        returnMessage returnMessage = new returnMessage();

        try {
            userRepo.updateUser(givenUsername, givenHashedPasscode, givenIsActive, givenUserID);
            returnMessage.setMessage("user information successfully updated");
            returnMessage.setResult(true);
            return returnMessage;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            returnMessage.setMessage("user update failed");
            returnMessage.setResult(false);
            return returnMessage;
        }


    }
    
    public returnMessage deleteUser(int givenUserID) {

        returnMessage returnMessage = new returnMessage();

        try {
            userRepo.deleteUser(givenUserID);
            returnMessage.setMessage("user successfully deleted");
            returnMessage.setResult(true);
            return returnMessage;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            returnMessage.setMessage("failed to delete user");
            returnMessage.setResult(false);
            return returnMessage;
        }
    }
    
    public user getUser(int givenUserID) {

        try {
            user currentUser = userRepo.getUser(givenUserID);
            messageReturner("successfully retrieved user information", true);
            return currentUser;
        } catch (SQLException e) {
            messageReturner("failed to retrieve user", false);
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

        returnMessage returnMessage = new returnMessage();
        int validCheck = 0;

        //check passcode length
        if (givenTypedPasscode.length() < 8 || givenTypedPasscode.length() > 64) {
            returnMessage.setMessage("passcode must be between 8 and 64 chars");
            returnMessage.setResult(false);
            return returnMessage;
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
            returnMessage.setMessage("password must contain digits, letters, and special characters");
            returnMessage.setResult(false);
            return returnMessage;
        }

        returnMessage.setMessage("given passcode is valid");
        returnMessage.setResult(true);
        return returnMessage;
    }

    //hashing the passcode given by the user
    /*'We need to be aware that the MessageDigest is not thread-safe. 
     Consequently, we should use a new instance for every thread.'
    */

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
