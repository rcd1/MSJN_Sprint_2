package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LeaveNotePopupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private TextArea noteField;

    private String note;

    private Button applyButton;

    @FXML
    void keytyped(KeyEvent event) {
        if(!noteField.getText().equals("")) {
            applyButton.setDisable(false);
        } else {
            applyButton.setDisable(true);
        }
        if(noteField.getText().contains("\n"))
        noteField.setText(noteField.getText().replace("\n",""));
        note = noteField.getText();
    }

    @FXML
    void initialize() {
        assert dialogPane != null : "fx:id=\"dialogPane\" was not injected: check your FXML file 'leavenotepopup.fxml'.";
        assert noteField != null : "fx:id=\"noteField\" was not injected: check your FXML file 'leavenotepopup.fxml'.";

        noteField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER) {
                event.consume();
            }
        });

        applyButton = ((Button) dialogPane.lookupButton(ButtonType.APPLY));
        applyButton.setText("Leave Note");
        applyButton.setDisable(true);

    }

    public String getNote() {
        return note;
    }

}
