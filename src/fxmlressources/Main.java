package fxmlressources;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;

/**
 * JavaFX App
 */
public class Main extends Application {
    private static Scene scene;
    private static FileChooser fileChooser = new FileChooser();

    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(), 640, 480);
        stage.setScene(scene);
        stage.setTitle("RiskManager");
        stage.show();
        stage.setMaximized(true);
    }
    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainPage.fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}