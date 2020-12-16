package model;
import java.io.Serializable;

public class Risk implements Serializable {

    //Datafield (variables)
    private String riskName;
    private double probability;
    private double consequence;
    private String description;
    private double priority;
    private double revisedProbability;
    private double revisedConsequence;
    private CounterMeasure cm;
    static int count = 0;
    int id;


    // Constructor
    public Risk(String description, double probability, double consequence, String riskName) {
        this.riskName = riskName;
        this.consequence = consequence;
        this.probability = probability;
        this.description = description;
        this.priority = this.consequence * this.probability;
        this.id = count++;
    }

    //Methods
    public void editRisk(String riskName, double probability, double consequence, String description) {

        this.description = description;
        this.probability = probability;
        this.consequence = consequence;
        this.riskName = riskName;
        this.priority = consequence * probability;
        updateRisk();
    }

    public void addCounterMeasure(double probabilityImpact, double consequenceImpact, String description, boolean active) {
        cm = new CounterMeasure(probabilityImpact, consequenceImpact, description, active);
        updateRisk();
    }

    public void activateCounterMeasure(boolean wantedState) {
        cm.activateCounterMeasure(wantedState);
        updateRisk();
    }

    public void removeCounterMeasure() {
        this.cm = null;
        this.revisedConsequence = Double.NaN;
        this.revisedProbability = Double.NaN;
        updateRisk();
    }

    public void updateRisk() {
        if (cm == null || !cm.isActive()) {
            this.priority = consequence * probability;
        }
        if (cm != null && !cm.isActive()) {
            this.revisedConsequence = cm.getConsequenceImpact() * this.consequence;
            this.revisedProbability = cm.getProbabilityImpact() * this.probability;
        }
        if (cm != null && cm.isActive()) {
            this.revisedConsequence = cm.getConsequenceImpact() * this.consequence;
            this.revisedProbability = cm.getProbabilityImpact() * this.probability;
            this.priority = revisedConsequence * revisedProbability;
        }
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

    public int getID() {
        return id;
    }
}