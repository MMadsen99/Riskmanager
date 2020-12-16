/*
package control;

import exceptions.NoProjectException;
import exceptions.NoRiskException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class AddCounterMeasureController extends RiskManagerController {

    @FXML
    private TextField addCountermeasureCon;

    @FXML
    private Slider addCountermeasureProSlider;

    @FXML
    private Button addCountermeasureAddButton;

    @FXML
    private Button addCountermeasureCancelButton;

    @FXML
    private TextField addCountermeasureName;

    @FXML
    private TextField addCountermeasureDes;





    public void addCounterMeasure(int riskID, double probabilityImpact, double consequenceImpact, String description, boolean active) throws NoProjectException, NoRiskException {
        getOpenProject().addCounterMeasure(riskID, probabilityImpact, consequenceImpact, description, active);
        this.riskTableFX.Update(this);
    }
    public void removeCounterMeasure(int riskID) throws NoProjectException {
        getOpenProject().removeCounterMeasure(riskID);
        this.riskTableFX.Update(this);
    }

    public void activateCounterMeasure(int riskID, boolean wantedState) throws NoRiskException, NoProjectException {
        getOpenProject().getRiskTable().getRisk(riskID).activateCounterMeasure(wantedState);
        this.riskTableFX.Update(this);
    }




}
*/
