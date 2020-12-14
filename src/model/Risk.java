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
    public Risk(String riskName, double probability, double consequence, String description){
        this.riskName = riskName;
        this.consequence = consequence;
        this.priority = probability;
        this.description = description;
    }



    //Methods





  
}
