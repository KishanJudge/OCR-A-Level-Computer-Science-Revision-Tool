package models;

public class returnMessage {
    
    String message;
    boolean result;

    private String getMessage() {
        return message;
    }

    public void setMessage(String givenMessage) {
        message = givenMessage;
    }

    private boolean getResult() {
        return result;
    }

    public void setResult(boolean givenResult) {
        result = givenResult;
    }
}
