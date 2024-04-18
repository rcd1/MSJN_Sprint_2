package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.*;


public class NewStudentSemesterPlanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Accordion semesterplansaccordion;

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

    private User currentUser;

    @FXML
    void initialize() throws IOException {
        currentUser = DegreeFacade.getInstance().getCurrentUser();
        currentUser = DegreeFacade.getInstance().login("bwest@email.sc.edu","ma3w&zh3r3");

        if(currentUser instanceof Student) {
            ArrayList<SemesterPlan> semesterPlans = ((Student)currentUser).generateEightSemesterPlan();
            if(semesterPlans != null) {
                System.out.println("Semester plan received correctly");
            }

            for(int i = 0; i < semesterPlans.size(); i++) {
                TitledPane semester = getBlankSemester("Semester " + (i + 1));
                semesterplansaccordion.getPanes().add(semester);

                ArrayList<Course> semesterCourses = semesterPlans.get(i).getCourses();
                for(int j = 0; j < semesterCourses.size(); j++) {
                    StringBuilder sb = new StringBuilder();
                    Course currentCourse = semesterCourses.get(j);
                    if(currentCourse.getDesignator() != Designator.FILL) {
                        sb.append(currentCourse.getShortName());
                    } else {
                        sb.append(currentCourse.getName());
                    }
                    Button button = getBlankCourse(sb.toString());

                    addButtonEventListener(button, currentCourse);
                    
                    addCourseToSemester(semester, button);
                }
            }




        }

        // TitledPane titledPane = getBlankSemester("Semester 1");
        // semesterplansaccordion.getPanes().add(titledPane);

        // for(int i = 0; i < 7; i++) {
        //     TitledPane titledPane1 = getBlankSemester("Semester " + (i + 2));
        //     semesterplansaccordion.getPanes().add(titledPane1);
        // }

        // for(int i = 0; i < 5; i++) {
        //     Button button = getBlankCourse("Course" + (i + 1));
        //     Node anchorPane = titledPane.getContent();
        
        //     Node vbox = ((AnchorPane) anchorPane).getChildren().get(0);

        //     int[] storeIndex = new int[1];
        //     storeIndex[0] = i;
        //     button.setOnAction(new EventHandler<ActionEvent>() {
        //         @Override
        //         public void handle(ActionEvent event) {
        //             courseNameLabel.setText("Semester " + (storeIndex[0] + 1));
        //         }
        //     });
            
        //     ((VBox) vbox).getChildren().add(button);
        // }




    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(msjn.App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private TitledPane getBlankSemester(String text) throws IOException {
        TitledPane titledPane = (TitledPane)loadFXML("newstudentsemesterplansemesterbutton").lookup("#semesterstub");
        titledPane.setText(text);
        return titledPane;
    }

    private Button getBlankCourse(String text) throws IOException {
        Button button = (Button)loadFXML("newstudentsemesterplancoursebutton").lookup("#coursestub");
        button.setText(text);
        return button;
    }

    private void addCourseToSemester(TitledPane semester, Button course) {
        Node anchorPane = semester.getContent();
        Node vbox = ((AnchorPane) anchorPane).getChildren().get(0);
        ((VBox) vbox).getChildren().add(course);

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
            coursePrerequisitesLabel.setText("Prerequisites: TBA");
        }

        ArrayList<RequirementSet> corequisites = course.getCorequisites();
        if(corequisites == null || corequisites.isEmpty()) {
            courseCorequisitesLabel.setText("Corequisites: None");
        } else {
            courseCorequisitesLabel.setText("Corequisites: TBA");
        }

        Grade grade = ((Student)currentUser).satisfiesPrerequisite(course);
        if(grade != null) {
            courseStudentGradeLabel.setText("Student Grade: " + grade.getLetter());
        } else {
            courseStudentGradeLabel.setText("Student Grade: None");
        }

        ArrayList<Keyword> keywords = course.getKeywords();
        if(keywords == null || keywords.isEmpty()) {
            courseKeywordsLabel.setText("Keywords: None");
        } else {
            courseKeywordsLabel.setText("Keywords: TBA");
        }

        ArrayList<SemesterOffered> semestersOffered =  course.getSemestersOffered();
        if(semestersOffered == null || semestersOffered.isEmpty()) {
            courseSemestersLabel.setText("Semesters Offered: None");
        } else {
            courseSemestersLabel.setText("Semesters Offered: TBA");
        }
    }

    private void addButtonEventListener(Button button, Course course) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateCoursePane(course);
            }
        });
    }

}
