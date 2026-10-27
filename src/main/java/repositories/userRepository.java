package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import models.User;

public class userRepository {

    private static final String URL = "jdbc:sqlite:database/userInfo.db";

    public void addNewUser(
        String givenUsername, String givenHashedPasscode ) throws SQLException {
        
        //SQL - insert a new User into the users table
        String SQL = """
                INSERT INTO users (username, hashed_passcode)
                VALUES (?, ?)
                """;

        //connect to database & prepare statement, set all values equal to passed in information
        try (Connection connection = DriverManager.getConnection(URL);
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {
                pStatement.setString(1, givenUsername);
                pStatement.setString(2, givenHashedPasscode);
                //execute SQL statement
                pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public void updateUser(
        String givenUsername, String givenHashedPasscode, int givenUserID) throws SQLException {
        
        //SQL - edit specified User's information with new information
        String SQL = """
                UPDATE users 
                SET (username, hashed_passcode)
                VALUES (?, ?, ?)
                WHERE user_id == ?
                """;
        
        //connect to database & prepare statement
        try (Connection connection = DriverManager.getConnection(URL); 
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {
            pStatement.setString(1, givenUsername);
            pStatement.setString(2, givenHashedPasscode);
            pStatement.setInt(3, givenUserID);
            //execute SQL
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public void deleteUser(int givenUserID) throws SQLException {
        
            //delete sql statement
            String SQL = """
                    DELETE FROM users
                    WHERE user_id == ?
                    """;
        
        //connect to database & prepare statement
        try (Connection connection = DriverManager.getConnection(URL);
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {
            pStatement.setInt(1, givenUserID);

            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public User getUser(
        int givenUserID) throws SQLException {

        User fetchedUser = null;

        String SQL = """
                SELECT * FROM users
                WHERE username = ?
                """;
        
        //connect to database & prepare statement
        try (Connection connection = DriverManager.getConnection(URL); 
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {

            pStatement.setInt(1, givenUserID);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {

                fetchedUser = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("hashed_passcode")
                );
            }

            return fetchedUser;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
    }

    public User getUsersByUsername(String givenUsername) throws SQLException {

        User fetchedUser = null;

        String SQL = """
                SELECT * FROM users
                WHERE username = ?
                """;

        try (Connection connection = DriverManager.getConnection(URL); 
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {
            pStatement.setString(1, givenUsername);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {

                fetchedUser = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("hashed_passcode")
                );
            }

            return fetchedUser;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }

    }

    public int checkUser(String givenUsername, String givenHashedPasscode) throws SQLException {

        int userID = -1;

        String SQL = """
                SELECT user_id 
                FROM users
                WHERE username = ? AND hashed_passcode = ?
                """;

        try (Connection connection = DriverManager.getConnection(URL);
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {
            pStatement.setString(1, givenUsername);
            pStatement.setString(2, givenHashedPasscode);

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                userID = rs.getInt("user_id");
            }

            return userID;
        }
    }
}
