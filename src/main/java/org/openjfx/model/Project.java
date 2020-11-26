package org.openjfx.model;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Project {

   private SimpleStringProperty projectName = new SimpleStringProperty();
   private static final ArrayList<Risk> risks = new ArrayList<>();


   public Project() {
      this("unnamed");
   }

   public Project(String projectName) {
      setProjectName(projectName);
   }

   public ArrayList<Risk> getRisks() {
      return risks;
   }

   public void addRisk(Risk risk) {
      risks.add(risk);
   }

   public void removeRisk(Risk r) {
      risks.remove(r);
   }

   public Project getProject() {
      return this;
   }

   public void setProjectName(String projectName) {
      this.projectName.set(projectName);
   }

   public String getProjectName() {
      return projectName.get();
   }
}
