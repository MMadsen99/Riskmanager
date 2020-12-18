package model;
import java.io.Serializable;

public class Risk implements Serializable {

    //Datafield (variables)
    private final String name;
    private final double probability;
    private final double consequence;
    private final String description;
    private double priority;
    private double revisedProbability;
    private double revisedConsequence;
    private CounterMeasure CounterMeasure;
    static int count = 0;
    int id;


    // Constructor
    public Risk(String name, double probability, double consequence, String description) {
        this.name = name;
        this.consequence = consequence;
        this.probability = probability;
        this.description = description;
        this.priority = this.consequence * this.probability;
        this.id = count++;
    }
    public Risk(int riskID, String name, double probability, double consequence, String description) {
        this.name = name;
        this.consequence = consequence;
        this.probability = probability;
        this.description = description;
        this.priority = this.consequence * this.probability;
        this.id = riskID;
    }

    //Methods
    /*public void editRisk(String riskName, double probability, double consequence, String description) {

        this.description = description;
        this.probability = probability;
        this.consequence = consequence;
        this.riskName = riskName;
        this.priority = consequence * probability;
        updateRisk();
        System.out.println("Risk: " + this.getID() + " has been modified");
        System.out.println("Priority: " + this.getPriority());
    }*/

    public void addCounterMeasure(double probabilityImpact, double consequenceImpact, String description, boolean active) {
        CounterMeasure = new CounterMeasure(probabilityImpact, consequenceImpact, description, active);
        updateRisk();
    }

    public void activateCounterMeasure(boolean wantedState) {
        CounterMeasure.activateCounterMeasure(wantedState);
        updateRisk();
    }
    public void removeCounterMeasure() {
        this.CounterMeasure = null;
        this.revisedConsequence = Double.NaN;
        this.revisedProbability = Double.NaN;
        updateRisk();
    }

    public void updateRisk() {
        if (CounterMeasure == null || !CounterMeasure.isActive()) {
            this.priority = consequence * probability;
        }
        if (CounterMeasure != null && !CounterMeasure.isActive()) {
            this.revisedConsequence = CounterMeasure.getConsequenceImpact() * this.consequence;
            this.revisedProbability = CounterMeasure.getProbabilityImpact() * this.probability;

            System.out.println(2);
        }
        if (CounterMeasure != null && CounterMeasure.isActive()) {
            this.revisedConsequence = CounterMeasure.getConsequenceImpact() * this.consequence;
            this.revisedProbability = CounterMeasure.getProbabilityImpact() * this.probability;
            this.priority = revisedConsequence * revisedProbability;
            System.out.println(3);
        }
    }


    // Getters and Setters
    public String getName() {
        return name;
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

    public CounterMeasure getCounterMeasure() {
        return CounterMeasure;
    }

    public int getID() {
        return id;
    }
}