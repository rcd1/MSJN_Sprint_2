package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.ApplicationID;
import model.*;
import msjn.*;

 public class ViewAppArea{
    @FXML
    private ResourceBundle resource;

    @FXML
    private URL location;

    @FXML
    private RadioButton undeclaredRadioButton;

    @FXML
    private RadioButton scienceRadioButton;

    @FXML
    private RadioButton mathRadioButton;

    @FXML
    private RadioButton digitalDesignRadioButton;

    @FXML
    private RadioButton robotRadioButton;

    @FXML
    private RadioButton speechRadioButton;

    @FXML
    private App app;

    private Button homebutton;

    private Student student;

    private ToggleGroup toggleGroup;

    public void setApp(App app) {
        this.app = app;
    }

     public void setStudent(Student student){
         this.student = student;
     }

     @FXML
     void homebuttonclicked(ActionEvent event) throws IOException {
         App.setRoot("studentProfile");
     }

    @FXML
    void buttonclicked(ActionEvent event) throws IOException {
        System.out.println("Button clicked!");
    }

    @FXML 
    void setToUndeclared (ActionEvent event) {
        if(student != null) {
            student.setApplicationID(ApplicationID.UNDECLARED);
        }
    }

    
    @FXML 
    void setToScience (ActionEvent event) {
        if(student != null) {
            student.setApplicationID(ApplicationID.SCIENCE);
        }
    }

    
    @FXML 
    void setToMath (ActionEvent event) {
        if(student != null) {
            student.setApplicationID(ApplicationID.MATH);
        }
    }

    
    @FXML 
    void setToDigitalDesign (ActionEvent event) {
        if(student != null) {
            student.setApplicationID(ApplicationID.DIGITALDESIGN);
        }
    }

    
    @FXML 
    void setToRobotics (ActionEvent event) {
        if(student != null) {
            student.setApplicationID(ApplicationID.ROBOTICS);
        }
    }


    
    @FXML 
    void setToSpeech (ActionEvent event) {
        if(student != null) {
            student.setApplicationID(ApplicationID.SPEECH);
        }
    }
    @FXML
    void initialize() {
        toggleGroup = new ToggleGroup();
        
        undeclaredRadioButton.setToggleGroup(toggleGroup);
        scienceRadioButton.setToggleGroup(toggleGroup);
        mathRadioButton.setToggleGroup(toggleGroup);
        digitalDesignRadioButton.setToggleGroup(toggleGroup);
        robotRadioButton.setToggleGroup(toggleGroup);
        speechRadioButton.setToggleGroup(toggleGroup);
    }

    @FXML
    void backButtonClicked(ActionEvent event) {

    }

    @FXML
    void homeButtonClicked(ActionEvent event) {

    }
}
