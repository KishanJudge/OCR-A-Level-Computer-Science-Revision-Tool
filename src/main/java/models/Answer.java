package models;

public class Answer {
    
    private int questionID;
    private int answerOptionIndex;
    private String answerOption;

    public Answer(int givenQuestionID, int givenAnswerOptionIndex, String givenAnswerOption) {
        questionID = givenQuestionID;
        answerOptionIndex = givenAnswerOptionIndex;
        answerOption = givenAnswerOption;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int givenQuestionID) {
        questionID = givenQuestionID;
    }

    public int getAnswerOptionIndex() {
        return answerOptionIndex;
    }

    public void setAnswerOptionIndex(int givenAnswerOptionIndex) {
        answerOptionIndex = givenAnswerOptionIndex;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(String givenAnswerOption) {
        answerOption = givenAnswerOption;
    }

    
}
