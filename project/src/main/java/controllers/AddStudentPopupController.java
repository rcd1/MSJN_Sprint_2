package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Button;

public class AddStudentPopupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    void initialize() {

        ((Button) dialogPane.lookupButton(ButtonType.APPLY)).setText("Add");
        assert dialogPane != null : "fx:id=\"dialogPane\" was not injected: check your FXML file 'Untitled'.";
        assert firstNameField != null : "fx:id=\"firstNameField\" was not injected: check your FXML file 'Untitled'.";
        assert lastNameField != null : "fx:id=\"lastNameField\" was not injected: check your FXML file 'Untitled'.";

    }

}
