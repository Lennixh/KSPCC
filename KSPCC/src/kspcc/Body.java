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
    
    private ArrayList<Body> children;
    
    public Body(){
        this("",0,0,0,0,0,null);
    }
    
    public Body(String name, double SMA, float E, double inc, double AoP, double LoA, Body parent){
        this.name = name;
        this.SMA = SMA;
        this.E = E;
        this.AP = SMA*(1+E);
        this.PE = SMA*(1-E);
        this.inc = inc;
        this.AoP = AoP;
        this.LoA = LoA;
        this.parent = parent;
        this.children = new ArrayList<>();
        if (parent != null) {
            parent.addChild(this);
        }
    }
    
    public void printAllChildren(){
        for (Body b : children) {
            System.out.println(b.getName());
            b.printAllChildren();
        }
    }
    public void printDirectChildren(){
        for (Body b : children) {
            System.out.println(b.getName());
        }
    }
    public boolean hasChildren(){
        return !children.isEmpty();
    }
    public boolean hasParent(){
        return parent == null;
    }
    
    public ArrayList<Body> getDirectChildren(){
        return children;
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
    
    public void addChild(Body child){
        children.add(child);
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
