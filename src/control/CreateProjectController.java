package control;

import exceptions.NoProjectException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CreateProjectController {

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
