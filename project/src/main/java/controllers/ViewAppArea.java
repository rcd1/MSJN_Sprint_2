package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import msjn.App;

 public class ViewAppArea {
    @FXML
    private ResourceBundle resource;

    @FXML
    private URL location;

    @FXML
    private RadioButton undeclaredRadioButton;

    @FXML
    private RadioButton scienceRadioButton;

    @FXML
    private RadioButton umathRadioButton;

    @FXML
    private RadioButton digitalDesignRadioButton;

    @FXML
    private RadioButton robotRadioButton;

    @FXML
    private RadioButton speechRadioButton;

    @FXML
    void buttonclicked(MouseEvent event) throws IOException {
        System.out.println("Application Area selected");
    }


}
