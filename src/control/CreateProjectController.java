package control;

import exceptions.NoProjectException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.ProjectTableFX;
import view.ProjectsFX;

public class CreateProjectController extends RiskManagerController {

    @FXML
    private Button createProjectCreateButton;

    @FXML
    private Button cancelProjectCancelButton;

    @FXML
    private TextField createProjectNameField;

    @FXML
    public void createProject() throws NoProjectException {
        getProjectTable().createProject("name");
        projectTableFX.updateProjects(super.getProjectFXListView(), projectTable);
    }
}
