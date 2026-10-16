import dbHandling.dbSetup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dbHandling.dbConnection;
import service.userService;

import java.sql.SQLException;
import java.util.Arrays;

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

        /*
        String[] passResults = new String[8];
        passResults[0] = String.valueOf(service.checkGivenPasscode("passcode123£").getResult());
        passResults[1] = String.valueOf(service.checkGivenPasscode("pass123£").getResult());
        passResults[2] = String.valueOf(service.checkGivenPasscode("""
        passcode123£passcode123£passcode123£passcode123£passcode123£pass
        """).getResult());
        passResults[3] = String.valueOf(service.checkGivenPasscode("").getResult());
        passResults[4] = String.valueOf(service.checkGivenPasscode("""
        passcode123£passcode123£passcode123£passcode123£passcode123£pass123£
        """).getResult());
        passResults[5] = String.valueOf(service.checkGivenPasscode("passcode£").getResult());
        passResults[6] = String.valueOf(service.checkGivenPasscode("123£123£1").getResult());
        passResults[7] = String.valueOf(service.checkGivenPasscode("passcode1234").getResult());
        System.out.println(Arrays.toString(passResults));
        */

        String[] results = new String[8];
        results[0] = String.valueOf(service.checkGivenUsername("user123").getResult());
        results[1] = String.valueOf(service.checkGivenUsername("p").getResult());
        results[2] = String.valueOf(service.checkGivenUsername("""
                user123user123user123user123user123user123user123use
                """).getResult());
        results[3] = String.valueOf(service.checkGivenUsername("").getResult());
        results[4] = String.valueOf(service.checkGivenUsername("""
                user123user123user123user123user123user123user123user12
                """).getResult());
        results[5] = String.valueOf(service.checkGivenUsername("user£$").getResult());
        results[6] = String.valueOf(service.checkGivenUsername("user123").getResult());
        results[7] = String.valueOf(service.checkGivenUsername("kishan").getResult());
        System.out.println(Arrays.toString(results));

        /*
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
        */

        //3 - check if the hashed SHA-256 is valid and correctly hashed
    
        //need to confirm whether or not we need to test invalid username entries into the database.
        //surely nothing will happen? maybe we need to test for SQL injection?
        /*
        System.out.println(service.insertUser("keegan", "HASHPLACEHOLDER").getResult());
        System.out.println(service.insertUser("kishan", "HASHPLACEHOLDER").getResult());

        System.out.println("-------------------------------------------------------------");
        */
        

    }


}
