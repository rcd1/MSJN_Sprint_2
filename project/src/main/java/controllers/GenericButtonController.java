package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addstudentpopup.fxml"));
        dialog.setDialogPane(fxmlLoader.load());
        AddStudentPopupController controller = fxmlLoader.getController();
        dialog.setTitle("Add Student");
        System.out.println(dialog.showAndWait().get());
        System.out.println(controller.getFirstName());
        System.out.println(controller.getLastName());

        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("User not found");
        alert.showAndWait();

    }

    @FXML
    void initialize() {
    
    }

}
