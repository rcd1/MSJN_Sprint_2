package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class LeaveNotePopupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private TextField noteField;

    private String note;

    private Button applyButton;

    @FXML
    void keytyped(KeyEvent event) {
        if(!noteField.getText().equals("")) {
            applyButton.setDisable(false);
        } else {
            applyButton.setDisable(true);
        }
        note = noteField.getText();
    }

    @FXML
    void initialize() {
        assert dialogPane != null : "fx:id=\"dialogPane\" was not injected: check your FXML file 'leavenotepopup.fxml'.";
        assert noteField != null : "fx:id=\"noteField\" was not injected: check your FXML file 'leavenotepopup.fxml'.";

        applyButton = ((Button) dialogPane.lookupButton(ButtonType.APPLY));
        applyButton.setText("Leave Note");
        applyButton.setDisable(true);

    }

    public String getNote() {
        return note;
    }

}
