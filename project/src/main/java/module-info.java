module msjn {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.graphics;
    requires javafx.base;

    opens msjn to javafx.fxml;
    exports msjn;

    opens model to javafx.fxml;
    exports model;

    opens controllers to javafx.fxml;
    exports controllers;
}
