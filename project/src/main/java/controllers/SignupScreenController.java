package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.DegreeFacade;
import model.UserType;
import msjn.App;
import model.*;

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
    private ChoiceBox<UserType> usertypebox;

    @FXML
    void loginButtonClicked(MouseEvent event) throws IOException {
        String firstName = firstnamefield.getText();
        String lastName = lastnamefield.getText();
        String email = emailfield.getText();
        String password = passwordfield.getText();
        UserType userType = usertypebox.getValue();

        if (usertypebox.getValue() == null) {
            errorlabel.setText("Please choose a user type");
            return;
        } else if (DegreeFacade.getInstance().userEmailExists(email)) {
            errorlabel.setText("Email already exists");
            return;
        }

        User user = DegreeFacade.getInstance().createAccount(firstName, lastName, email, password, userType);
        if (user == null) {
            errorlabel.setText("There was an error creating an account");
        } else {
            if (user instanceof Student) {
                App.setRoot("newstudetnsemesterplan");
            } else if (user instanceof Advisor) {
                App.setRoot("advisorProfile");
            }
        }
    }

    @FXML
    void initialize() {
        assert emailfield != null : "fx:id=\"emailfield\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert errorlabel != null : "fx:id=\"errorlabel\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert firstnamefield != null
                : "fx:id=\"firstnamefield\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert lastnamefield != null
                : "fx:id=\"lastnamefield\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert passwordfield != null
                : "fx:id=\"passwordfield\" was not injected: check your FXML file 'signupscreen.fxml'.";
        assert usertypebox != null
                : "fx:id=\"usertypebox\" was not injected: check your FXML file 'signupscreen.fxml'.";
        usertypebox.setItems(FXCollections.observableArrayList(UserType.values()));
    }

}
