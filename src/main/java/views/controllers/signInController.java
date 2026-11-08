package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import models.Session;
import models.User;
import models.returnMessage;
import service.userService;
import views.guiManager;

import java.sql.SQLException;
import java.util.List;

public class signInController {


    private Session session;
    private guiManager gui;


    private userService userService = new userService();
    @FXML Button switchButton;
    @FXML Text pageTitle;
    @FXML Button confirmButton;
    @FXML TextField enterUsername;
    @FXML TextField enterPassword;
    @FXML Text errorMessage;
    @FXML Button lightDarkSwitch;
    @FXML AnchorPane background;
    @FXML ImageView toggle;

    Boolean pageState = true; // true = login, false = sign-up
    Boolean pageColour = true; //true = dark, false = light

    public void initialise(guiManager gui, Session session) {
        this.gui = gui;
        this.session = session;
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
    public void switchPage() {

        errorMessage.setText("");
        enterUsername.clear();
        enterPassword.clear();

        if (pageState) {
            pageTitle.setText("Sign Up");
            confirmButton.setText("Sign-Up");
            switchButton.setText("Login");
            pageState = false;                
        }
        else {
            pageTitle.setText("Log In");
            confirmButton.setText("Login");
            switchButton.setText("Sign-Up");
            pageState = true;
        }


    }

    public void switchSuccessfulSignUp() {
        pageTitle.setText("Log In");
        confirmButton.setText("Login");
        switchButton.setText("Sign-Up");
        pageState = true;
    }

    public void confirmPressed() {
        if (pageState) { logIn(); }
        else { signUp(); }
    }
    
    public void logIn() {

        //validate username

        List<Object> userCheck = userService.checkUser(enterUsername.getText(), enterPassword.getText());

        int userID = -1;
        boolean pass = false;
        User user = null;

        //check if there is a user with the matching credentials
        try {
            userID = (int) userCheck.getFirst();
            if (userID > 0) {
                pass = true;
            }
        } catch (Exception e) {
            errorMessage.setText("username or password is incorrect - try again");
            enterUsername.clear();
            enterPassword.clear();
        }

        //if there is fetch that user's information
        if (pass) {
            try {
                user = (User) userService.getUser(userID).getFirst();
                errorMessage.setText("");
                enterUsername.clear();
                enterPassword.clear();
            } catch (Exception e) {
                errorMessage.setText("error fetching user information");
                enterUsername.clear();
                enterPassword.clear();
            }
            try {
                gui.loadHomePage();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
                System.out.println("error loading home page");
            }
        }
        else {
            errorMessage.setText("username or password is incorrect - try again");
            enterUsername.clear();
            enterPassword.clear();
        }



        session.setUser(user);

        //change scene, and pass the user into the session state
    }

    public void signUp() {


        returnMessage userReturnedM = userService.checkGivenUsername(enterUsername.getText());
        returnMessage passReturnedM = userService.checkGivenPasscode(enterPassword.getText());

        if (!(passReturnedM.getResult())) {
            errorMessage.setText(passReturnedM.getMessage());
        }
        if (!(userReturnedM.getResult())) {
            errorMessage.setText(userReturnedM.getMessage());
        }

        if (passReturnedM.getResult() && userReturnedM.getResult()) {
            try {
                userService.insertUser(enterUsername.getText(), enterPassword.getText());
                errorMessage.setText("Account creation successful, proceed to login");
                switchSuccessfulSignUp();
            } catch (Exception e) {
                errorMessage.setText("error inserting new user");
                enterUsername.clear();
                enterPassword.clear();
            }
        }
    }



}
