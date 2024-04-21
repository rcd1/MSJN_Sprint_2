package msjn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DegreeFacade;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("newwelcomescreen"), 600, 450);
        stage.setScene(scene);
        stage.setMinHeight(450);
        stage.setMinWidth(600);
        stage.setTitle("CourseWorks");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setRoot(Parent setParent) {
        scene.setRoot(setParent);
    }

    public static Parent getRoot() {
        return scene.getRoot();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        //DegreeFacade.getInstance().login("dbeez@email.sc.edu", "ep1c3l1t3");
        launch();
        // DegreeFacade.getInstance().logout();
    }

}