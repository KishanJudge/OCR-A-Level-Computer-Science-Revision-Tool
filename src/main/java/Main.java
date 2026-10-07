import dbHandling.dbSetup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dbHandling.dbConnection;
import service.userService;

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
        System.out.println(service.checkGivenPasscode("123£123£1")git.getResult()); //7
        System.out.println(service.checkGivenPasscode("passcode1234").getResult()); //8
    }


}
