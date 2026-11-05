package repositories;

import models.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


public class questionsRepository {
    
    private static final String URL = "jdbc:sqlite:database/userInfo.db";

    //questions are fetched and displayed
    //answers need to be fetched, as well as answeroptions
    //questionID, questionTopic, question, correctAnswerIndex, feedback
    //answers table: questionID, answerOptionIndex, answer



    //we have an array 
    //we set that array to be of question type, of size x, for how many questions we have total
    //we need an equally sized array for answer options
    //we need to fill up the arrays with the questions, and the answers. we can write the questions manually into the database, then randomly retrieve them

    //getRandomQuestion
    
    public Question getRandomQuestion() throws SQLException {

        int numOfQuestions = 0;
        //ensure indexing is correct for our randomNum
        Random random = new Random();
        int randomNum = random.nextInt(numOfQuestions);

        Question fetchedQuestion = null;
        
        String SQL = """
            SELECT * FROM question_bank 
            WHERE question_id = ?
        """;

        try (Connection connection = DriverManager.getConnection(URL);
        PreparedStatement pStatement = connection.prepareStatement(SQL)){
            pStatement.setInt(1, randomNum);
            
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                fetchedQuestion = new Question(
                    rs.getInt("question_id"),
                    rs.getString("question_topic"),
                    rs.getString("question"),
                    rs.getInt("correct_answer_index"),
                    rs.getString("feedback")
                );
            }

            return fetchedQuestion;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
        
    }


}
