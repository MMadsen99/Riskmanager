package control;

import exceptions.NoProjectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class AddRiskController extends RiskManagerController {
    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField desField;

    @FXML
    private TextField conField;

    @FXML
    private Slider proSlide;

    @FXML
    public void addRisk(ActionEvent event) throws NoProjectException {
        openProject.addRisk(nameField.getText(), proSlide.getValue(), Double.parseDouble(conField.getText()), desField.getText());
        riskTableFX.updateRisks(getRiskManagerController(), getOpenProject().getRiskTable());
    }

}
