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
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.*;
import msjn.*;


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

    @FXML
    private Button homebutton;

    private User currentUser;

    @FXML
    void initialize() throws IOException {
        currentUser = DegreeFacade.getInstance().getCurrentUser();
        

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

    private void addButtonEventListener(Button button, Course course){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(course.getDesignator() == Designator.FILL) { //TODO handle application area on fill requirement side
                    if(!(course.getKeywords().get(0) == Keyword.AP0 && ((Student) currentUser).getApplicationID() == ApplicationID.UNDECLARED)) {
                        try {
                            FXMLLoader loader =  new FXMLLoader(msjn.App.class.getResource("fillrequirementcoursesrefactor" + ".fxml"));
                            Parent parent = (Parent)loader.load();
                            FillRequirementCoursesRefactorController controller = loader.getController();
                            controller.setSearchKeyword(course.getKeywords().get(0));
                            App.setRoot(parent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    updateCoursePane(course);
                }
            }
        });
    }   

    @FXML
    void homebuttonclicked(ActionEvent event) throws IOException {
        App.setRoot("studentProfile");
    }

}
