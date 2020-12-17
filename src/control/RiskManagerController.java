package control;
import exceptions.NoProjectException;
import exceptions.NoRiskException;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Project;
import model.ProjectTable;
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
    private Button DeleteProject;

    @FXML
    public ListView<ProjectsFX> projectFXListView;

    @FXML
    public TableView<RiskFX> riskFXTableView = new TableView<>();

    // Data fields
    Project openProject = new Project("unnamed");
    RiskFX selectedRisk;
    ProjectsFX selectedProject;
    int riskId;
    int projectID;
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


    public void createProject(String name) throws NoProjectException {
        projectTable.createProject(name);
        projectTableFX.Update(this);
    }

    public void loadProjects() throws NoProjectException {
        ProjectLibrary.loadProjects(this);
        projectFXListView.getItems().clear();
        ArrayList<Project> projects = getProjectTable().getProjects();
        for (Project project:projects) {
            projectFXListView.getItems().add(new ProjectsFX(project.getProjectId(), project.getProjectName()));
        }
        this.riskTableFX.Update(this);
    }

    public void deleteProject(int projectID) throws NoProjectException {
        this.projectTable.deleteProject(projectID);
        this.projectTableFX.Update(this);
    }

    public void modifyRisk(int riskID,String riskName, double probability, double consequence, String description) throws NoProjectException, NoRiskException {
        this.openProject.editRisk(riskID,riskName,probability,consequence,description);
        this.riskTableFX.Update(this);
    }

    public void addRisk(String name, double probabilitySlideValue, double consequence, String description) throws NoProjectException {
        this.openProject.addRisk(name, probabilitySlideValue, consequence, description);
        this.riskTableFX.Update(this);
    }

    public void deleteRisk(int riskID) throws NoRiskException, NoProjectException {
        getOpenProject().getRiskTable().deleteRisk(riskID);
        this.riskTableFX.Update(this);
    }

    public void addCounterMeasure(int riskID, double probabilityImpact, double consequenceImpact, String description, boolean active) throws NoProjectException, NoRiskException {
        getOpenProject().addCounterMeasure(riskID, probabilityImpact, consequenceImpact, description, active);
        this.riskTableFX.Update(this);
    }
    public void removeCounterMeasure(int riskID) throws NoProjectException {
        getOpenProject().removeCounterMeasure(riskID);
        this.riskTableFX.Update(this);
    }

    public void activateCounterMeasure(int riskID, boolean wantedState) throws NoRiskException, NoProjectException {
        getOpenProject().getRiskTable().getRisk(riskID).activateCounterMeasure(wantedState);
        this.riskTableFX.Update(this);
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



   public void displayAddRiskPopUp(ActionEvent event) throws IOException {
       // made a another push
       Stage popupwindow =new Stage();

       popupwindow.initModality(Modality.APPLICATION_MODAL);

       popupwindow.setTitle("Add Risk");

       Label labelName = new Label("Risk name");
       labelName.setStyle("-fx-font-weight: bold");

       Label labelDescription = new Label("Risk description");
       labelDescription.setStyle("-fx-font-weight: bold");

       Label labelConsequence = new Label("Risk consequence");
       labelConsequence.setStyle("-fx-font-weight: bold");

       Label labelProbability = new Label("Risk probability");
       labelProbability.setStyle("-fx-font-weight: bold");

       TextField riskName = new TextField("Give the project name");
       TextArea riskDescription = new TextArea("Give the project name");
       TextField riskConsequence = new TextField("Give the project name");
       Slider riskProbability = new Slider(0, 100, 25);
       riskProbability.setShowTickMarks(true);
       riskProbability.setShowTickLabels(true);

       final Label riskProbabilityLabel = new Label("");

       riskProbabilityLabel.textProperty().bind(Bindings.format("%.0f", riskProbability.valueProperty()));


       Button createButton = new Button(" Add  ");

       Button cancelButton = new Button("Cancel");

       createButton.setOnAction(e -> {

           try {
               addRisk(riskName.getText(),
                       riskProbability.getValue(),
                       Double.parseDouble(riskConsequence.getText()),
                       riskDescription.getText()
               );
           } catch (NoProjectException noProjectException) {
               noProjectException.printStackTrace();
           }
           popupwindow.close();
       });

       cancelButton.setOnAction(e -> popupwindow.close());

       GridPane gridPane = new GridPane();

       VBox  vBox = new VBox(labelName, riskName,
                                    labelDescription, riskDescription,
                                    labelConsequence, riskConsequence,
                                    labelProbability,riskProbabilityLabel, riskProbability);
       HBox  hBox = new HBox(createButton, cancelButton);

       vBox.setSpacing(10);
       hBox.setSpacing(25);

       vBox.getChildren().add(hBox);
       gridPane.getChildren().add(vBox);

       gridPane.setAlignment(Pos.CENTER);

       Scene scene1= new Scene(gridPane);
       popupwindow.setScene(scene1);
       popupwindow.showAndWait();
    }

    public void displayCreateProjectPopUp(ActionEvent event) throws IOException {

        // made a another push
        Stage popupwindow =new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);

        popupwindow.setTitle("Create Project");

        Label label = new Label("Project name");
        label.setStyle("-fx-font-weight: bold");
        TextField projectName = new TextField("Give the project name");

        Button createButton = new Button("Create");

        Button cancelButton = new Button("Cancel");

        createButton.setOnAction(e -> {

            try {
                createProject(projectName.getText());
            } catch (NoProjectException noProjectException) {
                noProjectException.printStackTrace();
            }
            popupwindow.close();
        });

        cancelButton.setOnAction(e -> popupwindow.close());

        GridPane gridPane = new GridPane();

        VBox  vBox = new VBox(label, projectName);
        HBox  hBox = new HBox(createButton, cancelButton);

        vBox.setSpacing(10);
        hBox.setSpacing(25);

        vBox.getChildren().add(hBox);
        gridPane.getChildren().add(vBox);

        // layout.getChildren().addAll(projectName, createButton, cancelButton);

        gridPane.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(gridPane, 300, 250);
        popupwindow.setScene(scene1);

        popupwindow.showAndWait();


    }

    public void displayModifyRiskPopUp(ActionEvent event) throws IOException {
        Stage popupwindow =new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Modify Risk");

        Label labelName = new Label("New risk name");
        labelName.setStyle("-fx-font-weight: bold");

        Label labelDescription = new Label("New risk description");
        labelDescription.setStyle("-fx-font-weight: bold");

        Label labelConsequence = new Label("New risk consequence");
        labelConsequence.setStyle("-fx-font-weight: bold");

        Label labelProbability = new Label("New risk probability");
        labelProbability.setStyle("-fx-font-weight: bold");

        RiskFX selectedRisk = riskFXTableView.getSelectionModel().getSelectedItem();

        TextField riskName = new TextField(selectedRisk.getName());
        TextArea riskDescription = new TextArea(selectedRisk.getDescription());
        TextField riskConsequence = new TextField(String.valueOf(selectedRisk.getConsequence()));
        Slider riskProbability = new Slider(0, 100, selectedRisk.getProbability());
        riskProbability.setShowTickMarks(true);
        riskProbability.setShowTickLabels(true);

        final Label riskProbabilityLabel = new Label("");

        riskProbabilityLabel.textProperty().bind(Bindings.format("%.0f", riskProbability.valueProperty()));

        Button createButton = new Button(" Modify  ");

        Button cancelButton = new Button("Cancel");

        createButton.setOnAction(e -> {
            System.out.println(selectedRisk.toString());

            try {
                modifyRisk(selectedRisk.getId(),
                          selectedRisk.getName(),
                         selectedRisk.getProbability(),
                        selectedRisk.getConsequence(),
                         selectedRisk.getDescription()
                );
            } catch (NoProjectException | NoRiskException noProjectException) {
                noProjectException.printStackTrace();
            }
            popupwindow.close();
        });

        cancelButton.setOnAction(e -> popupwindow.close());

        GridPane gridPane = new GridPane();

        VBox  vBox = new VBox(labelName, riskName,
                labelDescription, riskDescription,
                labelConsequence, riskConsequence,
                labelProbability, riskProbability, riskProbabilityLabel);
        HBox  hBox = new HBox(createButton, cancelButton);

        vBox.setSpacing(10);
        hBox.setSpacing(25);

        vBox.getChildren().add(hBox);
        gridPane.getChildren().add(vBox);

        gridPane.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(gridPane);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    public void displayAddCounterMeasurePopUp(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent addCounterMeasureParent = FXMLLoader.load(RiskManagerController.class.getResource("../fxmlressources/AddCounterMeasure.fxml"));
        Scene addCounterMeasureScene = new Scene(addCounterMeasureParent, 500, 300);
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(addCounterMeasureScene);
        window.showAndWait();
    }

    public void displayDeleteRiskPopUp(ActionEvent event ) throws IOException {
        // made a another push
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);

        popupwindow.setTitle("Delete Risk");

        this.selectedRisk = riskFXTableView.getSelectionModel().getSelectedItem();
       this.riskId = selectedRisk.getId();

        Label label = new Label("Are you sure you want to delete this risk?");
        Label riskLabel = new Label(selectedRisk.getName());
        label.setStyle("-fx-font-weight: bold");

        Button createButton = new Button("Delete");

        Button cancelButton = new Button("Cancel");

        createButton.setOnAction(e -> {

            try {
                deleteRisk(this.riskId);
            } catch (NoRiskException | NoProjectException noProjectException) {
                noProjectException.printStackTrace();
            }
            popupwindow.close();
        });


        cancelButton.setOnAction(e -> popupwindow.close());

        GridPane gridPane = new GridPane();

        VBox  vBox = new VBox(label, riskLabel);
        HBox  hBox = new HBox(createButton, cancelButton);

        vBox.setSpacing(10);
        hBox.setSpacing(25);

        vBox.getChildren().add(hBox);
        gridPane.getChildren().add(vBox);

        // layout.getChildren().addAll(projectName, createButton, cancelButton);

        gridPane.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(gridPane, 300, 250);
        popupwindow.setScene(scene1);

        popupwindow.showAndWait();
    }



    @FXML
    void displayDeleteProjectPopUp(ActionEvent event) {

        // made a another push
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);

        popupwindow.setTitle("Delete Project");

        this.selectedProject = projectFXListView.getSelectionModel().getSelectedItem();
        this.projectID = selectedProject.getId();

        Label label = new Label("Are you sure you want to delete this Project?");
        Label riskLabel = new Label(selectedProject.getName());
        label.setStyle("-fx-font-weight: bold");

        Button createButton = new Button("Delete");

        Button cancelButton = new Button("Cancel");

        createButton.setOnAction(e -> {
            try {
                deleteProject(this.projectID);
            } catch (NoProjectException noProjectException) {
                noProjectException.printStackTrace();
            }
            popupwindow.close();
        });

        cancelButton.setOnAction(e -> popupwindow.close());

        GridPane gridPane = new GridPane();

        VBox  vBox = new VBox(label, riskLabel);
        HBox  hBox = new HBox(createButton, cancelButton);

        vBox.setSpacing(10);
        hBox.setSpacing(25);

        vBox.getChildren().add(hBox);
        gridPane.getChildren().add(vBox);

        // layout.getChildren().addAll(projectName, createButton, cancelButton);

        gridPane.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(gridPane, 300, 250);
        popupwindow.setScene(scene1);

        popupwindow.showAndWait();




    }





}
