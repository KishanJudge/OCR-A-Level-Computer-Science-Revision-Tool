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

    
}
