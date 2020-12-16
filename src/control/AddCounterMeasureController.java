/*
package control;

import exceptions.NoProjectException;
import exceptions.NoRiskException;

public class AddCounterMeasureController extends RiskManagerController {






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
