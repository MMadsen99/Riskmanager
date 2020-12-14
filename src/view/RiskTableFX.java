package view;

import control.RiskManagerController;
import exceptions.NoProjectException;
import model.Project;
import model.Risk;

import java.util.ArrayList;

public class RiskTableFX implements Observer{

    ArrayList<ProjectsFX> projectsFXES = new ArrayList<>();

    ArrayList<Risk> currentProjectRisks = new ArrayList<>();

    public ArrayList<ProjectsFX> getProjectsFXES() {
        return projectsFXES;
    }

    public ArrayList<Risk> getCurrentProjectRisks() {
        return currentProjectRisks;
    }

    @Override
    public void Update(RiskManagerController controller) throws NoProjectException {
        projectsFXES = new ArrayList<>();
        currentProjectRisks = new ArrayList<>();

        ArrayList<Project> projects = controller.getProjectTable().getProjects();

        updateProjects(projects);
        updateRisks(controller);
    }

    private void updateRisks(RiskManagerController controller) {
        try {
            currentProjectRisks = controller.getOpenProject().getRiskTable().getRisks();
        } catch (Exception e) {
            System.out.println("no current project");
        }
    }

    private void updateProjects(ArrayList<Project> projects) {
        for (Project project: projects) {
            projectsFXES.add(new ProjectsFX(project.getProjectId(), project.getProjectName()));
        }
    }
}
