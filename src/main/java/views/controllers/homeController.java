package views.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class homeController {

    @FXML
    private Text applicationTitle;

    public void initialize() {
    }

    //conditial
    //if the user is logged in, show buttons
    //if not, show login / register

    //we have a state of being logged in, tied to the user's account
    //this information needs to be retrieved at any point. lets store this in a session model
    //session model will tell us information about the currently logged in user

}

//needs a login and register popup / prompt