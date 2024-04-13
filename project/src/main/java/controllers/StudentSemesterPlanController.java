package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import msjn.App;

public class StudentSemesterPlanController implements Initializable {

   /* @FXML
    private MenuItem ENGL102ClickedMenuItem;

    @FXML
    private TextArea courseDetailsTextArea;
*/
    @FXML
    private Text screen;

    @FXML

    private void setOpeningScreen() {

    }

    @FXML
    private void ENGL102Clicked(ActionEvent event) {
        screen.setText("ENGL 102\nRhetoric and Composition\nInstruction and intensive practice in researching, analyzing, and composing written arguments about academic and public issues.\nCredit Hours: 3                    Prerequisites: ENGL 101          Corequisites: None               Student Grade: A            Keywords: None                 Semesters: Spring, Summer, Fall");
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //screen.setText("");
    }
    
}
