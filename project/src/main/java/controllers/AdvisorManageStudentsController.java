package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import model.*;

public class AdvisorManageStudentsController implements Initializable {

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
    
        String fxmlFileName = "";
        if (selectedTab.getText().equals("Add")) {
            fxmlFileName = "advisorAddStudent.fxml";
        } else if (selectedTab.getText().equals("View")) {
            fxmlFileName = "advisorViewStudent.fxml";
        } else if (selectedTab.getText().equals("Remove")) {
            fxmlFileName = "advisorRemoveStudent.fxml";
        }
    
        loadFXMLIntoAnchorPane(fxmlFileName);
    }

    private void loadFXMLIntoAnchorPane(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(msjn.App.class.getResource(fxmlFileName + ".fxml"));
            AnchorPane root = fxmlLoader.load();
            advisorAddStudent.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML file: " + fxmlFileName);
        }
    }
 }