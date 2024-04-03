package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import msjn.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
