package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import views.guiManager;

public class manageAccountController {

    //back to Home button
    //delete account - are you sure? (popup?), ask to type confirm code
    //edit details - are you sure
    //log out - return to log in page, are you sure

    private guiManager gui;

    @FXML Button lightDarkSwitch;
    @FXML ImageView toggle;
    @FXML AnchorPane background;

    boolean pageColour = true;

    public void initialise(guiManager gui) {
        this.gui = gui;
    }

    public void switchLightDark() {

        Scene scene = background.getScene();
        scene.getStylesheets().clear();

        if (pageColour) {
            pageColour = false;
            toggle.setImage(new Image(getClass().getResource("/smoon_black.png").toExternalForm()));
            scene.getStylesheets().setAll(getClass().getResource("/lightMode.css").toExternalForm());

        }
        else {
            pageColour = true;
            toggle.setImage(new Image(getClass().getResource("/smoon_white.png").toExternalForm()));
            scene.getStylesheets().setAll(getClass().getResource("/darkMode.css").toExternalForm());
        }
    }
}
