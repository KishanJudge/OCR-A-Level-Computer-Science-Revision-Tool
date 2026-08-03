import dbHandling.dbSetup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dbHandling.dbConnection;

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
    }


}
