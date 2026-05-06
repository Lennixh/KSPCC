package kspcc;


public class Antenna {
    
    private String name;
    
    private double strength;
    private int unit;
    
    private float compatabilityExponent;
    
    public Antenna(String name, double strength){
        this(name, strength, 1);
    }
    public Antenna(String name, double strength, int unit){
        this(name, strength, unit, 0.75f);
    }
    
    public Antenna(String name, double strength, int unit, float compatabilityExponent){
        this.name = name;
        this.strength = strength;
        this.unit = unit;
        this.compatabilityExponent = compatabilityExponent;
    }
    
    public String getName(){
        return name;
    }
    
    public double getStrength(){
        //in Megameter, just what i chose lol
        return strength * Math.pow(1000, 2-unit);
    }
    
    public int getUnit(){
        return unit;
    }
    
    public float getCompatabilityExponent(){
        return compatabilityExponent;
    }
    
    public boolean isCombinable(){
        return compatabilityExponent > 0;
    }

            
}