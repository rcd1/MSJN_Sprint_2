package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import msjn.*;

public class GenericButtonController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void buttonclicked(ActionEvent event) throws IOException {
        System.out.println("Button clicked!");
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setDialogPane(loadFXML("addstudentpopup"));
        dialog.setTitle("Add Student");
        System.out.println(dialog.showAndWait().get());

    }

    @FXML
    void initialize() {

    }

    private static DialogPane loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
