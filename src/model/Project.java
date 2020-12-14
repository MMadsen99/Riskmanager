package model;

public class Project {

    static int count= 0;
    public int id;


    //Create riskTable
    RiskTable rt = new RiskTable();

    //Data field for projectName (String)
    String projectName;

    //Constructor for projectName
    public Project(String projectName) {
        this.id = count++;
        this.projectName = projectName;
    }
    //Set projectName
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
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
    public RiskTable getRiskTable(){

        return rt;


    }
    //Countermeasure that is activate
    public void activateCounterMeasure(int riskID){

        Risk risk = (Risk) getRiskTable().getRisks().stream().filter(i -> i.getID() == riskID);


    }
    //Removed countermeasure
    public void removeCounterMeasure(){

    }

    public int getProjectId() {
        return this.id;
    }
}
