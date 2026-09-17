package dbHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbSetup {

    private static final String URL = "jdbc:sqlite:database/userInfo.db";

    //create all our tables
    public dbSetup() throws SQLException {
        createsUsersTable();
        createQuestionBankTable();
        createAnswerOptionsTable();
    }

    private void createsUsersTable() throws SQLException {
        String SQL = """
                CREATE TABLE IF NOT EXISTS users (
                user_id INTEGER PRIMARY KEY,
                username TEXT NOT NULL,
                hashed_passcode TEXT NOT NULL,
                is_active BOOLEAN NOT NULL DEFAULT FALSE
                )
                """;

        try (Connection connection = DriverManager.getConnection(URL);
        Statement statement = connection.createStatement()) {
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    private void createQuestionBankTable()  throws SQLException {
        String SQL = """
                CREATE TABLE IF NOT EXISTS question_bank (
                question_id INTEGER PRIMARY KEY,
                question_topic TEXT NOT NULL,
                question TEXT NOT NULL,
                answer_options 
                feedback TEXT NOT NULL
                )
                """;

        try (Connection connection = DriverManager.getConnection(URL); 
        Statement statement = connection.createStatement() ){
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    private void createAnswerOptionsTable() throws SQLException {
        String SQL = """
                CREATE TABLE IF NOT EXISTS answer_options (
                question_id INTEGER,
                answer_option_index INTEGER,
                answer_option TEXT NOT NULL
                FOREIGN KEY (question_id) REFERENCES question_bank (question_id)
                )
                """;

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

