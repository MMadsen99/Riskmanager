package control;

import exceptions.NoProjectException;
import javafx.event.ActionEvent;

public class AddRiskController {
    public void addRisk(ActionEvent event) throws NoProjectException {
        this.openProject.addRisk(nameField.getText(), proSlide.getValue(), Double.parseDouble(conField.getText()), desField.getText());
        this.riskTableFX.Update(this);
    }

}
