package models;

public class user {

    private int userID;
    private String username;
    private String hashedPasscode;

    public user (int givenuserID, String givenUsername, String givenHashedPasscode) {
        userID = givenuserID;
        username = givenUsername;
        hashedPasscode = givenHashedPasscode;
    }

    public void setUserID(int givenUserID) {
        userID = givenUserID;
    }

    public void setUsername(String givenUsername) {
        username = givenUsername;
    }

    public void setHashedPasscode(String givenHashedPasscode) {
        hashedPasscode = givenHashedPasscode;
    }

    
}