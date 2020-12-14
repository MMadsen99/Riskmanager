package control;
import exceptions.NoProjectException;
import model.Project;
import model.ProjectTable;
import view.RiskTableFX;

import java.util.ArrayList;

public class RiskManagerController {

    Project openProject = new Project("unnamed");
    ProjectTable projectTable = new ProjectTable();

    RiskTableFX riskTableFX = new RiskTableFX();
    public ProjectTable getProjectTable() {
        return projectTable;
    }

    public void setProjectTable(ProjectTable projectTable) {
        this.projectTable = projectTable;
    }

    public void createProject(String projectName) throws NoProjectException {
        projectTable.createProject(projectName);
        this.riskTableFX.Update(this);
    }

    public void loadProjects(ArrayList<Project> projects) {
        projectTable = new ProjectTable();
        for (Project project: projects) {
            projectTable.createProject(project.getProjectName());
        }
    }

    public void deleteProject(int projectID) throws NoProjectException {
        this.projectTable.deleteProject(projectID);
        this.riskTableFX.Update(this);
    }

    public void setOpenProject(int projectID) throws NoProjectException {
        try {
            this.openProject = projectTable.getProject(projectID);
        } catch (Exception e) {

        }
        this.riskTableFX.Update(this);
    }

    public Project getOpenProject() {
        return this.openProject;
    }
    public void addRisk(String name, double probability, double cost, String description) throws NoProjectException {
        this.openProject.addRisk(name, probability, cost, description);
        this.riskTableFX.Update(this);
    }

    public static void main(String[] args) throws NoProjectException {
        RiskManagerController riskManagerController = new RiskManagerController();
        riskManagerController.createProject("Mike");
        riskManagerController.setOpenProject(2);
        System.out.println(riskManagerController.getOpenProject().getProjectName());

        riskManagerController.addRisk("lose moneny", 2.0, 2.9, "fuck");

        riskManagerController.riskTableFX.getProjectsFXES().forEach(i -> System.out.println(i.getId() + " " + i.getName()));
        riskManagerController.addRisk("project two risk", 21, 23, "oh im here");
        riskManagerController.setOpenProject(2);
        riskManagerController.riskTableFX.getCurrentProjectRisks().forEach(i -> System.out.println(i.getID() + " " +i.getRiskName()));
    }
}
