package model;

import exceptions.NoRiskException;

import java.io.Serializable;
import java.util.ArrayList;

public class RiskTable implements Serializable {

    //Data fields
    ArrayList<Risk> risks = new ArrayList<>();



    public void addRisk(String riskName, double probability, double consequence, String description){
        getRisks().add(new Risk(riskName, probability, consequence, description));
    }

    public void addRisk(int riskID, String riskName, double probability, double consequence, String description){
        getRisks().add(new Risk(riskID, riskName, probability, consequence, description));
    }

    public void deleteRisk(int riskId) throws NoRiskException {
        getRisks().remove(getRisk(riskId));
    }

    public ArrayList<Risk> getRisks(){
        return risks;
    }
    public Risk getRisk(int riskId) throws NoRiskException {

        for (Risk risk:getRisks()) {
            if (risk.getID() == riskId) {
                return risk;
            }
        } throw new NoRiskException("Risk not found");
    }
}