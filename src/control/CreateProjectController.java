package control;

import exceptions.NoProjectException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateProjectController {



    @FXML
    private Button createProjectCreateButton;

    @FXML
    private Button cancelProjectCancelButton;

    @FXML
    private TextField createProjectNameField;





    @FXML
    public void createProject() throws NoProjectException {
        System.out.println(createProjectNameField.getText());
        projectTable.createProject(createProjectNameField.getText());
        System.out.println(projectTable.getProjects());
        Stage stage = (Stage) createProjectCreateButton.getScene().getWindow();
        stage.close();

        projectTableFX.Update(this);
    }

}
