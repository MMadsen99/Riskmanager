package model;

public class Project {


    //Create riskTable
    RiskTable rt = new RiskTable();

    //Data field for projectName (String)
    String projectName;

    //Constructor for projectName
    public Project(String projectName) {
        this.projectName = projectName;
    }
    //Set projectName
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    //Add CounterMeasure
    public void addCounterMeasure(double probabilityImpact, double consequenceImpact, String description, boolean active) {


    }
    //Add risk
    public void addRisk(String riskName, double probability, double consequence, String description){

        rt.addRisk(riskName, probability, consequence, description);
    }

    //Delete risk
    public void deleteRisk(){

    }

    //Edit risk
    public void editRisk(String riskName, double probability, double consequence, String description){

    }
    //Countermeasure that is active
    public void activeCounterMeasure(){


    }
    //Removed countermeasure
    public void removeCounterMeasure(){


    }


}
