package persistence;

import exceptions.NoProjectException;
import javafx.stage.FileChooser;
import model.ProjectTable;
import control.RiskManagerController;
import fxmlressources.Main;

import java.io.*;



public class ProjectLibrary  {

    static FileChooser fileChooser = new FileChooser();


    public static void saveProjects(RiskManagerController riskManagerController) {

        try
        {
            FileOutputStream fileOut = new FileOutputStream("src/persistence/projects.file");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(riskManagerController.getProjectTable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadProjects(RiskManagerController riskManagerController) {
        File projectFile = fileChooser.showOpenDialog(Main.getScene().getWindow());

        try
        {
            // Reading the object from a file

            FileInputStream file = new FileInputStream(projectFile);

            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            riskManagerController.setProjectTable((ProjectTable) in.readObject());
            riskManagerController.setOpenProject();
            in.close();
            file.close();
        }

        catch(IOException ex) {
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        catch (NoProjectException e) {
            e.printStackTrace();
        }
    }

}
