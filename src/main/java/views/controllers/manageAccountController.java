package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Session;
import models.User;
import models.returnMessage;
import service.userService;
import views.guiManager;

import java.sql.SQLException;
import java.util.List;

public class manageAccountController {

    //back to Home button
    //delete account - are you sure? (popup?), ask to type confirm code
    //edit details - are you sure
    //log out - return to log in page, are you sure

    private userService userService = new userService();
    private guiManager gui;
    private Session session;

    @FXML Button lightDarkSwitch;
    @FXML ImageView toggle;
    @FXML AnchorPane background;
    @FXML ImageView back;
    @FXML Button backButton;
    @FXML TextField enterNewUsername;
    @FXML TextField enterNewPassword;
    @FXML Button confirmButton;


    boolean pageColour;

    public void initialise(guiManager gui, Session session, boolean pageColour) {
        this.gui = gui;
        this.session = session;
        this.pageColour = pageColour;
    }

    public void switchLightDark() {

        Scene scene = background.getScene();
        scene.getStylesheets().clear();

        if (pageColour) {
            pageColour = false;
            back.setImage(new Image(getClass().getResource("/blackBackArrow.png").toExternalForm()));
            scene.getStylesheets().setAll(getClass().getResource("/lightMode.css").toExternalForm());

        }
        else {
            pageColour = true;
            back.setImage(new Image(getClass().getResource("/whiteBackArrow.png").toExternalForm()));
            scene.getStylesheets().setAll(getClass().getResource("/darkMode.css").toExternalForm());
        }
    }

    public void editUser() {

        //check that a user of the same name doesn't already exist
        returnMessage userCheck = userService.checkGivenUsername(enterNewUsername.getText());
        returnMessage passCheck = userService.checkGivenPasscode(enterNewPassword.getText());
        boolean pass = false;

        if (userCheck.getResult() && passCheck.getResult()) { pass = true; }
        else {  } //display error message


        if (pass) {
            try {
                userService.editUser(session.getUser().getUserID(), enterNewUsername.getText(), enterNewPassword.getText());
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
                System.out.println("error changing user details");
            }
        }

    }

    public void goBack() {
        Stage popupStage = (Stage) background.getScene().getWindow();
        popupStage.close();
    }
}
