package view;

import control.RiskManagerController;
import exceptions.NoProjectException;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.ProjectTable;
import model.RiskTable;

public interface Observer {

    public void updateProjects(ListView<ProjectsFX> projectsFXListView, ProjectTable projectTable) throws NoProjectException;

    public void updateRisks(RiskManagerController controller, RiskTable riskTable);
}
