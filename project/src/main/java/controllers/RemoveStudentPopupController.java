package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class RemoveStudentPopupController {

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

    private Button applyButton;

    private String returnFirstName, returnLastName;

    @FXML
    void initialize() {
        assert dialogPane != null : "fx:id=\"dialogPane\" was not injected: check your FXML file 'Untitled'.";
        assert firstNameField != null : "fx:id=\"firstNameField\" was not injected: check your FXML file 'Untitled'.";
        assert lastNameField != null : "fx:id=\"lastNameField\" was not injected: check your FXML file 'Untitled'.";

        applyButton = ((Button) dialogPane.lookupButton(ButtonType.APPLY));
        applyButton.setText("Remove");
        applyButton.setDisable(true);

    }

    @FXML
    void keytyped(KeyEvent event) {
        if(!firstNameField.getText().equals("") && !lastNameField.getText().equals("")) {
            applyButton.setDisable(false);
        } else {
            applyButton.setDisable(true);
        }
        returnFirstName = firstNameField.getText();
        returnLastName = lastNameField.getText();
    }

    public String getFirstName() {
        return returnFirstName;
    }

    public String getLastName() {
        return returnLastName;
    }

}