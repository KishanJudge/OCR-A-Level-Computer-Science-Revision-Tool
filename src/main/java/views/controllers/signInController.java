package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class signInController {


    @FXML Button signUpSwitch;
    @FXML Text pageTitle;
    @FXML Button confirmButton;
    @FXML TextField enterUsername;
    @FXML TextField enterPassword;
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
    }

    public void signUp() {

    }
    public void initialise() {

    }
}
