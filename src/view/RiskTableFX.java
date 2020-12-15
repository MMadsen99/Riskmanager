package view;

import control.RiskManagerController;
import exceptions.NoProjectException;
import model.Project;
import model.Risk;

import java.util.ArrayList;

public class RiskTableFX implements Observer{


    ArrayList<Risk> currentProjectRisks = new ArrayList<>();


    @Override
    public void Update(RiskManagerController controller) throws NoProjectException {
        currentProjectRisks = new ArrayList<>();
        updateRisks(controller);
    }

    private void updateRisks(RiskManagerController controller) {
        try {
            currentProjectRisks = controller.getOpenProject().getRiskTable().getRisks();
        } catch (Exception e) {
            System.out.println("no current project");
        }
    }
}
