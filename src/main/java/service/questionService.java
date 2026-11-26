package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Question;
import models.returnMessage;
import models.Answer;
import repositories.questionRepository;
import repositories.userRepository;

public class questionService {
    
    private questionRepository questionRepo = new questionRepository();

    public Question getRandomQuestion() {

        try {
            Question fetchedQuestion = questionRepo.getRandomQuestion();
            return fetchedQuestion;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;          
        }
    }

    //need to do error handling wi returnMessage
    public List<Object> fetchQuestionsFromTopic(String givenTopic) {

        List<Object> returnMult = new ArrayList<>();
        
        try {
            returnMult.add(questionRepo.getQuestionsFromTopic(givenTopic));
            returnMult.add(new returnMessage("Questions successfully fetched", true));
            return returnMult;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<Answer> getAnswers(int givenQuestionID) {
        
        try {
            List<Answer> fetchedAnswers = questionRepo.getAnswers(givenQuestionID);
            return fetchedAnswers;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
    }
}
