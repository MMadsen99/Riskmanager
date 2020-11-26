package org.openjfx.persistence;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.openjfx.App;
import org.openjfx.RiskViewController;
import org.openjfx.model.Project;
import org.openjfx.model.Risk;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class LoaderSaver {

    static FileChooser fileChooser = new FileChooser();
    private final Project project = new Project();
    public String loadedProjectName = "";

    public String getLoadedProjectName() {
        return loadedProjectName;
    }

    public boolean loadProject() {

        File projectFile = fileChooser.showOpenDialog(App.getScene().getWindow());

        try {
            Scanner reader = new Scanner(projectFile);

            project.getRisks().forEach(project::removeRisk);
            if (!reader.hasNext()) return true;
            loadedProjectName += reader.nextLine().strip();
            while (reader.hasNextLine()) {
                String[] riskArgs = reader.nextLine().split(" ");
                project.addRisk(new Risk(riskArgs[0],
                        Double.parseDouble(riskArgs[1]),
                        Double.parseDouble(riskArgs[2])));
            }
            System.out.println("I loaded something");
            reader.close();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void saveProject(RiskViewController riskViewController) {
        try {
            File projectFile = fileChooser.showSaveDialog(App.getScene().getWindow());
            if (projectFile == null) return;

            FileWriter writer = new FileWriter(projectFile);
            ObservableList<Risk> risks = riskViewController.getTableView().getItems();
            writer.write(project.getProjectName() + "\n");
            for (Risk risk:risks
                 ) {
                writer.write(risk.getName() + " " + risk.getCost() + " " + risk.getProbability() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Project getProject() {
        return project.getProject();
    }
}
