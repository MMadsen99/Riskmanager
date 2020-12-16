package control;

import exceptions.NoProjectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class AddRiskController {
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

    public void addRisk(ActionEvent event) throws NoProjectException {
        this.openProject.addRisk(nameField.getText(), proSlide.getValue(), Double.parseDouble(conField.getText()), desField.getText());
        this.riskTableFX.Update(this);
    }

}
