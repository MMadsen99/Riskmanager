package persistence;

import model.ProjectTable;
import control.RiskManagerController;

import java.io.*;

public class ProjectLibrary {


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
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/persistence/projects.file");

            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            riskManagerController.setProjectTable((ProjectTable) in.readObject());
            in.close();
            file.close();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

}
