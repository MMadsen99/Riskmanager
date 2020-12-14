package model;

import java.io.Serializable;

public class Risk implements Serializable {

    static int count = 0;

    int id;

    public int getID() {
        return id;
    }


    //Datafield (variables)
    private String riskName;
    private double probability;
    private double consequence;
    private String description;
    private double priority;
    private double revisedProbability;
    private double revisedConsequence;
    private CounterMeasure cm;


    // Constructor
    public Risk(String riskName, double probability, double consequence, String description) {
        this.riskName = riskName;
        this.consequence = consequence;
        this.probability = probability;
        this.description = description;
        this.priority = consequence * probability;
        this.id = count++;
    }

    //Methods
    public void addCounterMeasure(double probabilityImpact, double consequenceImpact, String description, boolean active) {
        cm = new CounterMeasure(probabilityImpact, consequenceImpact, description, active);
        if (cm.isActive()) {
            updatePriority();
        }
    }

    public void activateCounterMeasure(boolean wantedState) {
        cm.activeCounterMeasure(wantedState);
        if (cm.isActive()) {
            updatePriority();
        }
    }

    public void updatePriority() {
        if (cm == null) {
            return;
        } else {
            this.revisedConsequence = cm.getConsequenceImpact() * this.consequence;
            this.revisedProbability = cm.getProbabilityImpact() * this.probability;
            this.priority = revisedConsequence * revisedProbability;
        }
    }


    public void editRisk(String riskName, double probability, double consequence, String description) {

        this.description = description;
        this.probability = probability;
        this.consequence = consequence;
        this.riskName = riskName;
        this.priority = consequence * probability;
        updatePriority();
    }

    public void removeCounterMeasure() {
        this.cm = null;
    }


    // Getters and Setters
    public String getRiskName() {
        return riskName;
    }

    public String getDescription() {
        return description;
    }

    public double getConsequence() {
        return consequence;
    }

    public double getProbability() {
        return probability;
    }

    public double getPriority() {
        return priority;
    }

    public double getRevisedConsequence() {
        return revisedConsequence;
    }

    public double getRevisedProbability() {
        return revisedProbability;
    }

    public CounterMeasure getCm() {
        return cm;
    }


}



