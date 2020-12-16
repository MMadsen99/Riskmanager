package control;
import exceptions.NoProjectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Project;
import model.ProjectTable;
import model.RiskTable;
import persistence.ProjectLibrary;
import view.ProjectTableFX;
import view.ProjectsFX;
import view.RiskFX;
import view.RiskTableFX;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RiskManagerController implements Initializable {
    @FXML
    private Button createProject;

    @FXML
    private Button  loadProject;

    @FXML
    private Button ModifyRiskKnap;

    @FXML
    private Button addCounterMeasure;

    @FXML
    private Button activateCounterMeasureKnap;

    @FXML
    private Button deleteKnap;

    @FXML
    private Button addRisk;

    @FXML
    public ListView<ProjectsFX> projectFXListView = new ListView<>();

    @FXML
    public TableView<RiskFX> riskFXTableView = new TableView<>();

    // Data fields
    Project openProject = new Project("unnamed");
    ProjectTable projectTable = new ProjectTable();
    RiskTableFX riskTableFX = new RiskTableFX();
    ProjectTableFX projectTableFX = new ProjectTableFX();
    public ListView<ProjectsFX> getProjectFXListView() {
        return projectFXListView;
    }

    // Methods
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ProjectsFX> projectsFXES = new ArrayList<>();
        getProjectTable().getProjects().forEach(p -> projectsFXES.add(new ProjectsFX(p.getProjectId(), p.getProjectName())));
        if (projectFXListView != null) {
            projectFXListView.getItems().addAll(projectsFXES);

        }
    }

    @FXML
    public void loadProjects() throws NoProjectException {
        ProjectLibrary.loadProjects(this);
        projectFXListView.getItems().clear();
        ArrayList<Project> projects = getProjectTable().getProjects();
        for (Project project:projects) {
            projectFXListView.getItems().add(new ProjectsFX(project.getProjectId(), project.getProjectName()));
        }
        this.riskTableFX.updateRisks(this, getOpenProject().getRiskTable());
    }
    // Getters and Setters

    public RiskManagerController getRiskManagerController() {
        return this;
    }
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
        this.riskTableFX.updateProjects(projectFXListView, projectTable);
    }

    public Project getOpenProject() {
        return this.openProject;
    }


   public void displayAddRiskPopUp(ActionEvent event) throws IOException {
           Stage window = new Stage();
           Parent addRiskParent = FXMLLoader.load(RiskManagerController.class.getResource("../fxmlressources/AddRisk.fxml"));
           Scene addRiskScene = new Scene(addRiskParent, 500, 300);
           window.initModality(Modality.APPLICATION_MODAL);
           window.setScene(addRiskScene);
           window.showAndWait();

    }

    public void displayCreateProjectPopUp(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent createProjectParent = FXMLLoader.load(RiskManagerController.class.getResource("../fxmlressources/CreateProject.fxml"));
        Scene createProjectScene = new Scene(createProjectParent, 500, 300);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(createProjectScene);
        window.showAndWait();

    }

    public void displayModifyRiskPopUp(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent modifyRiskParent = FXMLLoader.load(RiskManagerController.class.getResource("../fxmlressources/ModifyRisk.fxml"));
        Scene modifyRiskScene = new Scene(modifyRiskParent, 500, 300);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(modifyRiskScene);
        window.showAndWait();
    }

    public void displayAddCounterMeasurePopUp(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent addCounterMeasureParent = FXMLLoader.load(RiskManagerController.class.getResource("../fxmlressources/AddCounterMeasure.fxml"));
        Scene addCounterMeasureScene = new Scene(addCounterMeasureParent, 500, 300);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(addCounterMeasureScene);
        window.showAndWait();
    }

    public void displayDeletePopUp(ActionEvent event ) throws IOException {
        Stage window = new Stage();
        Parent deleteParent = FXMLLoader.load(RiskManagerController.class.getResource("../fxmlressources/Delete.fxml"));
        Scene deleteScene = new Scene(deleteParent, 500, 300);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(deleteScene);
        window.showAndWait();
    }



}
