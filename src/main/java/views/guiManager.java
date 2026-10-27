package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.controllers.homeController;
import views.controllers.signInController;

public class guiManager extends Application {

    private Stage mainStage;

    @Override
    public void start(Stage mainStage) throws Exception {
        this.mainStage = mainStage;
        loadSignInPage();
        mainStage.show();

    }

    public void loadSignInPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signInPage.fxml"));
        Parent root = loader.load();
        signInController controller = loader.getController();
        controller.initialise(this);
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
    }

    public void loadHomePage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        Parent root = loader.load();
        homeController controller = loader.getController();
        controller.initialise(this);
        Scene scene = new Scene(root);
        mainStage.setScene(scene);

    }
}
