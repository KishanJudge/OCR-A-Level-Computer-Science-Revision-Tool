import dbHandling.dbSetup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dbHandling.dbConnection;
import service.userService;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage homeStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        homeStage.setScene(scene);
        homeStage.show();

    }

    public static void main (String[] args) throws SQLException {
        dbConnection dbconnection = new dbConnection();
        dbSetup dbsetup = new dbSetup();
        launch(args);

        userService service = new userService();
        System.out.println(service.checkGivenPasscode("passcode123£").getResult()); //1
        System.out.println(service.checkGivenPasscode("pass123£").getResult()); //2
        System.out.println(service.checkGivenPasscode(""" 
                passcode123£passcode123£passcode123£passcode123£passcode123£pass
                """).getResult()); //3
        System.out.println(service.checkGivenPasscode("").getResult()); //4
        System.out.println(service.checkGivenPasscode("""
                passcode123£passcode123£passcode123£passcode123£passcode123£pass123£
                """).getResult()); //5
        System.out.println(service.checkGivenPasscode("passcode£").getResult()); //6
        System.out.println(service.checkGivenPasscode("123£123£1").getResult()); //7
        System.out.println(service.checkGivenPasscode("passcode1234").getResult()); //8

        System.out.println("-------------------------------------------------------------");

        System.out.println(service.checkGivenUsername("user123").getResult()); //1
        System.out.println(service.checkGivenUsername("p").getResult()); //2
        System.out.println(service.checkGivenUsername("""
                user123user123user123user123user123user123user123use
                """).getResult()); //3
        System.out.println(service.checkGivenUsername("").getResult()); //4
        System.out.println(service.checkGivenUsername("user123user123user123user123user123user123user123user12").getResult()); //5
        System.out.println(service.checkGivenUsername("user£$").getResult()); //6
        System.out.println(service.checkGivenUsername("user123").getResult()); //7
        System.out.println(service.checkGivenUsername("kishan").getResult()); //8

        System.out.println("-------------------------------------------------------------");

        try {
            System.out.println(service.hashPasscode("passcode"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

                try {
            System.out.println(service.hashPasscode("passcode"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } //1

                try {
            System.out.println(service.hashPasscode("kishancode"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

                try {
            System.out.println(service.hashPasscode("codekishan"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } //2


        //3 - check if the hashed SHA-256 is valid and correctly hashed
    
        //need to confirm whether or not we need to test invalid username entries into the database.
        //surely nothing will happen? maybe we need to test for SQL injection
        System.out.println(service.insertUser("keegan", "HASHPLACEHOLDER").getResult());
        System.out.println(service.insertUser("kishan", "HASHPLACEHOLDER").getResult());

        System.out.println("-------------------------------------------------------------");

        

    }


}
