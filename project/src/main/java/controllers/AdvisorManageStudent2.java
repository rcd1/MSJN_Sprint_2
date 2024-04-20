package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import model.*;

public class AdvisorManageStudent2 implements Initializable {

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane advisorAddStudent;

    @FXML
    private AnchorPane advisorViewStudent;

    @FXML
    private AnchorPane advisorRemoveStudent;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleTabSelection(newValue);
        });
    }

    private void handleTabSelection(Tab selectedTab) {
         if (selectedTab == null) {
             return;
            }
            
         advisorAddStudent.setDisable(!selectedTab.getText().equals("Add"));
         advisorViewStudent.setDisable(!selectedTab.getText().equals("View"));
         advisorRemoveStudent.setDisable(!selectedTab.getText().equals("Remove"));
    }
 }