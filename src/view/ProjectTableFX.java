package view;

import control.RiskManagerController;
import exceptions.NoProjectException;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.Project;
import model.ProjectTable;
import model.Risk;
import model.RiskTable;

import java.util.ArrayList;

public class ProjectTableFX implements Observer {

    ArrayList<ProjectsFX> projectsFXES = new ArrayList<>();

    @Override
    public void updateProjects(ListView<ProjectsFX> projectsFXListView, ProjectTable projectTable) throws NoProjectException {
        System.out.println("hello");
        ArrayList<Project> projects = projectTable.getProjects();
        updateProjects(projects);
        if(!(projectsFXListView == null)){
            System.out.println("RR");
            projectsFXListView.getItems().clear();
            projectsFXListView.getItems().addAll(projectsFXES);
        }

        projectsFXListView.getItems().forEach(i -> System.out.println(i.getName()));
    }
    private void updateProjects(ArrayList<Project> projects) {
        projectsFXES = new ArrayList<>();
        for (Project project: projects) {
            projectsFXES.add(new ProjectsFX(project.getProjectId(), project.getProjectName()));
        }
    }
    @Override
    public void updateRisks(RiskManagerController riskManagerController, RiskTable riskTable) {

    }
}
