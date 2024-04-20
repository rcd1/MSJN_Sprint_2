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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

    @FXML
    private TextField searchfield;

    private Keyword searchKeyword = Keyword.LAE;

    private User currentUser;

    private ArrayList<Course> allAvailableCourses;

    private Course currentCourse;

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

        currentUser = DegreeFacade.getInstance().getCurrentUser();
        currentUser = DegreeFacade.getInstance().login("bwest@email.sc.edu","ma3w&zh3r3");

        allAvailableCourses = CourseList.getInstance().findCourses(searchKeyword.toString());
        if(currentUser != null) {
            allAvailableCourses = ((Student)currentUser).filterAvailableCourses(allAvailableCourses);
        }
        ObservableList<Course> courseListCells = FXCollections.observableArrayList(allAvailableCourses);
        courselistview.setItems(courseListCells);

        courselistview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {

            @Override
            public void changed(ObservableValue<? extends Course> observable, Course oldValue, Course newValue) {
                if(newValue == null) {
                    selectcoursebutton.setDisable(true);
                    clearCourseDetails();
                    currentCourse = null;
                } else {
                    selectcoursebutton.setDisable(false);
                    updateCoursePane(newValue);
                    currentCourse = newValue;
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
                    if(!empty) {
                        if(course != null) {
                            setText(course.getShortName());
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

    private void clearCourseDetails() {
        courseNameLabel.setText("No Course Selected");
        courseFullNameLabel.setText("To get started, select a course");
        courseDescriptionLabel.setText("");
        courseCreditHoursLabel.setText("");
        coursePrerequisitesLabel.setText("");
        courseCorequisitesLabel.setText("");
        courseStudentGradeLabel.setText("");
        courseKeywordsLabel.setText("");
        courseSemestersLabel.setText("");
    }

    private void updateCoursePane(Course course) {
        courseNameLabel.setText(course.getShortName());
        courseFullNameLabel.setText(course.getName());
        courseDescriptionLabel.setText(course.getDescription());

        courseCreditHoursLabel.setText("Credit Hours: " + course.getHours());

        ArrayList<RequirementSet> prerequisites = course.getPrerequisites();
        if(prerequisites == null || prerequisites.isEmpty()) {
            coursePrerequisitesLabel.setText("Prerequisites: None");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Prerequisites: ");
            for(int i = 0; i < prerequisites.size(); i++) {
                sb.append(prerequisites.get(i).toString());
                if(i != prerequisites.size() - 1) {
                    sb.append(", ");
                }
            }
            coursePrerequisitesLabel.setText(sb.toString());
        }

        ArrayList<RequirementSet> corequisites = course.getCorequisites();
        if(corequisites == null || corequisites.isEmpty()) {
            courseCorequisitesLabel.setText("Corequisites: None");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Corequisites: ");
            for(int i = 0; i < corequisites.size(); i++) {
                sb.append(corequisites.get(i).toString());
                if(i != corequisites.size() - 1) {
                    sb.append(", ");
                }
            }
            courseCorequisitesLabel.setText(sb.toString());
        }

        if(currentUser != null) {
            Grade grade = ((Student)currentUser).satisfiesPrerequisite(course);
            if(grade != null) {
                courseStudentGradeLabel.setText("Student Grade: " + grade.getLetter());
            } else {
                courseStudentGradeLabel.setText("Student Grade: None");
            }
        } else {
            courseStudentGradeLabel.setText("Student Grade: None");
        }

        ArrayList<Keyword> keywords = course.getKeywords();
        if(keywords == null || keywords.isEmpty()) {
            courseKeywordsLabel.setText("Keywords: None");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Keywords: ");
            for(int i = 0; i < keywords.size(); i++) {
                sb.append(keywords.get(i));
                if(i != keywords.size() - 1) {
                    sb.append(", ");
                }
            }
            courseKeywordsLabel.setText(sb.toString());
        }

        ArrayList<SemesterOffered> semestersOffered =  course.getSemestersOffered();
        if(semestersOffered == null || semestersOffered.isEmpty()) {
            courseSemestersLabel.setText("Semesters: None");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Semesters: ");
            for(int i = 0; i < semestersOffered.size(); i++) {
                sb.append(semestersOffered.get(i).getName());
                if(i != semestersOffered.size() - 1) {
                    sb.append(", ");
                }
            }
            courseSemestersLabel.setText(sb.toString());
        }
    }

    @FXML
    private void searchcourses(KeyEvent event) {
        ArrayList<Course> searchCourses = new ArrayList<Course>();
        if(searchfield.getText().equals("")) {
            searchCourses = allAvailableCourses;
        } else {
            for(Course course : allAvailableCourses) {
                if(course.getShortName().contains(searchfield.getText().toUpperCase())) {
                    searchCourses.add(course);
                }
            }
        }

        ObservableList<Course> courseListCells = FXCollections.observableArrayList(searchCourses);
        courselistview.setItems(courseListCells);
    }

    public void setSearchKeyword(Keyword keyword) {
        this.searchKeyword = keyword;
        initialize();
    }
}
