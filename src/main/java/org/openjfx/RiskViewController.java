package org.openjfx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.openjfx.inputvalidation.InputValidation;
import org.openjfx.model.Risk;

public class RiskViewController {


    ///risk table, and the input fields
    @FXML private TableView<Risk> tableView;
    @FXML private TextField riskNameField;
    @FXML private TextField riskCostField;
    @FXML private TextField riskProbabilityField;

    //risk description pane
    @FXML private TitledPane riskPane;
    @FXML private TextArea summaryTextArea;

    //Popup alerts
    @FXML private final Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML private final Alert editConfirm = new Alert(Alert.AlertType.CONFIRMATION);


    ///Not working yet
    @FXML
    protected void enterPressed(KeyEvent e) {
        if(e.getCode() == KeyCode.ENTER) {
            addRisk();
        }
        deletePressed(e);
    }

    @FXML
    private void deletePressed(KeyEvent e) {
        if(e.getCode() == KeyCode.DELETE) {
            Risk selectedRisk = tableView.getSelectionModel().getSelectedItem();
            if( selectedRisk !=  null) {
                removeRisk();
            }
        }
    }

    @FXML
    protected void addRisk() {
        ObservableList<Risk> tableData = tableView.getItems();
        if (checkFields()) {
            tableData.add(new Risk(riskNameField.getText(), Double.parseDouble(riskCostField.getText()),
                    Double.parseDouble(riskProbabilityField.getText())));
            clearFields();
        }
    }

    @FXML
    protected void removeRisk() {
        Risk selectedRisk = tableView.getSelectionModel().getSelectedItem();
        if (selectedRisk == null) return;
        editConfirm.setHeaderText("Deleting risk");
        editConfirm.setContentText("Are you sure you want to delete the risk called " + "'" + selectedRisk.getName() + "'");
        if(editConfirm.showAndWait().get() == ButtonType.OK) {
            tableView.getItems().remove(selectedRisk);
            resetDescription();
        }
    }

    @FXML
    protected void editRisk() {
        Risk selectedRisk = tableView.getSelectionModel().getSelectedItem();

        if (selectedRisk != null) {

            editConfirm.setContentText("Are you sure you want to edit the risk called " + "'" + selectedRisk.getName() + "'");

            if(editConfirm.showAndWait().get() == ButtonType.OK) {
                tableView.getItems().remove(selectedRisk);
                addRisk();
                loadDescription();
            }
        } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("You haven't chosen a risk to edit");
            alert.show();
        }
    }

    @FXML
    protected void loadDescription() {
        try {
            Risk selectedRisk = tableView.getSelectionModel().getSelectedItem();
            riskPane.setText(selectedRisk.getName());
            summaryTextArea.setText(selectedRisk.getString());
        } catch (Exception e) { }
    }
    @FXML
    protected void resetDescription() {
        riskPane.setText("Risk Summary");
        summaryTextArea.setText("Choose a risk");
    }


    private boolean nameIsUnique() {
        for (Risk r:tableView.getItems()
        ) {
            if (r.getName().equals(riskNameField.getText())) {
                return false;
            }
        }
        return true;
    }


    //TODO Move checkFields and is Unique method into InputValidation class. Need getters for private fields.
    private boolean checkFields() {
        String alertMessage = "";
        int throwAlarm = 1;

        if (!InputValidation.isOnlyLetters(riskNameField)) {
            alertMessage += "Risk name field can only contain letters\n";
            throwAlarm = 0;
        }
        if (!nameIsUnique()) {
            alertMessage += "Risk name isn't unique\n";
            throwAlarm = 0;
        }

        if (!InputValidation.isDouble(riskCostField)) {
            alertMessage += "Risk cost field can only contain numbers\n";
            throwAlarm = 0;
        }

        if (!InputValidation.isDouble(riskProbabilityField)) {
            alertMessage += "Probability field can only contain numbers\n";
            throwAlarm = 0;
        }

        // checks if alarm needs to pop up
        if (throwAlarm == 0) {
            alert.setHeaderText("Error with input(s)..");
            alert.setContentText(alertMessage);
            alert.show();
        }
        return throwAlarm != 0;
    }

    private void clearFields() {
        riskNameField.setText("");
        riskCostField.setText("");
        riskProbabilityField.setText("");
    }

}
