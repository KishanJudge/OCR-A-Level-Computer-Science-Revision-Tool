package dbHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    //URL that links to database file
    private static final String URL = "jdbc:sqlite:database/userInfo.db";

    //constructor
    public dbConnection() {

        //try to connect, if working print success, if not print error msg & stack trace
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
