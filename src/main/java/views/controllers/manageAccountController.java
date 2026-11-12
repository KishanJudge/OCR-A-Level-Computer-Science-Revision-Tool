package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.Session;
import views.guiManager;

public class manageAccountController {

    //back to Home button
    //delete account - are you sure? (popup?), ask to type confirm code
    //edit details - are you sure
    //log out - return to log in page, are you sure

    private guiManager gui;
    private Session session;

    @FXML Button lightDarkSwitch;
    @FXML ImageView toggle;
    @FXML AnchorPane background;
    @FXML ImageView back;
    @FXML Button backButton;

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
            toggle.setImage(new Image(getClass().getResource("/smoon_black.png").toExternalForm()));
            back.setImage(new Image(getClass().getResource("/blackBackArrow.png").toExternalForm()));
            scene.getStylesheets().setAll(getClass().getResource("/lightMode.css").toExternalForm());

        }
        else {
            pageColour = true;
            toggle.setImage(new Image(getClass().getResource("/smoon_white.png").toExternalForm()));
            back.setImage(new Image(getClass().getResource("/whiteBackArrow.png").toExternalForm()));
            scene.getStylesheets().setAll(getClass().getResource("/darkMode.css").toExternalForm());
        }
    }

    public void goBack() {
        try {
            gui.loadHomePage(!pageColour);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println("error loading home page");
        }
    }
}
