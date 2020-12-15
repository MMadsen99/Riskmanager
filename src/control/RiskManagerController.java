package control;

import exceptions.NoProjectException;
import exceptions.NoRiskException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Project;
import model.ProjectTable;
import persistence.ProjectLibrary;
import view.ProjectTableFX;
import view.ProjectsFX;
import view.RiskTableFX;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RiskManagerController {

    @FXML
    private Button loadProject;

    @FXML ListView projectListView;


    // Data fields
    Project openProject = new Project("unnamed");
    ProjectTable projectTable = new ProjectTable();
    RiskTableFX riskTableFX = new RiskTableFX();

    // Methods
    public void createProject(String projectName) throws NoProjectException {
        projectTable.createProject(projectName);
        this.riskTableFX.Update(this);
    }
    @FXML
    public void loadProjects(ArrayList<Project> projects) {
        projectTable = new ProjectTable();
        for (Project project: projects) {
            projectTable.createProject(project.getProjectName());
        }
    }
    @FXML
    public void loadProject() throws NoProjectException {
        ProjectLibrary.loadProjects(this);
        ArrayList<Project> projects = getProjectTable().getProjects();

        for (Project project:projects) {
            projectListView.getItems().add(new ProjectsFX(project.getProjectId(), project.getProjectName()));
        }

    }

    public void deleteProject(int projectID) throws NoProjectException {
        this.projectTable.deleteProject(projectID);
        this.riskTableFX.Update(this);
    }


    public void addRisk(String name, double probability, double cost, String description) throws NoProjectException {
        this.openProject.addRisk(name, probability, cost, description);
        this.riskTableFX.Update(this);
    }

    public void addCounterMeasure(int riskID, double probabilityImpact, double consequenceImpact, String description, boolean active) throws NoProjectException {
        getOpenProject().addCounterMeasure(riskID, probabilityImpact, consequenceImpact, description, active);
        this.riskTableFX.Update(this);
    }
    public void removeCounterMeasure(int riskID) {
        getOpenProject().removeCounterMeasure(riskID);
    }

    public void activateCounterMeasure(int riskID, boolean wantedState) throws NoRiskException {
        getOpenProject().getRiskTable().getRisk(riskID).activateCounterMeasure(wantedState);
    }
    // Getters and Setters
    public ProjectTable getProjectTable() {
        return projectTable;
    }

    public void setProjectTable(ProjectTable projectTable) {
        this.projectTable = projectTable;
    }

    public void setOpenProject(int projectID) throws NoProjectException {
        try {
            this.openProject = projectTable.getProject(projectID);
        } catch (Exception e) {

        }
        this.riskTableFX.Update(this);
    }

    public Project getOpenProject() {
        return this.openProject;
    }
    public static void main(String[] args) throws NoProjectException, NoRiskException {
        RiskManagerController riskManagerController = new RiskManagerController();

        riskManagerController.createProject("name");
        riskManagerController.createProject("mike");

        ProjectLibrary.saveProjects(riskManagerController);
    }
}
