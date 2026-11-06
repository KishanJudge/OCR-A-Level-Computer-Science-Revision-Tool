package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
            System.out.println("switched to light");
            scene.getStylesheets().add(getClass().getResource("/lightMode.css").toExternalForm());
        }
        else {
            pageColour = true;
            System.out.println("switched to dark");
            scene.getStylesheets().add(getClass().getResource("/darkMode.css").toExternalForm());
        }



    }
    public void switchPage() {

        if (pageState) {
            errorMessage.setText("");
            pageTitle.setText("Sign Up");
            confirmButton.setText("Sign-Up");
            switchButton.setText("Login");
            pageState = false;                
        }
        else {
            errorMessage.setText("");
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
