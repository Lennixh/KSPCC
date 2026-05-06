package kspcc;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class AntennaVisualizer extends JPanel{
    
    private JFrame f;
    private BG bgo;
    private ArrayList<Antenna> antennae;
    private ArrayList<String> selectorStrings;
    ArrayList<String> units;
        
    private ArrayList<Antenna> vesselAntennae;
    
    JPanel displayer;
    
    private JComboBox antennaSelectorBox;
    
    public void init(Font font, Color bg, Color fg, JFrame f, BG bgo){
        
        this.f = f;
        this.bgo = bgo;
        
        setLayout(new GridBagLayout());
        setBackground(bg);
        setForeground(fg);

        units = new ArrayList<>();
        units.add("Tm");
        units.add("Gm");
        units.add("Mm");
        units.add("Km");
        units.add("m");
        
        vesselAntennae = new ArrayList<>();
        
        antennae = new ArrayList<>();
        antennae.add(new Antenna("Communotron 16", 500, 3, 1.0f));
        antennae.add(new Antenna("Communotron 16-S", 500, 3, 0.0f));
        antennae.add(new Antenna("HG-5 High Gain Antenna", 5, 2));
        antennae.add(new Antenna("Communotron DTS-M1", 2));
        antennae.add(new Antenna("Communotron HG-55", 15));
        antennae.add(new Antenna("Communotron 88-88", 100));
        antennae.add(new Antenna("Level 1 TS", 2));
        antennae.add(new Antenna("Level 2 TS", 50));
        antennae.add(new Antenna("Level 3 TS", 250));
        
        
        selectorStrings = new ArrayList<>();
        for (Antenna a : antennae) {
            selectorStrings.add(a.getName());
        }
        selectorStrings.add("Custom");
        selectorStrings.add("Select one");
        
        antennaSelectorBox = new JComboBox(selectorStrings.toArray());
        antennaSelectorBox.setBackground(bg);
        antennaSelectorBox.setForeground(fg);
        antennaSelectorBox.setFont(font);
        antennaSelectorBox.setSelectedIndex(antennaSelectorBox.getItemCount()-1);
        antennaSelectorBox.addActionListener(e -> {
            if (antennaSelectorBox.getSelectedIndex() < antennaSelectorBox.getItemCount()-2) {
                vesselAntennae.add(antennae.get(antennaSelectorBox.getSelectedIndex()));
                JButton anntennaHandler = new JButton(vesselAntennae.get(vesselAntennae.size()-1).getName());
                anntennaHandler.setFont(font);
                anntennaHandler.setBackground(bg);
                anntennaHandler.setForeground(fg);
                
                anntennaHandler.addActionListener(ex ->{
                    int ind = displayer.getComponentCount()-1;
                    vesselAntennae.remove(ind);
                    displayer.remove(anntennaHandler);
                    revalidate();
                    f.repaint();
                    bgo.updateLabels();
                });
                
                revalidate();
                f.repaint();
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridy = displayer.getComponentCount();
                displayer.add(anntennaHandler, c);
                
                bgo.updateLabels();
            }
            if (antennaSelectorBox.getSelectedIndex() == antennaSelectorBox.getItemCount()-2 ) {
                String name = fetchUserAntennaName("Input name of Antenna");
                int unit = units.indexOf(fetchUserUnit());
                double signalStrength = Double.parseDouble(fetchUserDoubleAsString("Input signal strength of antenna"));
                float compatabilityEponent = Float.parseFloat(fetchUserFloatAsString("Input compatability exponent \n(leave blank for standard 0.75)"));
                Antenna customAntenna = new Antenna(name, signalStrength, unit, compatabilityEponent);
                antennae.add(customAntenna);
                selectorStrings.add(antennae.size()-1, customAntenna.getName());
                antennaSelectorBox.removeAllItems();
                for (String s : selectorStrings) {
                    antennaSelectorBox.addItem(s);
                }
            }
            antennaSelectorBox.setSelectedIndex(antennaSelectorBox.getItemCount()-1);
        });
        
        GridBagConstraints c = new GridBagConstraints();
        displayer = new JPanel();
        displayer.setLayout(new GridBagLayout());
        displayer.setBackground(bg);
        add(displayer, c);
        c.gridy = 1;
        add(antennaSelectorBox, c);
        
    }
    
    private String fetchUserUnit(){
        String s = (String) JOptionPane.showInputDialog(f, "Input unit\n(Options: Tm, Gm, Mm, Km, m)", "");
        if (!units.contains(s)) {
            s = fetchUserUnit();
        }
        return s;
    }
    
    private String fetchUserFloatAsString(String message){
        String s = (String) JOptionPane.showInputDialog(f, message, "");
        try {
            if (s.equals("")) {
                s = "0.75";
            }
            double val = Float.parseFloat(s);
        } catch (NumberFormatException ex) {
            s = fetchUserDoubleAsString(message);
        }
        return s;
    }
    
    private String fetchUserDoubleAsString(String message){
        String s = (String) JOptionPane.showInputDialog(f, message, "");
        try {
            if (s.equals("")) {
                s = "1";
            }
            double val = Double.parseDouble(s);
        } catch (NumberFormatException ex) {
            s = fetchUserDoubleAsString(message);
        }
        return s;
    }
    
    private String fetchUserAntennaName(String message){
        String s = (String) JOptionPane.showInputDialog(f, message, "");
        if (s.equals("")){
            s = "New Antenna " + (selectorStrings.size() - 11);
        }
        return s;
    }
    

    private double getMaxPow(){
        double maxPow = 0;
        for (Antenna a : vesselAntennae) {
            if (a.getStrength() > maxPow) {
                maxPow = a.getStrength();
            }
        }
        //maxPow in megameters, but needed in Gigameters
        return maxPow/1000;
    }
    
    private double getSumPow(){
        double sumPow = 0;
        for (Antenna a : vesselAntennae) {
            sumPow += a.getStrength();
        }
        //sumPow in megameters, but needed in Gigameters
        return sumPow/1000;
    }
    
    private float getAverageCompatabilityExponent(){
        double num1 = 0;
        double num2 = 0;
        for (Antenna a : vesselAntennae) {
            num1 += a.getStrength()*a.getCompatabilityExponent();
            num2 += a.getStrength();
        }
        return (float) (num1/num2);
    }
    
    public double getTotalAntennaPower(){
        if (vesselAntennae.isEmpty()) {
            return 0d;
        }
        double maxPow = getMaxPow();
        double sumPow = getSumPow();
        float averageCE = getAverageCompatabilityExponent();
        return maxPow*Math.pow(sumPow/maxPow, averageCE);
    }
}
