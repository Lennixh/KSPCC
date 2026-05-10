package kspcc;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class AntennaVisualizer extends JPanel{
    
    private JFrame f;
    private BG master;
    public ArrayList<Antenna> antennae;
    public ArrayList<String> selectorStrings;
    ArrayList<String> units;
        
    private ArrayList<Antenna> vesselAntennae;
    
    JPanel displayer;
    
    public JComboBox antennaSelectorBox;
    
    private AntennaCreator ac;
    
    public void init(Font font, JFrame f, BG master){
        
        this.f = f;
        this.master = master;

        ac = new AntennaCreator(master, this);
        
        setLayout(new GridBagLayout());
        setBackground(master.BGCOLOR);
        setForeground(master.FONTCOLOR);

        units = new ArrayList<>();
        units.add("Tm");
        units.add("Gm");
        units.add("Mm");
        units.add("Km");
        units.add("m");
        
        vesselAntennae = new ArrayList<>();
        
        antennae = new ArrayList<>();
        antennae.add(new Antenna("Communotron 16", 500, 3, 1.0f, Antenna.DIRECT));
        antennae.add(new Antenna("Communotron 16-S", 500, 3, 0.0f, Antenna.DIRECT));
        antennae.add(new Antenna("HG-5 High Gain Antenna", 5, 2, 0.75f, Antenna.RELAY));
        antennae.add(new Antenna("Communotron DTS-M1", 2, 1, 0.75f, Antenna.DIRECT));
        antennae.add(new Antenna("RA-2 Relay Antenna", 2, 1, 0.75f, Antenna.RELAY));
        antennae.add(new Antenna("Communotron HG-55", 15, 1, 0.75f, Antenna.DIRECT));
        antennae.add(new Antenna("RA-15 Relay Antenna", 15, 1, 0.75f, Antenna.RELAY));
        antennae.add(new Antenna("Communotron 88-88", 100, 1, 0.75f, Antenna.DIRECT));
        antennae.add(new Antenna("RA-100 Relay Antenna", 100, 1, 0.75f, Antenna.RELAY));
        antennae.add(new Antenna("Level 1 TS", 2, 1, 0.75f, Antenna.RELAY));
        antennae.add(new Antenna("Level 2 TS", 50, 1, 0.75f, Antenna.RELAY));
        antennae.add(new Antenna("Level 3 TS", 250, 1, 0.75f, Antenna.RELAY));
        
        
        selectorStrings = new ArrayList<>();
        for (Antenna a : antennae) {
            selectorStrings.add(a.getName());
        }
        selectorStrings.add("Custom");
        selectorStrings.add("Select one");
        
        antennaSelectorBox = new JComboBox(selectorStrings.toArray());
        antennaSelectorBox.setBackground(master.BGCOLOR);
        antennaSelectorBox.setForeground(master.FONTCOLOR);
        antennaSelectorBox.setFont(font);
        antennaSelectorBox.setToolTipText("Click antennae to remove them");
        antennaSelectorBox.setSelectedIndex(antennaSelectorBox.getItemCount()-1);
        antennaSelectorBox.addActionListener(e -> {
            if (antennaSelectorBox.getSelectedIndex() < antennaSelectorBox.getItemCount()-2 && !master.creatorOpen) {
                vesselAntennae.add(antennae.get(antennaSelectorBox.getSelectedIndex()));
                JButton anntennaHandler = new JButton(vesselAntennae.get(vesselAntennae.size()-1).getName());
                anntennaHandler.setFont(font);
                anntennaHandler.setBackground(master.BGCOLOR);
                anntennaHandler.setForeground(master.FONTCOLOR);
                
                anntennaHandler.addActionListener(ex ->{
                    int ind = displayer.getComponentCount()-1;
                    vesselAntennae.remove(ind);
                    displayer.remove(anntennaHandler);
                    revalidate();
                    f.repaint();
                    master.updateLabels();
                });
                
                revalidate();
                f.repaint();
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridy = displayer.getComponentCount();
                displayer.add(anntennaHandler, c);
                
                master.updateLabels();
            }
            if (antennaSelectorBox.getSelectedIndex() == antennaSelectorBox.getItemCount()-2 && !master.creatorOpen) {
                ac.open();
            }
            antennaSelectorBox.setSelectedIndex(antennaSelectorBox.getItemCount()-1);
        });
        
        GridBagConstraints c = new GridBagConstraints();
        displayer = new JPanel();
        displayer.setLayout(new GridBagLayout());
        displayer.setBackground(master.BGCOLOR);
        add(displayer, c);
        c.gridy = 1;
        add(antennaSelectorBox, c);
    }
    
    public void updateColors() {
        for (int i = 0; i < displayer.getComponentCount(); i++) {
            displayer.getComponent(i).setBackground(master.BGCOLOR);
            displayer.getComponent(i).setForeground(master.FONTCOLOR);
        }
        displayer.setBackground(master.BGCOLOR);
        displayer.setForeground(master.FONTCOLOR);
        antennaSelectorBox.setBackground(master.BGCOLOR);
        antennaSelectorBox.setForeground(master.FONTCOLOR);
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
