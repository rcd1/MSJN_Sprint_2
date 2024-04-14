package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;

public class LoginScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailfield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Label errorlabel;

    @FXML
    void loginButtonClicked(MouseEvent event) {
        String username = emailfield.getText();
        String password = passwordfield.getText();

        DegreeFacade degreeFacade = DegreeFacade.getInstance();
        User user = degreeFacade.login(username,password);
        if(user == null) {
            errorlabel.setText("Incorrect username or password");
        } else {
            errorlabel.setText("Success!");
        }
    }

    @FXML
    void initialize() {
        assert emailfield != null : "fx:id=\"emailfield\" was not injected: check your FXML file 'loginscreen.fxml'.";
        assert passwordfield != null : "fx:id=\"passwordfield\" was not injected: check your FXML file 'loginscreen.fxml'.";

    }

}
