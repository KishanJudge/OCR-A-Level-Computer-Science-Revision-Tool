package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.returnMessage;
import service.userService;

public class signInController {


    private userService userService = new userService();
    @FXML Button signUpSwitch;
    @FXML Text pageTitle;
    @FXML Button confirmButton;
    @FXML TextField enterUsername;
    @FXML TextField enterPassword;
    @FXML Text errorMessage;
    Boolean pageState = true; // 1 = login, 0 = sign-up

    public void switchPage() {
        pageTitle.setText("Sign Up");
        confirmButton.setText("Sign-Up");
    }

    public void confirmPressed() {
        if (pageState) { logIn(); }
        else { signUp(); }
    }
    public void logIn() {

        //validate username
        enterUsername.getText();
        enterPassword.getText();

        userService.getUserByUsername();


    }

    public void signUp() {

    }
    public void initialise() {

    }

    /*
        returnMessage userReturnedM = userService.checkGivenUsername(enterUsername.getText());
        returnMessage passReturnedM = userService.checkGivenPasscode(enterPassword.getText());
        if (!(userReturnedM.getResult())) {
            errorMessage.setText(userReturnedM.getMessage());
        }
        if (!(passReturnedM.getResult())) {
            errorMessage.setText(passReturnedM.getMessage());
        }
     */
}
