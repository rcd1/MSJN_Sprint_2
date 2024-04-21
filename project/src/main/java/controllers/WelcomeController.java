package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import msjn.App;
public class WelcomeController implements Initializable {

    int easterEggCounter = 5;

    @FXML
    private void btnCreateAccountClicked(MouseEvent event) throws IOException {
        App.setRoot("signupscreen");
    }

    @FXML
    private void btnLoginClicked (MouseEvent event) throws IOException {
        App.setRoot("loginscreen");
    }

    @FXML
    void headerclicked(MouseEvent event) throws IOException {
        if(--easterEggCounter == 0) {
            Stage stage = new Stage();
            stage.setTitle("Credits");
            FXMLLoader loader = new FXMLLoader(App.class.getResource("credits.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            easterEggCounter = 5;
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
    
}
