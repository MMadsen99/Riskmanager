package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.openjfx.inputvalidation.InputValidation;
import org.openjfx.model.Project;
import org.openjfx.model.Risk;

import java.util.Optional;

public class RiskViewController {
    ;
    @FXML
    private TableView<Risk> tableView;
    @FXML
    private TitledPane riskPane;
    @FXML
    private TextArea summaryTextArea;
    @FXML private TextField riskNameField;
    @FXML private TextField riskCostField;
    @FXML private TextField riskProbabilityField;

    @FXML private Alert inputNotDoubleAlert = new Alert(Alert.AlertType.ERROR);
    @FXML private Alert editConfirm = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    protected void addRisk() {
        ObservableList<Risk> data = tableView.getItems();

        if (isUnique() && InputValidation.isDouble(riskProbabilityField, riskProbabilityField.getText())) {
            data.add(new Risk(riskNameField.getText(),
                    Double.parseDouble(riskCostField.getText()),
                    Double.parseDouble(riskProbabilityField.getText())
            ));
            riskNameField.setText("");
            riskCostField.setText("");
            riskProbabilityField.setText("");
        } else {
            inputNotDoubleAlert.setHeaderText("Bad Input Alert");
            inputNotDoubleAlert.setContentText("Not a double..");
            inputNotDoubleAlert.showAndWait();
        }
    }
    @FXML
    protected void removeRisk() {
        Risk selectedRisk = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selectedRisk);
    }

    @FXML
    public void loadDescription() {
        try {
            Risk selectedRisk = tableView.getSelectionModel().getSelectedItem();
            riskPane.setText(selectedRisk.getName());
            summaryTextArea.setText(selectedRisk.getString());
        } catch (Exception e) {

        }

    }
    @FXML
    public boolean isUnique() {
        for (Risk r:tableView.getItems()
             ) {
            if (r.getName().equals(riskNameField.getText())) {
                return false;
            }
        }
        return true;
    }

    public void resetDescription() {
        riskPane.setText("Risk Summary");
        summaryTextArea.setText("Chose a risk");
    }

    public void editRisk() {
        Risk selectedRisk = tableView.getSelectionModel().getSelectedItem();

        if (selectedRisk == null) {

        }
        if (selectedRisk != null && InputValidation.isDouble(riskProbabilityField, riskProbabilityField.getText())) {

            editConfirm.setContentText("Are you sure you want to edit the risk?");

            if(editConfirm.showAndWait().get() == ButtonType.OK) {
                tableView.getItems().remove(selectedRisk);
                addRisk();
            }
            clearFields();

        } else if (selectedRisk != null &&!InputValidation.isDouble(riskProbabilityField,
                                                                    riskProbabilityField.getText())) {
            inputNotDoubleAlert.setHeaderText("Bad Input Alert");
            inputNotDoubleAlert.setContentText("You're inputs are wrongly formatted, can't let you do this edit");
            inputNotDoubleAlert.showAndWait();
            clearFields();
        } else {
            inputNotDoubleAlert.setAlertType(Alert.AlertType.INFORMATION);
            inputNotDoubleAlert.setContentText("You havn't chosen a risk to edit");
            inputNotDoubleAlert.show();
        }
    }

    private void clearFields() {
        riskNameField.setText("");
        riskCostField.setText("");
        riskProbabilityField.setText("");
    }

}
