package controllers;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;
import msjn.App;

public class StudentProfileController {
    @FXML
    public void logout() throws IOException {
        App.setRoot("welcome");
    }
}
