package model;

import exceptions.NoProjectException;
import exceptions.NoRiskException;

import java.util.ArrayList;

public class RiskTable {
    ArrayList<Risk> risks = new ArrayList<>();


    public RiskTable(){
        risks.add(new Risk("RiskName"));
    }

    public ArrayList<Risk> getRisks(){
        return risks;
    }

    public void addRisk(String name, double probability, double consequence, String riskName){
        getRisks().add(new Risk(riskName));
    }

    public void deleteRisk(int riskId) throws NoRiskException {
        getRisks().remove(getRisk(riskId));
    }

    public Risk getRisk(int riskId) throws NoRiskException {

        for (Risk risk:getRisks()) {
            if (risk.getid() == riskId) {
                return risk;
            }
        } throw new NoRiskException("Project not found");
    }
}