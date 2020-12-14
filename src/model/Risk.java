package model;

public class Risk {


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
    public Risk(String name){
        this.riskName = riskName;
        this.consequence = consequence;
        this.priority = probability;
        this.description = description;
    }



    //Methods
    public void addCounterMeasure(double probabilityImpact, double consequenceImpact, String description, boolean active){
        cm =  new CounterMeasure(probabilityImpact,consequenceImpact,description,active);
    }

    public void activateCounterMeasure(boolean wantedState){


    }






  
}
