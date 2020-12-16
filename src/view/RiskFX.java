package view;

public class RiskFX {

    int id;
    String name;
    double probability;
    double consequence;
    double priority;


    public RiskFX(int id, String name, double probability, double consequence, double priority) {
        this.id = id;
        this.name = name;
        this.probability = probability;
        this.consequence = consequence;
        this.priority = priority;
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
