package model;

import java.io.Serializable;

public class CounterMeasure implements Serializable {
 
  
    // DataFields ( Variables)
    private double probabilityImpact = 0;
    private double consequenceImpact = 0;
    private String description;
    private  boolean active = false;


    // Constructors
    // This constructor creates a counterMeasure and assigns its some specified values; 
    public CounterMeasure(double probabilityImpact, double consequenceImpact, String description, boolean active){
        this.active = active;
        this.consequenceImpact = consequenceImpact ;
        this.probabilityImpact = probabilityImpact;
        this.description = description;
    }

    // Methods
    // This method can set whether or not a counter measure should be active;
    public void activeCounterMeasure(boolean wantedState){
        this.active = wantedState;
    }

    // This method returns whether or not a counterMeasure is active;
    public boolean isActive(){
        return this.active;
    }

    // This method returns a counterMeasures probabilityImpact;
    public double getProbabilityImpact(){
        return this.probabilityImpact; 
    }

    // This method returns a counterMeasures ConsequenceImpact;
    public double getConsequenceImpact() {
        return consequenceImpact;
        
    }
    // This method returns a counterMeasures Description;
    public String getDescription() {
        return description;
    }
}

