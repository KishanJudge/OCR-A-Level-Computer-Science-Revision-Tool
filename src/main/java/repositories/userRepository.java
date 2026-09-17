package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class userRepository {

    private static final String URL = "jdbc:sqlite:database/userInfo.db";

    public static void addNewUser(
        String givenUsername, String givenHashedPasscode ) throws SQLException {
        String SQL = """
                INSERT INTO users (username, hashed_passcode, is_active)
                VALUES (?, ?, ?)
                """;

            
        try (Connection connection = DriverManager.getConnection(URL);
        PreparedStatement statement = connection.prepareStatement(SQL)) {
            for (int i = 0; i < 4; i++) {
                statement.setString(1, givenUsername);
                statement.setString(2, givenHashedPasscode);
                statement.setBoolean(3, true);
            }
        }
    }
}
