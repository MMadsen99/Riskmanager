package view;

import control.RiskManagerController;
import exceptions.NoProjectException;
import model.Project;

import java.util.ArrayList;

public class ProjectTableFX implements Observer {

    ArrayList<ProjectsFX> projectsFXES = new ArrayList<>();
    @Override
    public void Update(RiskManagerController controller) throws NoProjectException {
        System.out.println("hello");
        ArrayList<Project> projects = controller.getProjectTable().getProjects();
        updateProjects(projects);
        if(!(controller.projectFXListView == null)){
            System.out.println("RR");
            controller.projectFXListView.getItems().clear();
            controller.projectFXListView.getItems().addAll(projectsFXES);
        }

    }

    private void updateProjects(ArrayList<Project> projects) {
         projectsFXES = new ArrayList<>();
        for (Project project: projects) {
            projectsFXES.add(new ProjectsFX(project.getProjectId(), project.getProjectName()));
        }
    }

}
