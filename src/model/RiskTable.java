package model;

import exceptions.NoRiskException;

import java.util.ArrayList;

public class RiskTable {
    ArrayList<Risk> risks = new ArrayList<>();


    public ArrayList<Risk> getRisks(){
        return risks;
    }

    public void addRisk(String riskName, double probability, double consequence, String description){
        getRisks().add(new Risk(riskName, probability, consequence, description));
    }

    public void deleteRisk(int riskId) throws NoRiskException {
        getRisks().remove(getRisk(riskId));
    }

    public Risk getRisk(int riskId) throws NoRiskException {

        for (Risk risk:getRisks()) {
            if (risk.getID() == riskId) {
                return risk;
            }
        } throw new NoRiskException("Project not found");
    }
}