package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import model.*;
import java.util.ArrayList;

public class StudentViewNotes {

    public static StudentViewNotes instance;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label noteDateLabel;

    @FXML
    private TextFlow noteTextFlow;

    @FXML
    private Text noteText;

    @FXML
    private Label noteTitleLabel;

    @FXML
    private ListView<Note> notelistview;

    private User currentUser;

    private Student currentStudent = null;

    private ArrayList<Note> currentStudentNotes;

    private Note currentNote;

    @FXML
    void initialize() {
        assert noteDateLabel != null
                : "fx:id=\"noteDateLabel\" was not injected: check your FXML file 'studentViewNotes.fxml'.";
        assert noteText != null : "fx:id=\"noteText\" was not injected: check your FXML file 'studentViewNotes.fxml'.";
        assert noteTitleLabel != null
                : "fx:id=\"noteTitleLabel\" was not injected: check your FXML file 'studentViewNotes.fxml'.";
        assert notelistview != null
                : "fx:id=\"notelistview\" was not injected: check your FXML file 'studentViewNotes.fxml'.";

        currentUser = DegreeFacade.getInstance().getCurrentUser();
        currentUser = DegreeFacade.getInstance().login("dbeez@email.sc.edu", "ep1c3l1t3");
        currentStudent = (Student) currentUser;
        currentStudentNotes = currentStudent.getNotes();

        ObservableList<Note> noteListCells = FXCollections.observableArrayList(currentStudentNotes);
        notelistview.setItems(noteListCells);

        notelistview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Note>() {

            @Override
            public void changed(ObservableValue<? extends Note> observable, Note oldValue, Note newValue) {
                if (newValue == null) {
                    
                    clearNoteDetails();
                    currentNote = null;
                } else {
                    updateNotePane(newValue);
                    currentNote = newValue;
                }
            }
        });

        notelistview.setCellFactory(new Callback<ListView<Note>, ListCell<Note>>() {
            @Override
            public ListCell<Note> call(ListView<Note> note) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Note note, boolean empty) {
                        super.updateItem(note, empty);
                        if (!empty) {
                            if (note != null) {
                                setText(note.getTitle());
                            }
                        } else {
                            setGraphic(null);
                            setText("");
                        }
                    }
                };
            }
        });

    }

    protected void updateNotePane(Note newValue) {
        noteTitleLabel.setText(newValue.getTitle());
        noteDateLabel.setText(newValue.getDate());
        noteText.setText(newValue.getNote());
    }

    protected void clearNoteDetails() {
        noteTitleLabel.setText("No Note Selected");
        noteDateLabel.setText("To get started, select a note");
        noteText.setText("");
    }

}