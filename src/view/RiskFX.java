package view;

import model.CounterMeasure;
import model.Risk;

public class RiskFX {

    private final CounterMeasure CounterMeaure;
    int ID;
    String name;
    String description;

    double probability;
    double consequence;
    double priority;
    String revisedProbability;
    String revisedConsequence;
    String CounterMeasureStatus;

    public String getDescription() {
        return description;
    }


    public RiskFX(Risk risk) {

        this.ID = risk.getID();
        this.name = risk.getName();
        this.probability = risk.getProbability();
        this.consequence = risk.getConsequence();
        this.priority = risk.getPriority();
        this.description = risk.getDescription();
        this.CounterMeaure = risk.getCounterMeasure();

        if (risk.getCounterMeasure() != null) {
            this.revisedProbability = String.valueOf(risk.getRevisedProbability());
            this.revisedConsequence = String.valueOf(risk.getRevisedConsequence());
            if (risk.getCounterMeasure().isActive()){
                this.CounterMeasureStatus = "Active";
            } else if (!risk.getCounterMeasure().isActive()) {
                this.CounterMeasureStatus = "Inactive";
            }
        }else{

            this.revisedConsequence = "";
            this.revisedProbability = "";


        }
    }

    public int getID() {
        return ID;
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

    public CounterMeasure getGetCounterMeasure() {
        return CounterMeaure;
    }

    @Override
    public String toString() {

        return "RiskID:" + this.ID + "\n" +
                "Risk Name" + this.name + "\n" +
                "Risk Description " + this.description + "\n" +
                "Risk Probability " + this.probability + "\n" +
                "Risk Consequence " + this.consequence + "\n" +
                "Risk Priority" + this.priority;
    }
}
