package kspcc;

import java.util.ArrayList;

public class Body {
    
    private String name;
    private double SMA;
    private double AP;
    private double PE;
    private double AoP;
    private double LoA;
    private float E;
    private double inc;
    
    private Body parent;
    
    public Body(){
        this("",0,0,0,0,0,null);
    }
    
    public Body(String name, double SMA, float E, double inc, double LoA, double AoP, Body parent){
        this.name = name;
        this.SMA = SMA;
        this.E = E;
        this.AP = SMA*(1+E);
        this.PE = SMA*(1-E);
        this.inc = inc;
        this.AoP = AoP;
        this.LoA = LoA;
        this.parent = parent;
    } 
    
    public boolean hasParent(){
        return parent == null;
    }
    
    public String getName(){
        return name;
    }
    public double getSMA(){
        return SMA;
    }
    public double getSMB(){
        return SMA*Math.sqrt(1-(E*E));
    }
    public double getAP(){
        return AP;
    }
    public double getPE(){
        return PE;
    }
    public float getE(){
        return E;
    }
    public double getAoP(){
        return AoP;
    }
    public double getLoA(){
        return LoA;
    }
    public double getInc(){
        return inc;
    }
    
    public Body getParent(){
        return parent;
    }
    
    public void setName(String newName){
        name = newName;
    }
    public void setSMA(double val){
        SMA = val;
    }
    public void setE(float val){
        E = val;
    }
    public void setAoP(double val){
        AoP = val;
    }
    public void setLoA(double val){
        LoA = val;
    }
    public void setInc(double val){
        inc = val;
    }
}
