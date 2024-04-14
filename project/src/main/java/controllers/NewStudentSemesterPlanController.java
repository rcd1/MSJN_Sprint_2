package controllers;

import java.io.IOException;
import java.net.URL;
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


public class NewStudentSemesterPlanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Accordion semesterplansaccordion;

    @FXML
    private Label courseNameLabel;

    @FXML
    void initialize() throws IOException {
        TitledPane titledPane = (TitledPane)loadFXML("newstudentsemesterplansemesterbutton").lookup("#semesterstub");
        titledPane.setText("Semester 1");
        semesterplansaccordion.getPanes().add(titledPane);

        for(int i = 0; i < 5; i++) {
            Button button = (Button)loadFXML("newstudentsemesterplancoursebutton").lookup("#coursestub");
            Node anchorPane = titledPane.getContent();
        
            Node vbox = ((AnchorPane) anchorPane).getChildren().get(0);

            button.setText("Course" + (i + 1));

            int[] storeIndex = new int[1];
            storeIndex[0] = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    courseNameLabel.setText("Semester " + (storeIndex[0] + 1));
                }
            });
            
            ((VBox) vbox).getChildren().add(button);
        }




    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(msjn.App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
