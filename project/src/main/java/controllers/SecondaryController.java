package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import msjn.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}