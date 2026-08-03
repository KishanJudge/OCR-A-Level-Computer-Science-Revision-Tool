package dbHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    //db URL
    private static final String URL = "jdbc:sqlite:database/userInfo.db";

    public dbConnection() {

        //try to connect, print success, if not print error msg + stack trace
        try (Connection connection = DriverManager.getConnection(URL)) {
            if (connection != null) {
                System.out.println("dbconnect success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }



}
