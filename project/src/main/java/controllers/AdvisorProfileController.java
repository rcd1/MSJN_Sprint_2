package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import msjn.App;

public class AdvisorProfileController implements Initializable{

    @FXML
    private void btnManageStudentsClicked(MouseEvent event) throws IOException {
        App.setRoot("managestudents");
    }

    @FXML
    private void btnLogoutClicked(MouseEvent event) throws IOException {
        App.setRoot("welcome");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
    
}
