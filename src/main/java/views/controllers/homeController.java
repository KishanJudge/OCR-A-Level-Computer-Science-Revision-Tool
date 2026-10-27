package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import views.guiManager;

public class homeController {

    private guiManager gui;

    @FXML private Text applicationTitle;

    //quizButton
    //algorithmButton
    //login/signup button

    @FXML private Button quizButton;
    @FXML private Button algorithmButton;
    @FXML private Button login;

    @FXML
    public void initialise(guiManager gui) {
        this.gui = gui;
    }

    //if a User is logged in, they are taken to the home page, if not they are taken to the signup / login page.
    //we need to detect this first before the User even logs in.
    //can we have login be detected from the get go? 
    //need to allow User to click on quiz page or algorithm page and be taken there
    //need to allow User to sign up or login
    

    //we need to test what works with the GUI launch, can we call start only after checking if a User is logged in
    //can we keep a User logged in between sessions, or do they have to login every time.

    //keeping them logged in may be troublesome, so for now we will ensure log in between sessions
    //therefore the first page the User sees should be the login / sign up page.
    //during the design of this page, we will also be starting to design our colour scheme
    //therefore we need to consider the 

    /*
    we want a popup screen for the signup page, that dissapears and appears as needed, same for login.
    
    signup needs to take a username and passcode, it should run checks for username and passcode, return errors and continually 
    prompt the User to input correct information, and if all is correct, continue them to the home page.
    

    */
    
}

//needs a login and register popup / prompt