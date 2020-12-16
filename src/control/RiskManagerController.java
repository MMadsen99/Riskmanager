package control;

import exceptions.NoProjectException;
import exceptions.NoRiskException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.Project;
import model.ProjectTable;
import persistence.ProjectLibrary;
import view.ProjectTableFX;
import view.ProjectsFX;
import view.RiskFX;
import view.RiskTableFX;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RiskManagerController implements Initializable {


    @FXML
    public ListView<ProjectsFX> projectFXListView;

    @FXML
    public TableView<RiskFX> riskFXTableView;
    // Data fields
    Project openProject = new Project("unnamed");
    ProjectTable projectTable = new ProjectTable();
    RiskTableFX riskTableFX = new RiskTableFX();



    // Methods
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ProjectsFX> projectsFXES = new ArrayList<>();
        getProjectTable().getProjects().forEach(p -> projectsFXES.add(new ProjectsFX(p.getProjectId(), p.getProjectName())));
        projectFXListView.getItems().addAll(projectsFXES);
        try {
            createProject();
        } catch (NoProjectException e) {
            e.printStackTrace();
        }

        try {
            setOpenProject(1);
        } catch (NoProjectException e) {
            e.printStackTrace();
        }
        try {
            addRisk("mik",2,2,"2");
        } catch (NoProjectException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void createProject() throws NoProjectException {
        projectTable.createProject("mike");

        projectFXListView.getItems().add(new ProjectsFX(1, "hi"));
    }

    @FXML
    public void loadProjects() throws NoProjectException {
        ProjectLibrary.loadProjects(this);
        projectFXListView.getItems().clear();
        ArrayList<Project> projects = getProjectTable().getProjects();
        for (Project project:projects) {
            projectFXListView.getItems().add(new ProjectsFX(project.getProjectId(), project.getProjectName()));
        }
        this.riskTableFX.Update(this);
    }
    @FXML
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
            System.out.println("No project with this ID");
        }
        this.riskTableFX.Update(this);
    }

    public Project getOpenProject() {
        return this.openProject;
    }
    public static void main(String[] args) throws NoProjectException, NoRiskException {
        RiskManagerController riskManagerController = new RiskManagerController();
        ProjectLibrary.saveProjects(riskManagerController);
    }
}
