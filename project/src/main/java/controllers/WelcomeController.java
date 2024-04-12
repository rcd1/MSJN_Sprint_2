package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import msjn.App;
public class WelcomeController implements Initializable {

    @FXML
    private void btnCreateAccountClicked(MouseEvent event) throws IOException {
        App.setRoot("create account");
    }

    @FXML
    private void btnLoginClicked (MouseEvent event) throws IOException {
        App.setRoot("login");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
    
}
