package org.openjfx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.openjfx.model.Risk;
import org.openjfx.inputvalidation.InputValidation;

public class FXMLTableViewController {
    @FXML
    private TableView<Risk> tableView;
    @FXML private TextField riskNameField;
    @FXML private TextField riskCostField;
    @FXML private TextField riskProbabilityField;
    @FXML private Alert emailAlert = new Alert(Alert.AlertType.ERROR);

    @FXML
    protected void addRisk() {
        ObservableList<Risk> data = tableView.getItems();
        if (InputValidation.isDouble(riskProbabilityField, riskProbabilityField.getText())) {
            data.add(new Risk(riskNameField.getText(),
                    Double.parseDouble(riskCostField.getText()),
                    Double.parseDouble(riskProbabilityField.getText())
            ));
            riskNameField.setText("");
            riskCostField.setText("");
            riskProbabilityField.setText("");
        } else {
            emailAlert.setHeaderText("Bad Input Alert");
            emailAlert.setContentText("Not a double..");
            emailAlert.showAndWait();
        }
    }
    @FXML
    protected void removeRisk() {
        Risk selectedRisk = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selectedRisk);
    }

}
