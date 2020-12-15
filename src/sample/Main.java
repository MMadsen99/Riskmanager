package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static Scene scene;
    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("RiskManager");
        primaryStage.setScene(new Scene(root, 1, 1));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
