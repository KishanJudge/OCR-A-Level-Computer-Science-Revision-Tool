package dbHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbSetup {

    //URL linking to the database file
    private static final String URL = "jdbc:sqlite:database/userInfo.db";

    //instantiate all the tables
    public dbSetup() throws SQLException {
        createsUsersTable();
        createQuestionBankTable();
        createAnswerOptionsTable();
    }

    //creates the users table
    private void createsUsersTable() throws SQLException {
        //SQL - adds fields for user_id, username and hashed passcode
        String SQL = """
                CREATE TABLE IF NOT EXISTS users (
                user_id INTEGER PRIMARY KEY,
                username TEXT NOT NULL,
                hashed_passcode TEXT NOT NULL
                )
                """;

        //try to execute the SQL, if it fails print stack trace & error message
        try (Connection connection = DriverManager.getConnection(URL);
        Statement statement = connection.createStatement()) {
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    //creates the question bank table
    private void createQuestionBankTable()  throws SQLException {
        //SQL - creates fields for question_id, topic, the question itself, and feedback.
        String SQL = """
                CREATE TABLE IF NOT EXISTS question_bank (
                question_id INTEGER PRIMARY KEY,
                question_topic TEXT NOT NULL,
                question TEXT NOT NULL,
                correct_answer_index,
                feedback TEXT NOT NULL
                )
                """;

        //try to execute the SQL, if it fails print stack trace & error message
        try (Connection connection = DriverManager.getConnection(URL); 
        Statement statement = connection.createStatement() ){
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    //create the table to store the answer options
    private void createAnswerOptionsTable() throws SQLException {
        //SQL - create fields for question_id (foreign key), the answer option, and its relative index
        String SQL = """
                CREATE TABLE IF NOT EXISTS answer_options (
                question_id INTEGER,
                answer_option_index INTEGER,
                answer_option TEXT NOT NULL,
                FOREIGN KEY (question_id) REFERENCES question_bank (question_id)
                )
                """;
        //try to execute the SQL, if it fails print stack trace & error message
        try (Connection connection = DriverManager.getConnection(URL);
        Statement statement = connection.createStatement() ){
            statement.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    //private void createPerformanceInformationTable() throws SQLException {}
}

