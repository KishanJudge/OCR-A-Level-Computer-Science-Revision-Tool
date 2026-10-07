package models;

public class returnMessage {
    
    private String message;
    private boolean result;

    public returnMessage(String givenMessage, boolean givenResult) {
        message = givenMessage;
        result = givenResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String givenMessage) {
        message = givenMessage;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean givenResult) {
        result = givenResult;
    }
}
