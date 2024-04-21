package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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

    @FXML
    void keytyped(KeyEvent event) {

    }

    @FXML
    void initialize() {
        assert dialogPane != null : "fx:id=\"dialogPane\" was not injected: check your FXML file 'leavenotepopup.fxml'.";
        assert noteField != null : "fx:id=\"noteField\" was not injected: check your FXML file 'leavenotepopup.fxml'.";

    }

}
