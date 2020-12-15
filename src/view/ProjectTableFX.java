package view;

import control.RiskManagerController;
import exceptions.NoProjectException;
import model.Project;

import java.util.ArrayList;

public class ProjectTableFX implements Observer {


    public ArrayList<ProjectsFX> getProjectsFXES() {
        return projectsFXES;
    }

    ArrayList<ProjectsFX> projectsFXES = new ArrayList<>();
    @Override
    public void Update(RiskManagerController controller) throws NoProjectException {

        ArrayList<Project> projects = controller.getProjectTable().getProjects();
        updateProjects(projects);
    }

    private void updateProjects(ArrayList<Project> projects) {
         projectsFXES = new ArrayList<>();
        for (Project project: projects) {
            projectsFXES.add(new ProjectsFX(project.getProjectId(), project.getProjectName()));
        }
    }

}
