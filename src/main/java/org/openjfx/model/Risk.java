package org.openjfx.model;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Risk {

    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleDoubleProperty cost = new SimpleDoubleProperty();
    private final SimpleDoubleProperty probability = new SimpleDoubleProperty();
    private final SimpleDoubleProperty priority = new SimpleDoubleProperty();


    public Risk() {
        this("", 0.0, 0.0);
    }

    public Risk(String riskName, Double costValue, Double probabilityValue) {
        setName(riskName);
        setCost(costValue);
        setProbability(probabilityValue);
        setPriority();
    }

    private void setProbability(Double probabilityValue) {
        probability.set(probabilityValue);
    }
    private Double getProbability() {
        return probability.get();
    }

    private void setCost(Double costValue) {
        cost.set(costValue);
    }

    private void setName(String riskName) {
        name.set(riskName);
    }

    public String getName() {
        return name.get();
    }


    public double getCost() {
        return cost.get();
    }

    public void setPriority() {
        priority.set(getCost() * getProbability());
    }

    public Double getPriority() {
        return priority.get();
    }


}
