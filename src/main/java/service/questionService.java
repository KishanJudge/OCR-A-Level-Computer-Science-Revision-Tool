package service;

import java.sql.SQLException;
import java.util.List;

import models.Question;
import models.Answer;
import repositories.questionRepository;

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
