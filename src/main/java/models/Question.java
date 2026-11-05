package models;

public class Question {
    
    private int questionID;
    private String questionTopic;
    private String question;
    private int correctAnswerIndex;
    private String feedback;

    public Question(int givenQuestionID, String givenQuestionTopic, String givenQuestion, int givenCorrectAnswerIndex, String givenFeedback) {
        questionID = givenQuestionID;
        questionTopic = givenQuestionTopic;
        question = givenQuestion;
        correctAnswerIndex = givenCorrectAnswerIndex;
        feedback = givenFeedback;
    }

    public int getQuestionID() {
        return questionID;
    }
    public void setQuestionID(int givenQuestionID) {
        questionID = givenQuestionID;
    }

    public String getQuestionTopic() {
        return questionTopic;
    }
    public void setQuestionTopic(String givenQuestionTopic) {
        questionTopic = givenQuestionTopic;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String givenQuestion) {
        question = givenQuestion;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
    public void setCorrectAnswerIndex(int givenCorrectAnswerIndex) {
        correctAnswerIndex = givenCorrectAnswerIndex;
    }

    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String givenFeedback) {
        feedback = givenFeedback;
    }
}
