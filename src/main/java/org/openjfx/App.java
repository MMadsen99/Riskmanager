package org.openjfx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.openjfx.model.Project;
import org.openjfx.model.Risk;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {
    static Project project = new Project();

    private static Scene scene;
    private static FileChooser fileChooser = new FileChooser();

    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("hi");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void loadProject() throws IOException {

        File projectFile = fileChooser.showOpenDialog(scene.getWindow());
        Scanner reader = new Scanner(projectFile);

        while (reader.hasNextLine()) {
            String[] riskArgs = reader.nextLine().split(" ");
                            project.addRisk(new Risk(riskArgs[0],
                         Double.parseDouble(riskArgs[1]),
                     Double.parseDouble(riskArgs[2])));
        }
        reader.close();
    }

    static void saveProject(RiskViewController riskViewController) throws IOException {
        try {
            File projectFile = fileChooser.showSaveDialog(scene.getWindow());
            FileWriter writer = new FileWriter(projectFile);
            ObservableList<Risk> risks = riskViewController.getTableView().getItems();
            for (Risk risk:risks
                 ) {
                writer.write(risk.getName() + " " + risk.getCost() + " " + risk.getProbability() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}