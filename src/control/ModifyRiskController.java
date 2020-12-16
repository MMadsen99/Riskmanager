package control;

import exceptions.NoProjectException;
import exceptions.NoRiskException;

public class ModifyRiskController {
    public void editRisk(int riskID,String riskName, double probability, double consequence, String description) throws NoProjectException, NoRiskException {
        this.openProject.editRisk(riskID,riskName,probability,consequence,description);
        this.riskTableFX.Update(this);
    }


}
