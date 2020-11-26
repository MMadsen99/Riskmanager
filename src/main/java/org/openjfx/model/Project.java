package org.openjfx.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Project {

   public ArrayList<Risk> getRisks() {
      return risks;
   }

   private static ArrayList<Risk> risks = new ArrayList<>();

   public void addRisk(Risk risk) {
      risks.add(risk);
   }

}
