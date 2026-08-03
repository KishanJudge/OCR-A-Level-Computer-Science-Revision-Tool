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
    }

    private void createsUsersTable() throws SQLException {
        String SQL = """
                CREATE TABLE IF NOT EXISTS users (
                userid INTEGER PRIMARY KEY,
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
}

