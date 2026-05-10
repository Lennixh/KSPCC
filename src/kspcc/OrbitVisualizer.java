package kspcc;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class OrbitVisualizer extends JPanel {

    private BG master;
    
    private Dimension d;
    
    public double scale;
    
    private ArrayList<String> units;
    
    public ArrayList<Body> orbits;
    public ArrayList<Polygon> orbitShapes;
    public ArrayList<Color> orbitColors;
    
    public void init(Dimension d, Body b1, Body b2, BG master){
        this.master = master;
        this.d = d;
        this.setPreferredSize(d);
        this.scale = 1;
        orbits = new ArrayList<>();
        orbits.add(b1);
        orbits.add(b2);
        orbitShapes = new ArrayList<>();
        orbitColors = new ArrayList<>();
        orbitColors.add(master.color1);
        orbitColors.add(master.color2);
        units = new ArrayList<>();
        units.add("Tm");
        units.add("Gm");
        units.add("Mm");
        units.add("Km");
        units.add("m");
    }
    
    public void paint(Graphics g) {
        

        orbitShapes.clear();
        orbitColors.clear();
        orbitColors.add(master.color1);
        orbitColors.add(master.color2);
        
        scale = Math.min(0.5d/(orbits.get(0).getSMA()), 0.5d/(orbits.get(1).getSMA()));
        
        if (orbits.get(0).getParent() == orbits.get(1)) {
            orbits.get(1).setSMA(0);
            scale = 0.5d/orbits.get(0).getSMA();
        } 
        if (orbits.get(1).getParent() == orbits.get(0)){
            orbits.get(0).setSMA(0);
            scale = 0.5d/orbits.get(1).getSMA();
        }

        for (Body b : orbits) {
            generateEllipse(b.getSMA(), b.getSMB(), Math.toRadians(b.getAoP()), Math.toRadians(b.getLoA()), Math.toRadians(b.getInc()));
        }
        
        super.paintComponent(g);
        
        g.setColor(Color.GRAY);
        g.drawLine(d.width/2, 0, d.width/2, d.height);
        g.drawLine(0,d.height/2, d.width, d.height/2);

        for (int i = 0; i < orbitShapes.size(); i++) {
            g.setColor(orbitColors.get(i));
            g.drawPolygon(orbitShapes.get(i));
        }
    }
    
    public void addOrbit(Body b){
        orbits.add(b);
    }
    

    public void generateEllipse(double a, double b, double AoP, double LoA, double inc){
        
        a=0.5d*a*d.width*scale;
        b=0.5d*b*d.width*scale;
        
        
        final double cosB = Math.cos(AoP+LoA);
        final double sinB = Math.sin(AoP+LoA);
        final double cosA = Math.cos(LoA);
        final double sinA = Math.sin(LoA);
        final double cosNA = Math.cos(-LoA);
        final double sinNA = Math.sin(-LoA);
        final double cosInc = Math.cos(inc);
        
        
        final double dx = (((cosB*cosNA - sinB*sinNA)*a*cosA - (cosB*sinNA + sinB*cosNA)*a*sinA*cosInc)/a)*Math.sqrt(a*a-b*b);
        final double dy = (((cosB*cosNA - sinB*sinNA)*a*sinA + (cosB*sinNA + sinB*cosNA)*a*cosA*cosInc)/a)*Math.sqrt(a*a-b*b);
        double cosT, sinT;
        
        Polygon pNew = new Polygon();
        for (double i = 0; i < 2*Math.PI; i+=Math.PI/512) {
            cosT = Math.cos(i);
            sinT = Math.sin(i);
            pNew.addPoint(
                    (int) Math.round(((cosT*cosB*a - sinT*sinB*b)*cosNA - (cosT*sinB*a + sinT*cosB*b)*sinNA)*cosA 
                                   - ((cosT*cosB*a - sinT*sinB*b)*sinNA + (cosT*sinB*a + sinT*cosB*b)*cosNA)*sinA*cosInc 
                                   + d.width/2 + dx),
                    
                    (int) Math.round(-((cosT*cosB*a - sinT*sinB*b)*cosNA - (cosT*sinB*a + sinT*cosB*b)*sinNA)*sinA 
                                   - ((cosT*cosB*a - sinT*sinB*b)*sinNA + (cosT*sinB*a + sinT*cosB*b)*cosNA)*cosA*cosInc
                                   + d.height/2 - dy)
            );
        }
        orbitShapes.add(pNew);
    }
    
    public double[] getDists(){ 
        
        final double a0 = orbits.get(0).getSMA();
        final double b0 = orbits.get(0).getSMB();
        final double c0 = Math.sqrt(a0*a0-b0*b0);
        final double sinAP0 = Math.sin(Math.toRadians(orbits.get(0).getAoP()));
        final double cosAP0 = Math.cos(Math.toRadians(orbits.get(0).getAoP()));
        final double sinB0 = Math.sin(Math.toRadians(orbits.get(0).getAoP() + orbits.get(0).getLoA()));
        final double cosB0 = Math.cos(Math.toRadians(orbits.get(0).getAoP() + orbits.get(0).getLoA()));
        final double sinA0 = Math.sin(Math.toRadians(orbits.get(0).getLoA()));
        final double cosA0 = Math.cos(Math.toRadians(orbits.get(0).getLoA()));
        final double sinNA0 = Math.sin(-Math.toRadians(orbits.get(0).getLoA()));
        final double cosNA0 = Math.cos(-Math.toRadians(orbits.get(0).getLoA()));
        final double sinInc0 = Math.sin(Math.toRadians(orbits.get(0).getInc()));
        final double cosInc0 = Math.cos(Math.toRadians(orbits.get(0).getInc()));
        double x0, y0, z0, cosT0, sinT0;
        
        final double a1 = orbits.get(1).getSMA();
        final double b1 = orbits.get(1).getSMB();
        final double c1 = Math.sqrt(a1*a1-b1*b1);
        final double sinAP1 = Math.sin(Math.toRadians(orbits.get(1).getAoP()));
        final double cosAP1 = Math.cos(Math.toRadians(orbits.get(1).getAoP()));
        final double sinB1 = Math.sin(Math.toRadians(orbits.get(1).getAoP() + orbits.get(1).getLoA()));
        final double cosB1 = Math.cos(Math.toRadians(orbits.get(1).getAoP() + orbits.get(1).getLoA()));
        final double sinA1 = Math.sin(Math.toRadians(orbits.get(1).getLoA()));
        final double cosA1 = Math.cos(Math.toRadians(orbits.get(1).getLoA()));
        final double sinNA1 = Math.sin(Math.toRadians(-orbits.get(1).getLoA()));
        final double cosNA1 = Math.cos(Math.toRadians(-orbits.get(1).getLoA()));
        final double sinInc1 = Math.sin(Math.toRadians(orbits.get(1).getInc()));
        final double cosInc1 = Math.cos(Math.toRadians(orbits.get(1).getInc()));
        double x1, y1, z1, cosT1, sinT1;
        
        double maxDist = 0;
        double minDist = a0+a1;
        double maxAngle0 = 0;
        double maxAngle1 = 0;
        double minAngle0 = 0;
        double minAngle1 = 0;

        for (double i = 0; i < 2*Math.PI; i+=Math.PI/512) {
            
            cosT0 = Math.cos(i);
            sinT0 = Math.sin(i);
            
            x0 = ((cosT0*cosB0*a0 - sinT0*sinB0*b0)*cosNA0 - (cosT0*sinB0*a0 + sinT0*cosB0*b0)*sinNA0)*cosA0 
                -((cosT0*cosB0*a0 - sinT0*sinB0*b0)*sinNA0 + (cosT0*sinB0*a0 + sinT0*cosB0*b0)*cosNA0)*sinA0*cosInc0
                +((cosB0*cosNA0 - sinB0*sinNA0)*cosA0 - (cosB0*sinNA0 + sinB0*cosNA0)*cosInc0*sinA0)*c0;
            
            y0 = ((cosT0*cosB0*a0 - sinT0*sinB0*b0)*cosNA0 - (cosT0*sinB0*a0 + sinT0*cosB0*b0)*sinNA0)*sinA0 
                +((cosT0*cosB0*a0 - sinT0*sinB0*b0)*sinNA0 + (cosT0*sinB0*a0 + sinT0*cosB0*b0)*cosNA0)*cosA0*cosInc0
                +((cosB0*cosNA0 - sinB0*sinNA0)*sinA0 + (cosB0*sinNA0 + sinB0*cosNA0)*cosInc0*cosA0)*c0;
            
            z0 = (cosT0*sinAP0*a0+sinT0*cosAP0*b0+sinAP0*c0)*sinInc0;
            
            for (double j = 0; j < 2*Math.PI; j+=Math.PI/512) {
                
                cosT1 = Math.cos(j);
                sinT1 = Math.sin(j);
                
                x1 = ((cosT1*cosB1*a1 - sinT1*sinB1*b1)*cosNA1 - (cosT1*sinB1*a1 + sinT1*cosB1*b1)*sinNA1)*cosA1 
                    -((cosT1*cosB1*a1 - sinT1*sinB1*b1)*sinNA1 + (cosT1*sinB1*a1 + sinT1*cosB1*b1)*cosNA1)*sinA1*cosInc1
                    +((cosB1*cosNA1 - sinB1*sinNA1)*cosA1 - (cosB1*sinNA1 + sinB1*cosNA1)*cosInc1*sinA1)*c1;
                
                y1 = ((cosT1*cosB1*a1 - sinT1*sinB1*b1)*cosNA1 - (cosT1*sinB1*a1 + sinT1*cosB1*b1)*sinNA1)*sinA1 
                    +((cosT1*cosB1*a1 - sinT1*sinB1*b1)*sinNA1 + (cosT1*sinB1*a1 + sinT1*cosB1*b1)*cosNA1)*cosA1*cosInc1
                    +((cosB1*cosNA1 - sinB1*sinNA1)*sinA1 + (cosB1*sinNA1 + sinB1*cosNA1)*cosInc1*cosA1)*c1;
                
                z1 = (cosT1*sinAP1*a1+sinT1*cosAP1*b1+sinAP1*c1)*sinInc1;
                
                if (minDist > Math.sqrt((x0-x1)*(x0-x1)+(y0-y1)*(y0-y1)+(z0-z1)*(z0-z1))) {
                    minDist = Math.sqrt((x0-x1)*(x0-x1)+(y0-y1)*(y0-y1)+(z0-z1)*(z0-z1));
                    minAngle0 = i;
                    minAngle1 = j;
                }
                if (maxDist < Math.sqrt((x0-x1)*(x0-x1)+(y0-y1)*(y0-y1)+(z0-z1)*(z0-z1))) {
                    maxDist = Math.sqrt((x0-x1)*(x0-x1)+(y0-y1)*(y0-y1)+(z0-z1)*(z0-z1));
                    maxAngle0 = i;
                    maxAngle1 = j;
                }
            }
        }
        
        //System.out.println("Min Dist~ " + minDist + " at angles: " + minAngle0 + " " + minAngle1);
        //System.out.println("Max Dist~ " + maxDist + " at angles: " + maxAngle0 + " " + maxAngle1);
        double[] res = {minDist,maxDist};
        return res;
    }
}

