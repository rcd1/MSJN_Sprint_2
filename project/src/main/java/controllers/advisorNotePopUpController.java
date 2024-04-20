package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.*;
import msjn.App;

public class advisorNotePopUpController {

    private Student studentToReceiveNote;
    private static advisorNotePopUpController instance;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label errorLbl;

    @FXML
    private Label lblText;

    @FXML
    private TextArea noteText;

    @FXML
    private Button backButton;

    @FXML
    private Button sendButton;

    @FXML
    private TextField titleText;

    @FXML
    void sendNote() {
        if (titleText.getText().trim().isBlank()) {
            errorLbl.setVisible(true);
            titleText.clear();
            return;
        }
        if (noteText.getText().trim().isBlank()) {
            errorLbl.setVisible(true);
            noteText.clear();
            return;
        }
        System.out.println(titleText.getText() + ": " + noteText.getText());
        // studentToReceiveNote.addNote(new Note(titleText.getText(),noteText.getText()));
        errorLbl.setText("Nice Work!");
        errorLbl.setVisible(false);
        return;

    }

    @FXML
    void backToManageStudents() throws IOException {
        App.setRoot("advisorManageStudents");
    }

    @FXML
    void initialize() {
        assert lblText != null : "fx:id=\"lblText\" was not injected: check your FXML file 'advisorNotePopUp.fxml'.";
        assert noteText != null : "fx:id=\"noteText\" was not injected: check your FXML file 'advisorNotePopUp.fxml'.";
        assert sendButton != null
                : "fx:id=\"sendButton\" was not injected: check your FXML file 'advisorNotePopUp.fxml'.";
        assert titleText != null
                : "fx:id=\"titleText\" was not injected: check your FXML file 'advisorNotePopUp.fxml'.";
        instance = this;
    }

    public static void prepSetStudent(Student student) {
        instance.setStudent(student);
    }

    public void setStudent(Student student) {
        studentToReceiveNote = student;
    }

}
