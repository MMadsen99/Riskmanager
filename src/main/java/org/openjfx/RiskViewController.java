package org.openjfx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.openjfx.inputvalidation.InputValidation;
import org.openjfx.model.Project;
import org.openjfx.model.Risk;
import org.openjfx.persistence.LoaderSaver;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RiskViewController implements Initializable {

    LoaderSaver loaderSaver = new LoaderSaver();
    Project project;

    ///risk table, and the input fields
    @FXML private TableView<Risk> tableView;
    @FXML private TextField riskNameField;
    @FXML private TextField riskCostField;
    @FXML private TextField riskProbabilityField;
    //risk description pane
    @FXML private TitledPane riskPane;
    @FXML private TextArea summaryTextArea;

    @FXML private Label projectNameLabel;

    //Popup alerts
    @FXML private final Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML private final Alert editConfirm = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML protected void saveProject() throws IOException {
        loaderSaver.saveProject(this);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loaderSaver.loadProject();
        loaderSaver.getProject().getRisks().forEach(i-> tableView.getItems().add(i));
        setProjectNameLabel(loaderSaver.getLoadedProjectName());
    }
    @FXML
    protected void enterPressed(KeyEvent e) {
        if(e.getCode() == KeyCode.ENTER) {
            addRisk();
        }
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
        if (InputValidation.checkFields(this)) {
            tableData.add(new Risk(riskNameField.getText()));

            clearFields();
        } else {

            if (InputValidation.checkFields(this)) {
                tableData.add(new Risk(riskNameField.getText(), Double.parseDouble(riskCostField.getText()),
                        Double.parseDouble(riskProbabilityField.getText())));
                clearFields();
            }
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
        Risk selectedRisk = tableView.getSelectionModel().getSelectedItem();

        if (selectedRisk == null) return;
        riskPane.setText(selectedRisk.getName());
        summaryTextArea.setText(selectedRisk.getString());

    }

    @FXML
    protected void resetDescription() {
        riskPane.setText("Risk Summary");
        summaryTextArea.setText("Choose a risk");
    }

    public TextField getRiskNameField() {
        return riskNameField;
    }

    public TextField getRiskCostField() {
        return riskCostField;
    }

    public TextField getRiskProbabilityField() {
        return riskProbabilityField;
    }

    public TableView<Risk> getTableView() {
        return tableView;
    }

    public Alert getAlert() {
        return alert;
    }


    private void clearFields() {
        riskNameField.setText("");
        riskCostField.setText("");
        riskProbabilityField.setText("");
    }

    public void setProjectNameLabel(String projectName) {

        projectNameLabel.setText(projectName);

    }
}
