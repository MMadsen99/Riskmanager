package view;

import model.Risk;

public class RiskFX {

    int id;
    String name;
    String description;

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

    public String getDescription() {
        return description;
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
        this.description = risk.getDescription();

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

    @Override
    public String toString() {

        return "RiskID:" + this.id + "\n" +
                "Risk Name" + this.name + "\n" +
                "Risk Description " + this.description + "\n" +
                "Risk Probability " + this.probability + "\n" +
                "Risk Consequence " + this.consequence + "\n" +
                "Risk Priority" + this.priority;
    }
}
