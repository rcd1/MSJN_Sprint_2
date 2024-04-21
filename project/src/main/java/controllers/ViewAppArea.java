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
import javafx.scene.control.Toggle;
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

    @FXML
    private Button saveButton;

    private Button homebutton;

    private Student student;

    private ToggleGroup toggleGroup;

    public void setApp(App app) {
        this.app = app;
    }

     public void setStudent(Student student){
         this.student = student;
     }

    //  @FXML
    //  void homebuttonclicked(ActionEvent event) throws IOException {
    //      App.setRoot("studentProfile");
    //  }

    // @FXML
    // void buttonclicked(ActionEvent event) throws IOException {
    //     System.out.println("Button clicked!");
    // }

    // @FXML 
    // void setToUndeclared (ActionEvent event) {
    //     if(student != null) {
    //         student.setApplicationID(ApplicationID.UNDECLARED);
    //     }
    // }

    
    // @FXML 
    // void setToScience (ActionEvent event) {
    //     if(student != null) {
    //         student.setApplicationID(ApplicationID.SCIENCE);
    //     }
    // }

    
    // @FXML 
    // void setToMath (ActionEvent event) {
    //     if(student != null) {
    //         student.setApplicationID(ApplicationID.MATH);
    //     }
    // }

    
    // @FXML 
    // void setToDigitalDesign (ActionEvent event) {
    //     if(student != null) {
    //         student.setApplicationID(ApplicationID.DIGITALDESIGN);
    //     }
    // }

    
    // @FXML 
    // void setToRobotics (ActionEvent event) {
    //     if(student != null) {
    //         student.setApplicationID(ApplicationID.ROBOTICS);
    //     }
    // }


    
    // @FXML 
    // void setToSpeech (ActionEvent event) {
    //     if(student != null) {
    //         student.setApplicationID(ApplicationID.SPEECH);
    //     }
    // }
    @FXML
    void initialize() {
        this.toggleGroup = new ToggleGroup();
        this.student = (Student) DegreeFacade.getInstance().getCurrentUser();
        
        undeclaredRadioButton.setToggleGroup(toggleGroup);
        scienceRadioButton.setToggleGroup(toggleGroup);
        mathRadioButton.setToggleGroup(toggleGroup);
        digitalDesignRadioButton.setToggleGroup(toggleGroup);
        robotRadioButton.setToggleGroup(toggleGroup);
        speechRadioButton.setToggleGroup(toggleGroup);

        if(student.getApplicationID() != ApplicationID.UNDECLARED) {
            disableButtons();
        }
        Toggle toggle;
        switch(student.getApplicationID()) {
            case UNDECLARED:
                toggle = undeclaredRadioButton;
                break;
            case SCIENCE:
                toggle = scienceRadioButton;
                break;
            case MATH:
                toggle = mathRadioButton;
                break;
            case DIGITALDESIGN:
                toggle = digitalDesignRadioButton;
                break;
            case ROBOTICS:
                toggle = robotRadioButton;
                break;
            case SPEECH:
                toggle = speechRadioButton;
                break;
            default:
                toggle = null;
        }
        toggleGroup.selectToggle(toggle);
    }

    @FXML
    void backButtonClicked(ActionEvent event) {

    }

    @FXML
    void homeButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveButtonClicked(ActionEvent event) {
        Toggle toggle = toggleGroup.getSelectedToggle();

        if(toggle == undeclaredRadioButton) {
            return;
        } else if (toggle == scienceRadioButton) {
            student.setApplicationID(ApplicationID.SCIENCE);
        } else if (toggle == mathRadioButton) {
            student.setApplicationID(ApplicationID.MATH);
        } else if (toggle == digitalDesignRadioButton) {
            student.setApplicationID(ApplicationID.DIGITALDESIGN);
        } else if (toggle == robotRadioButton) {
            student.setApplicationID(ApplicationID.ROBOTICS);
        } else if (toggle == speechRadioButton) {
            student.setApplicationID(ApplicationID.SPEECH);
        }
        disableButtons();
        

    }

    void disableButtons() {
        undeclaredRadioButton.setDisable(true);
        scienceRadioButton.setDisable(true);
        mathRadioButton.setDisable(true);
        digitalDesignRadioButton.setDisable(true);
        robotRadioButton.setDisable(true); 
        speechRadioButton.setDisable(true);
        saveButton.setDisable(true);      
    }
}
