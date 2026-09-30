package service;
import java.sql.SQLException;

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

    public returnMessage checkGivenPasscode(String givenTypedPasscode) {
        /*
        passcode rules:
            - min chars: 8
            - max chars: 64
            - must contain nums, letters, and special chars
        */

        returnMessage returnMessage = new returnMessage();
        validCheck = 0;

        if (givenTypedPasscode.length() < 8 || givenTypedPasscode.length() > 64) {
            returnMessage.setMessage("passcode must be between 8 and 64 chars");
            returnMessage.setResult(false);
            return returnMessage;
        }
        for (int i = 0; i < 3; i++) {
            for (char ch : givenTypedPasscode.toCharArray()) {
            if (String.valueOf(ch).isDigit()) {
                validCheck ++;
                break;
            }
        }
        }

        returnMessage.setMessage("given passcode is valid");
        returnMessage.setResult(true);
        return returnMessage;
    }



    public static void hashPasscode(String givenPasscode) {

    }
}
