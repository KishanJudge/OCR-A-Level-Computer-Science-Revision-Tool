package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Session;
import views.controllers.homeController;
import views.controllers.manageAccountController;
import views.controllers.signInController;

public class guiManager extends Application {

    private Session session = new Session();
    private Stage mainStage;

    @Override
    public void start(Stage mainStage) throws Exception {
        this.mainStage = mainStage;
        loadSignInPage(false);
        mainStage.show();

    }


    public void loadSignInPage(boolean pageColour) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signInPage.fxml"));
        Parent root = loader.load();
        signInController controller = loader.getController();
        controller.initialise(this, session, pageColour);
        Scene scene = new Scene(root);
        controller.switchLightDark();
        //scene.getStylesheets().setAll(getClass().getResource("/darkMode.css").toExternalForm());
        mainStage.setScene(scene);
    }

    public void loadHomePage(boolean pageColour) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        Parent root = loader.load();
        homeController controller = loader.getController();
        controller.initialise(this, session, pageColour);
        Scene scene = new Scene(root);
        controller.switchLightDark();
        //scene.getStylesheets().setAll(getClass().getResource("/darkMode.css").toExternalForm());
        mainStage.setScene(scene);

    }

    public void loadManageAccountPage(boolean pageColour, Stage owner) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/manageAccountPage.fxml"));
        Parent root = loader.load();
        manageAccountController controller = loader.getController();
        controller.initialise(this, session, pageColour);
        mainStage.initOwner(owner);
        mainStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        controller.switchLightDark();
        //scene.getStylesheets().setAll(getClass().getResource("/darkMode.css").toExternalForm());
        mainStage.setScene(scene);

    }
}
