package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import model.*;
import msjn.App;

public class StudentProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void goToApplicationArea(MouseEvent event) throws IOException {
        App.setRoot("viewAppArea");
    }

    @FXML
    void goToFillRequirements(MouseEvent event) throws IOException {
        App.setRoot("fillrequirementcourses");
    }

    @FXML
    void goToSemesterPlans(MouseEvent event) throws IOException {
        App.setRoot("newstudetnsemesterplan");
    }

    @FXML
    void goToViewCourses(MouseEvent event) throws IOException {
        App.setRoot("newviewcourses");
    }

    @FXML
    void goToViewNotes(MouseEvent event) throws IOException {
        App.setRoot("studentViewNotes");
    }

    @FXML
    public void logout() throws IOException {
        DegreeFacade.getInstance().logout();
        App.setRoot("newwelcomescreen");
    }

    @FXML
    void initialize() {

    }

}
