package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AdvisorViewSemesterPlanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backbutton;

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
    private Button homebutton;

    @FXML
    private Button leaveNoteButton;

    @FXML
    private Accordion semesterplansaccordion;

    @FXML
    void backbuttonclicked(ActionEvent event) {

    }

    @FXML
    void homebuttonclicked(ActionEvent event) {

    }

    @FXML
    void leavenoteclicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert backbutton != null : "fx:id=\"backbutton\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert courseCorequisitesLabel != null : "fx:id=\"courseCorequisitesLabel\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert courseCreditHoursLabel != null : "fx:id=\"courseCreditHoursLabel\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert courseDescriptionLabel != null : "fx:id=\"courseDescriptionLabel\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert courseFullNameLabel != null : "fx:id=\"courseFullNameLabel\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert courseKeywordsLabel != null : "fx:id=\"courseKeywordsLabel\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert courseNameLabel != null : "fx:id=\"courseNameLabel\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert coursePrerequisitesLabel != null : "fx:id=\"coursePrerequisitesLabel\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert courseSemestersLabel != null : "fx:id=\"courseSemestersLabel\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert courseStudentGradeLabel != null : "fx:id=\"courseStudentGradeLabel\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert homebutton != null : "fx:id=\"homebutton\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert leaveNoteButton != null : "fx:id=\"leaveNoteButton\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";
        assert semesterplansaccordion != null : "fx:id=\"semesterplansaccordion\" was not injected: check your FXML file 'advisorviewsemesterplan.fxml'.";

    }

}
