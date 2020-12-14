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

    public int getProjectId() {
        return this.id;
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

    //Add CounterMeasure
    public void addCounterMeasure(int riskID, double probabilityImpact, double consequenceImpact, String description, boolean active) {
        Risk riskToCounter = null;
        for (Risk risk: getRiskTable().getRisks()) {
            if (risk.getID() == riskID) {
                riskToCounter = risk;
            }
        }
        assert riskToCounter != null;
        riskToCounter.addCounterMeasure(probabilityImpact, consequenceImpact, description, active );

    }
    //Countermeasure that is activate
    public void activateCounterMeasure(int riskID){

        Risk risk = (Risk) getRiskTable().getRisks().stream().filter(i -> i.getID() == riskID);
        risk.activateCounterMeasure(true);
    }
    //Removed countermeasure
    public void removeCounterMeasure(int riskID){
        Risk risk = (Risk) getRiskTable().getRisks().stream().filter(i -> i.getID() == riskID);
        getRiskTable().getRisks().remove(risk);
    }
}
