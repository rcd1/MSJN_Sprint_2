package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import model.*;

public class FillRequirementCoursesController {

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
    private VBox courselistvbox;

    @FXML
    private Button selectcoursebutton;

    private Keyword searchKeyword;

    @FXML
    void initialize() throws IOException {
        assert courseCorequisitesLabel != null : "fx:id=\"courseCorequisitesLabel\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert courseCreditHoursLabel != null : "fx:id=\"courseCreditHoursLabel\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert courseDescriptionLabel != null : "fx:id=\"courseDescriptionLabel\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert courseFullNameLabel != null : "fx:id=\"courseFullNameLabel\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert courseKeywordsLabel != null : "fx:id=\"courseKeywordsLabel\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert courseNameLabel != null : "fx:id=\"courseNameLabel\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert coursePrerequisitesLabel != null : "fx:id=\"coursePrerequisitesLabel\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert courseSemestersLabel != null : "fx:id=\"courseSemestersLabel\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert courseStudentGradeLabel != null : "fx:id=\"courseStudentGradeLabel\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert courselistvbox != null : "fx:id=\"courselistvbox\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";
        assert selectcoursebutton != null : "fx:id=\"selectcoursebutton\" was not injected: check your FXML file 'fillrequirementcourses.fxml'.";

        searchKeyword = Keyword.LAE;

        ArrayList<Course> courses = CourseList.getInstance().findCourses(searchKeyword.toString());
        for(Course course : courses) {
            courselistvbox.getChildren().add(getBlankCourse(course.getShortName()));
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(msjn.App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private Button getBlankCourse(String text) throws IOException {
        Button button = (Button)loadFXML("fillrequirementcoursesbutton").lookup("#coursestub");
        button.setText(text);
        return button;
    }

}
