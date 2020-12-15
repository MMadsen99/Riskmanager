package view;

import model.Risk;

public class RiskFX {

    int id;
    String name;
    double probability;
    double consequence;
    double priority;
    double revisedProbability;
    double revisedConsequence;
    String cmStatus;

    public double getRevisedProbability() {
        return revisedProbability;
    }

    public double getRevisedConsequence() {
        return revisedConsequence;
    }

    public String getCmStatus() {
        return cmStatus;
    }

    public RiskFX(Risk risk) {

        this.id = risk.getID();
        this.name = risk.getRiskName();
        this.probability = risk.getProbability();
        this.consequence = risk.getConsequence();
        this.priority = risk.getPriority();

        if (risk.getCm() != null) {
            this.revisedProbability = risk.getRevisedProbability();
            this.revisedConsequence = risk.getRevisedConsequence();
            if (risk.getCm().isActive()){
                this.cmStatus = "Active";
            } else {
                this.cmStatus = "Inactive";
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getProbability() {
        return probability;
    }

    public double getConsequence() {
        return consequence;
    }

    public double getPriority() {
        return priority;
    }
}
