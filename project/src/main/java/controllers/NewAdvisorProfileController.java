package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import model.DegreeFacade;
import msjn.*;
import model.*;
import java.util.ArrayList;

public class NewAdvisorProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backbutton;

    @FXML
    private ListView<Student> studentlistview;

    @FXML
    private Button homebutton;

    @FXML
    private TextField searchfield;

    @FXML
    private Label studentApplicationAreaLabel;

    @FXML
    private Label studentGPALabel;

    @FXML
    private Label studentHonorsLabel;

    @FXML
    private Label studentMajorLabel;

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label studentStartLabel;

    @FXML
    private Button viewProfileButton;

    private ArrayList<Student> advisorStudents;

    private User currentUser;

    @FXML
    void addbuttonclicked(ActionEvent event) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addstudentpopup.fxml"));
        dialog.setDialogPane(fxmlLoader.load());
        AddStudentPopupController controller = fxmlLoader.getController();
        dialog.setTitle("Add Student");
        dialog.showAndWait().get();
    }

    @FXML
    void backbuttonclicked(ActionEvent event) {

    }

    @FXML
    void homebuttonclicked(ActionEvent event) {

    }

    @FXML
    void removebuttonclicked(ActionEvent event) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("removestudentpopup.fxml"));
        dialog.setDialogPane(fxmlLoader.load());
        RemoveStudentPopupController controller = fxmlLoader.getController();
        dialog.setTitle("Remove Student");
        dialog.showAndWait().get();
    }

    @FXML
    void searchstudents(KeyEvent event) {
        ArrayList<Student> searchStudents = new ArrayList<Student>();
        if(searchfield.getText().equals("")) {
            searchStudents = ((Advisor) currentUser).getStudents();
        } else {
            for(Student student : ((Advisor) currentUser).getStudents()) {
                if((student.getFirstName() + " " + student.getLastName()).toLowerCase().contains(searchfield.getText().toLowerCase())) {
                    searchStudents.add(student);
                }
            }
        }

        ObservableList<Student> studentListCells = FXCollections.observableArrayList(searchStudents);
        studentlistview.setItems(studentListCells);
    }

    @FXML
    void viewbuttonclicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert backbutton != null : "fx:id=\"backbutton\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentlistview != null : "fx:id=\"courselistview\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert homebutton != null : "fx:id=\"homebutton\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert searchfield != null : "fx:id=\"searchfield\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentApplicationAreaLabel != null : "fx:id=\"studentApplicationAreaLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentGPALabel != null : "fx:id=\"studentGPALabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentHonorsLabel != null : "fx:id=\"studentHonorsLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentMajorLabel != null : "fx:id=\"studentMajorLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentNameLabel != null : "fx:id=\"studentNameLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentStartLabel != null : "fx:id=\"studentStartLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert viewProfileButton != null : "fx:id=\"viewProfileButton\" was not injected: check your FXML file 'newviewcourses.fxml'.";

        currentUser = DegreeFacade.getInstance().getCurrentUser();
        currentUser = DegreeFacade.getInstance().login("osberto@email.sc.edu","ozzie0zz13");

        if(currentUser != null) {
            ArrayList<Student> students = ((Advisor) currentUser).getStudents();
            ObservableList<Student> courseListCells = FXCollections.observableArrayList(students);
            studentlistview.setItems(courseListCells);

            studentlistview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {

                @Override
                public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                    if(newValue == null) {
                        // Do stuff
                    } else {
                        // Do stuff
                    }
                }
            });

            studentlistview.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
            @Override
            public ListCell<Student> call(ListView<Student> student) {
            return new ListCell<>(){
                @Override
                public void updateItem(Student student, boolean empty) {
                    super.updateItem(student, empty);
                    if(!empty) {
                        if(student != null) {
                            setText(student.getFirstName() + " " + student.getLastName());
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

    }

}
