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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private ListView<Student> studentlistview;

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
        ButtonType clickedButton = dialog.showAndWait().get();

        if(clickedButton == ButtonType.APPLY) {
            String firstName = controller.getFirstName().toLowerCase();
            String lastName = controller.getLastName().toLowerCase();

            Student student = UserList.getInstance().findStudentByName(firstName, lastName);

            if(student == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Student not found");
                alert.setTitle("Student not found");
                alert.setContentText("Please check your spelling and try again");
                alert.showAndWait();
            } else if (((Advisor) currentUser).getStudents().contains(student)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Student already exists in your list");
                alert.setContentText("Please make sure the student you want to add is not already in your list");
                alert.setTitle("Student already exists");
                alert.showAndWait();
            } else {
                ((Advisor) currentUser).getStudents().add(student);
                refreshStudentList();
            }
        }
    }

    @FXML
    void removebuttonclicked(ActionEvent event) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("removestudentpopup.fxml"));
        dialog.setDialogPane(fxmlLoader.load());
        RemoveStudentPopupController controller = fxmlLoader.getController();
        dialog.setTitle("Remove Student");
        ButtonType clickedButton = dialog.showAndWait().get();

        if(clickedButton == ButtonType.APPLY) {
            String firstName = controller.getFirstName().toLowerCase();
            String lastName = controller.getLastName().toLowerCase();

            Student student = UserList.getInstance().findStudentByName(firstName, lastName);

            if(student == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Student not found");
                alert.setTitle("Student not found");
                alert.setContentText("Please check your spelling and try again");
                alert.showAndWait();
            } else if (((Advisor) currentUser).getStudents().contains(student)) {
                ((Advisor) currentUser).getStudents().remove(student);
                refreshStudentList();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Student found, but does not exist in your list");
                alert.setContentText("Please make sure the student you want to remove is in your list");
                alert.setTitle("Student does not exist");
                alert.showAndWait();
            }
                
        }
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
    void viewbuttonclicked(ActionEvent event) throws IOException {
        Student targetStudent = studentlistview.getSelectionModel().getSelectedItem();
        if(targetStudent != null) {
            FXMLLoader loader = new FXMLLoader(msjn.App.class.getResource("advisorviewsemesterplan.fxml"));
            Parent parent = (Parent)loader.load();
            AdvisorViewSemesterPlanController controller = loader.getController();
            controller.setTargetStudent(targetStudent);
            controller.initialize();
            App.setRoot(parent);
        } else {
            System.out.println("Student is null");
        }

    }

    @FXML
    void logoutbuttonclicked(ActionEvent event) throws IOException {
        DegreeFacade.getInstance().logout();
        App.setRoot("newwelcomescreen");
    }

    @FXML
    void initialize() {
        assert studentlistview != null : "fx:id=\"courselistview\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert searchfield != null : "fx:id=\"searchfield\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentApplicationAreaLabel != null : "fx:id=\"studentApplicationAreaLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentGPALabel != null : "fx:id=\"studentGPALabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentHonorsLabel != null : "fx:id=\"studentHonorsLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentMajorLabel != null : "fx:id=\"studentMajorLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentNameLabel != null : "fx:id=\"studentNameLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert studentStartLabel != null : "fx:id=\"studentStartLabel\" was not injected: check your FXML file 'newviewcourses.fxml'.";
        assert viewProfileButton != null : "fx:id=\"viewProfileButton\" was not injected: check your FXML file 'newviewcourses.fxml'.";

        clearStudentDetails();

        currentUser = DegreeFacade.getInstance().getCurrentUser();
        // currentUser = DegreeFacade.getInstance().login("osberto@email.sc.edu","ozzie0zz13");

        if(currentUser != null) {
            refreshStudentList();

            studentlistview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {

                @Override
                public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                    if(newValue == null) {
                        clearStudentDetails();
                    } else {
                        updateStudentPane(newValue);
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

    private void clearStudentDetails() {
        studentNameLabel.setText("No Student Selected");
        studentMajorLabel.setText("");
        studentApplicationAreaLabel.setText("");
        studentStartLabel.setText("");
        studentGPALabel.setText("");
        studentHonorsLabel.setText("");
        viewProfileButton.setVisible(false);
    }

    private void refreshStudentList() {
        if(currentUser != null) {
            ObservableList<Student> students = FXCollections.observableArrayList(((Advisor) currentUser).getStudents());
            studentlistview.setItems(students);
        }
    }

    private void updateStudentPane(Student student) {
        studentNameLabel.setText(student.getFirstName() + " " + student.getLastName());
        studentMajorLabel.setText("Major: " + student.getMajor().getMajorName());
        studentApplicationAreaLabel.setText("Application Area: " + student.getApplicationID().getName());
        studentStartLabel.setText("Start: TBA");
        studentGPALabel.setText("GPA: " + student.getGpa());

        String honors = student.getHonors() ? "Yes" : "No";
        studentHonorsLabel.setText("Honors: " + honors);

        viewProfileButton.setVisible(true);
    }
}
