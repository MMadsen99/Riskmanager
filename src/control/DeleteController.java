package control;

import exceptions.NoProjectException;
import javafx.fxml.FXML;

public class DeleteController {

    @FXML
    public void deleteProject(int projectID) throws NoProjectException {
        this.projectTable.deleteProject(projectID);
        this.riskTableFX.Update(this);
    }
}
