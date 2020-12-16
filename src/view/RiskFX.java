package view;

import model.Risk;

public class RiskFX {

    int id;
    String name;
    double probability;
    double consequence;
    double priority;
    String revisedProbability;
    String revisedConsequence;
    String cmStatus;

    public String getRevisedProbability() {
        return revisedProbability;
    }

    public String getRevisedConsequence() {
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
            this.revisedProbability = String.valueOf(risk.getRevisedProbability());
            this.revisedConsequence = String.valueOf(risk.getRevisedConsequence());
            if (risk.getCm().isActive()){
                this.cmStatus = "Active";
            } else if (!risk.getCm().isActive()) {
                this.cmStatus = "Inactive";
            }
        }else{

            this.revisedConsequence = "";
            this.revisedProbability = "";


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
