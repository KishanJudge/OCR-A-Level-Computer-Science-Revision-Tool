package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class userRepository {

    private static final String URL = "jdbc:sqlite:database/userInfo.db";

    public static void addNewUser(
        String givenUsername, String givenHashedPasscode ) throws SQLException {
            //Insert SQL statement
        String SQL = """
                INSERT INTO users (username, hashed_passcode, is_active)
                VALUES (?, ?, ?)
                """;

        //connect to database
        try (Connection connection = DriverManager.getConnection(URL);
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {
                pStatement.setString(1, givenUsername);
                pStatement.setString(2, givenHashedPasscode);
                pStatement.setBoolean(3, true);
                //execute SQL statement
                pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public static void updateUser(
        String givenUsername, String givenHashedPasscode, 
        int givenIsActive, int givenUserID) throws SQLException {
        //Update SQL statement
        String SQL = """
                UPDATE users 
                SET (username, hashed_passcode, is_active)
                VALUES (?, ?, ?)
                WHERE user_id == ?
                """;
        //connect to database
        try (Connection connection = DriverManager.getConnection(URL); 
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {
            pStatement.setString(1, givenUsername);
            pStatement.setString(2, givenHashedPasscode);
            pStatement.setInt(3, givenIsActive);
            pStatement.setInt(4, givenUserID);
            //execute SQL
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public static void deleteUser(
        int givenUserID) throws SQLException {
            //delete sql statement
            String SQL = """
                    DELETE FROM users
                    WHERE user_id == ?
                    """;

        try (Connection connection = DriverManager.getConnection(URL);
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {
            pStatement.setInt(1, givenUserID);

            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public static void getUser(
        int givenUserID) throws SQLException {
        String SQL = """
                SELECT * FROM users
                WHERE user_id = ?
                """;
        
        try (Connection connection = DriverManager.getConnection(URL); 
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {
            pStatement.setInt(1, givenUserID);

            pStatement.executeQuery();
        }
    }
}
