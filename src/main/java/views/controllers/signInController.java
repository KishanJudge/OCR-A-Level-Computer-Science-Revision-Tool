package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Session;
import models.User;
import models.returnMessage;
import service.userService;
import views.guiManager;

import java.util.List;

public class signInController {


    Session session = new Session();
    private guiManager gui;

    private userService userService = new userService();
    @FXML Button switchButton;
    @FXML Text pageTitle;
    @FXML Button confirmButton;
    @FXML TextField enterUsername;
    @FXML TextField enterPassword;
    @FXML Text errorMessage;
    Boolean pageState = true; // 1 = login, 0 = sign-up

    public void initialise(guiManager gui) {
        this.gui = gui;
    }

    public void switchPage() {
        errorMessage.setText("");
        pageTitle.setText("Sign Up");
        confirmButton.setText("Sign-Up");
        switchButton.setText("Login");
        pageState = false;

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
                errorMessage.setText("error #1a - try again");
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



    }



}
