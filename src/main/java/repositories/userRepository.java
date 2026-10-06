package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import models.user;

public class userRepository {

    private static final String URL = "jdbc:sqlite:database/userInfo.db";

    public void addNewUser(
        String givenUsername, String givenHashedPasscode ) throws SQLException {
        
        //SQL - insert a new user into the users table
        String SQL = """
                INSERT INTO users (username, hashed_passcode, is_active)
                VALUES (?, ?, ?)
                """;

        //connect to database & prepare statement, set all values equal to passed in information
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

    public void updateUser(
        String givenUsername, String givenHashedPasscode, 
        int givenIsActive, int givenUserID) throws SQLException {
        
        //SQL - edit specified user's information with new information
        String SQL = """
                UPDATE users 
                SET (username, hashed_passcode, is_active)
                VALUES (?, ?, ?)
                WHERE user_id == ?
                """;
        
        //connect to database & prepare statement
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

    public void deleteUser(
        int givenUserID) throws SQLException {
        
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

    public user getUser(
        int givenUserID) throws SQLException {

        user fetchedUser = null;

        String SQL = """
                SELECT * FROM users
                WHERE user_id = ?
                """;
        
        //connect to database & prepare statement
        try (Connection connection = DriverManager.getConnection(URL); 
        PreparedStatement pStatement = connection.prepareStatement(SQL)) {

            pStatement.setInt(1, givenUserID);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {

                fetchedUser = new user(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("hashed_passcode")
                );
            }

            return fetchedUser;
        }
    }
}
