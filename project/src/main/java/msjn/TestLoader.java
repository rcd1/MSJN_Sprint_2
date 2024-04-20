package msjn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class TestLoader extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD
        scene = new Scene(loadFXML("fillRequirementCoures"), 600, 450);
=======
        scene = new Scene(loadFXML("newstudetnsemesterplan"), 600, 450);
>>>>>>> 445fadff2d59d1ab7ca567017b513219d323333d
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setRoot(Parent setParent) {
        scene.setRoot(setParent);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}