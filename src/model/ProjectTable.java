package model;

import exceptions.NoProjectException;

import java.io.Serializable;
import java.util.ArrayList;

//mike
public class ProjectTable implements Serializable {

    ArrayList<Project> projects = new ArrayList<>();

    public ProjectTable() {
        projects.add(new Project("unnamed"));
    }

    public int createProject(String projectName) {
        getProjects().add(new Project(projectName));
        return  getProjects().get(projects.size()-1).getId();
    }
    public void deleteProject(int projectID) throws NoProjectException {
        getProjects().remove(getProject(projectID));
    }

    public void clearProjects() {
        this.projects = new ArrayList<>();
    }

    public ArrayList<Project> getProjects() {

        return projects;
    }
    public Project getProject(int projectID) throws NoProjectException {

        for (Project project:getProjects()) {
            if (project.getId() == projectID) {
                return project;
            }
        } throw new NoProjectException("Project was not found in the project list");

    }

}
