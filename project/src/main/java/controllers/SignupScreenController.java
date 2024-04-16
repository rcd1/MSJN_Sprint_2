package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailfield;

    @FXML
    private Label errorlabel;

    @FXML
    private TextField firstnamefield;

    @FXML
    private TextField lastnamefield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private ChoiceBox<String> usertypebox;

    private String[] userTypes = {"Student", "Adivsor"};

    @FXML
    void loginButtonClicked(MouseEvent event) {
        System.out.println(usertypebox.getValue());
    }

    @FXML
    void initialize() {
        assert emailfield != null : "fx:id=\"emailfield\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert errorlabel != null : "fx:id=\"errorlabel\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert firstnamefield != null : "fx:id=\"firstnamefield\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert lastnamefield != null : "fx:id=\"lastnamefield\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert passwordfield != null : "fx:id=\"passwordfield\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert usertypebox != null : "fx:id=\"usertypebox\" was not injected: check your FXML file 'signupscreen.fxml'.";
        usertypebox.setItems(FXCollections.observableArrayList(userTypes));

    }

}
