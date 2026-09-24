package service;
import models.returnMessage;
public class userService {

    /*make an account
        - set username
        - set password
        - hash the password
        - insert user into db
    */

    public static returnMessage checkUsername(String givenUsername) {
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

    public static void checkGivenPasscode() {
        /*
        passcode rules:
            - min chars: 8
            - max chars: 64

        */
    }

    public static void insertUser(String username, String hashedPasscode) {

    }

    public static void hashPasscode(String givenPasscode) {

    }
}
