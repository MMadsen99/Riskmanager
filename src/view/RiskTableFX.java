package view;

import control.RiskManagerController;
import exceptions.NoProjectException;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.ProjectTable;
import model.RiskTable;

import java.util.ArrayList;

public class RiskTableFX implements Observer {


    ArrayList<RiskFX> currentProjectRisks = new ArrayList<>();

    @Override
    public void updateProjects(ListView<ProjectsFX> projectsFXListView, ProjectTable projectTable) throws NoProjectException {

    }
    @Override
    public void updateRisks(RiskManagerController controller, RiskTable riskTable) {
        currentProjectRisks = new ArrayList<>();
        updateRisks(controller);

        if (!(controller.riskFXTableView == null)) {
            controller.riskFXTableView.getItems().clear();
            controller.riskFXTableView.getItems().addAll(currentProjectRisks);
        }
    }

        public void updateRisks(RiskManagerController controller){
            try {
                controller.getOpenProject().getRiskTable().getRisks().forEach(risk -> currentProjectRisks.add(new RiskFX(risk)));
            } catch (Exception e) {
                System.out.println("no current project");
            }
        }
    }

