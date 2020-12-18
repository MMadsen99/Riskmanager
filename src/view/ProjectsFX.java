package view;

import model.Project;
import model.Risk;

public class ProjectsFX {

    int id;
    String name;

    public ProjectsFX(Project project) {
        this.id = project.getProjectId();
        this.name = project.getProjectName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
