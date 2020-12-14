package view;

public class RiskFX {

    int id;
    String name;
    double probability;
    double cost;

    public RiskFX(int id, String name, double probability, double cost) {
        this.id = id;
        this.name = name;
        this.probability = probability;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
