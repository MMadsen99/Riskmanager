package sample;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
    public void start(Stage primarystage) throws IOException {
        scene = new Scene(loadFXML(), 640, 480);
        primarystage.setScene(scene);
        primarystage.setTitle("RiskManager");

        primarystage.setMaximized(true);
        primarystage.show();
    }
    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( "sample.fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}