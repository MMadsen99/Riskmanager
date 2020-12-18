package model;

import exceptions.NoProjectException;

import java.io.Serializable;
import java.util.ArrayList;

//mike
public class ProjectTable implements Serializable {


    //Data fields
    ArrayList<Project> projects = new ArrayList<>();

    //Constructor, sørger for at en ny session starter med mindst et projekt.
   

    //Skaber og tilfølger et nyt projekt til listen over projekter.
    public void createProject(String projectName) {
        getProjects().add(new Project(projectName));
    }

    //Sletter projekt
    public void deleteProject(int projectID) throws NoProjectException {
        getProjects().remove(getProject(projectID));
    }
    //Gør listen af projekter tom
    public void clearProjects() {
        this.projects = new ArrayList<>();
    }

    // Getters and Setters
    public ArrayList<Project> getProjects() {

        return projects;
    }

    public Project getProject(int projectID) throws NoProjectException {

        for (Project project:getProjects()) {
            if (project.getProjectId() == projectID) {
                return project;
            }
        } throw new NoProjectException("Project was not found in the project list");

    }
}
