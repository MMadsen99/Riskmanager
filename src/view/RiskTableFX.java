package view;

import control.RiskManagerController;
import exceptions.NoProjectException;
import model.Project;
import model.Risk;

import java.util.ArrayList;

public class RiskTableFX implements Observer{


    ArrayList<RiskFX> currentProjectRisks = new ArrayList<>();


    @Override
    public void Update(RiskManagerController controller) throws NoProjectException {
        currentProjectRisks = new ArrayList<>();
        updateRisks(controller);
        controller.riskFXTableView.getItems().clear();
        controller.riskFXTableView.getItems().addAll(currentProjectRisks);
    }

    private void updateRisks(RiskManagerController controller) {
        try {
            controller.getOpenProject().getRiskTable().getRisks().forEach(risk -> currentProjectRisks.add(new RiskFX(risk)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
