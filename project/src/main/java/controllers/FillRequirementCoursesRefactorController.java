package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.*;
import java.util.ArrayList;

public class FillRequirementCoursesRefactorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label courseCorequisitesLabel;

    @FXML
    private Label courseCreditHoursLabel;

    @FXML
    private Label courseDescriptionLabel;

    @FXML
    private Label courseFullNameLabel;

    @FXML
    private Label courseKeywordsLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label coursePrerequisitesLabel;

    @FXML
    private Label courseSemestersLabel;

    @FXML
    private Label courseStudentGradeLabel;

    @FXML
    private ListView<Course> courselistview;

    @FXML
    private Button selectcoursebutton;

    private Keyword searchKeyword;

    @FXML
    void initialize() {
        assert courseCorequisitesLabel != null : "fx:id=\"courseCorequisitesLabel\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert courseCreditHoursLabel != null : "fx:id=\"courseCreditHoursLabel\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert courseDescriptionLabel != null : "fx:id=\"courseDescriptionLabel\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert courseFullNameLabel != null : "fx:id=\"courseFullNameLabel\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert courseKeywordsLabel != null : "fx:id=\"courseKeywordsLabel\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert courseNameLabel != null : "fx:id=\"courseNameLabel\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert coursePrerequisitesLabel != null : "fx:id=\"coursePrerequisitesLabel\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert courseSemestersLabel != null : "fx:id=\"courseSemestersLabel\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert courseStudentGradeLabel != null : "fx:id=\"courseStudentGradeLabel\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert courselistview != null : "fx:id=\"courselistview\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";
        assert selectcoursebutton != null : "fx:id=\"selectcoursebutton\" was not injected: check your FXML file 'fillrequirementcoursesrefactor.fxml'.";

        searchKeyword = Keyword.LAE;

        ArrayList<Course> courses = CourseList.getInstance().findCourses(searchKeyword.toString());
        ObservableList<Course> courseListCells = FXCollections.observableArrayList(courses);
        courselistview.setItems(courseListCells);

        courselistview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {

            @Override
            public void changed(ObservableValue<? extends Course> observable, Course oldValue, Course newValue) {
                if(newValue == null) {
                    selectcoursebutton.setDisable(true);
                } else {
                    selectcoursebutton.setDisable(false);
                }
            }
        });

        courselistview.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {
            @Override
            public ListCell<Course> call(ListView<Course> course) {
            return new ListCell<>(){
                @Override
                public void updateItem(Course course, boolean empty) {
                    super.updateItem(course, empty);
                    if(course != null) {
                        setText(course.getShortName());
                    }
                }
            };
            }
        });
    }
}
