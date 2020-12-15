package view;

import control.RiskManagerController;
import exceptions.NoProjectException;
import model.Project;
import model.Risk;

import java.util.ArrayList;

public class ProjectTableFX implements Observer {
    ArrayList<ProjectsFX> projectsFXES = new ArrayList<>();

    public ArrayList<ProjectsFX> getProjectsFXES() {
        return projectsFXES;
    }


    @Override
    public void Update(RiskManagerController controller) throws NoProjectException {

        ArrayList<Project> projects = controller.getProjectTable().getProjects();
        updateProjects(projects);
    }

    private void updateProjects(ArrayList<Project> projects) {
        ArrayList<ProjectsFX> projectsFXES = new ArrayList<>();
        for (Project project: projects) {
            projectsFXES.add(new ProjectsFX(project.getProjectId(), project.getProjectName()));
        }
    }

}