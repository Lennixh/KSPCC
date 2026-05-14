package kspcc;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class AntennaVisualizer extends JPanel
{
    public ArrayList<Antenna> antennae;
    public ArrayList<String> selectorStrings;
    private ArrayList<String> units;
        
    private ArrayList<Antenna> vesselAntennae;
    
    public JPanel displayer;
    
    public JComboBox antennaSelectorBox;
    
    public AntennaCreator ac;

    public AntennaVisualizer(KSPCC master, Color bgColor, Color fgColor, Font font)
    {

        ac = new AntennaCreator(master, this, bgColor, fgColor, font);
        master.windowManager.addWindow(ac);
        ac.setAlwaysOnTop(true);

        setLayout(new GridBagLayout());
        setBackground(bgColor);
        setForeground(fgColor);

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
        antennaSelectorBox.setBackground(bgColor);
        antennaSelectorBox.setForeground(fgColor);
        antennaSelectorBox.setFont(font);
        antennaSelectorBox.setToolTipText("Click antennae to remove them");
        antennaSelectorBox.setSelectedIndex(antennaSelectorBox.getItemCount()-1);
        antennaSelectorBox.addActionListener(e ->
        {
            if (antennaSelectorBox.getSelectedIndex() < antennaSelectorBox.getItemCount()-2)
            {
                vesselAntennae.add(antennae.get(antennaSelectorBox.getSelectedIndex()));
                JButton anntennaHandler = new JButton(vesselAntennae.get(vesselAntennae.size()-1).getName());
                anntennaHandler.setFont(font);
                anntennaHandler.setBackground(master.bg.BGCOLOR);
                anntennaHandler.setForeground(master.bg.FONTCOLOR);

                anntennaHandler.addActionListener(ex ->
                {
                    int ind = displayer.getComponentCount()-1;
                    vesselAntennae.remove(ind);
                    displayer.remove(anntennaHandler);
                    revalidate();
                    master.mainWindow.repaint();
                    master.bg.updateLabels();
                });

                revalidate();
                master.mainWindow.repaint();
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridy = displayer.getComponentCount();
                displayer.add(anntennaHandler, c);
                master.bg.updateLabels();
            }
            if (antennaSelectorBox.getSelectedIndex() == antennaSelectorBox.getItemCount()-2)
            {
                master.windowManager.openWindow(ac);
            }
            antennaSelectorBox.setSelectedIndex(antennaSelectorBox.getItemCount()-1);
        });

        GridBagConstraints c = new GridBagConstraints();
        displayer = new JPanel();
        displayer.setLayout(new GridBagLayout());
        displayer.setBackground(bgColor);
        add(displayer, c);
        c.gridy = 1;
        add(antennaSelectorBox, c);
    }

    private double getMaxPow()
    {
        double maxPow = 0;
        for (Antenna a : vesselAntennae)
        {
            if (a.getStrength() > maxPow)
            {
                maxPow = a.getStrength();
            }
        }
        //maxPow in megameters, but needed in Gigameters
        return maxPow/1000;
    }
    
    private double getSumPow()
    {
        double sumPow = 0;
        for (Antenna a : vesselAntennae)
        {
            sumPow += a.getStrength();
        }
        //sumPow in megameters, but needed in Gigameters
        return sumPow/1000;
    }
    
    private float getAverageCompatabilityExponent()
    {
        double num1 = 0;
        double num2 = 0;
        for (Antenna a : vesselAntennae)
        {
            num1 += a.getStrength()*a.getCompatabilityExponent();
            num2 += a.getStrength();
        }
        return (float) (num1/num2);
    }
    
    public double getTotalAntennaPower()
    {
        if (vesselAntennae.isEmpty())
        {
            return 0d;
        }
        double maxPow = getMaxPow();
        double sumPow = getSumPow();
        float averageCE = getAverageCompatabilityExponent();
        return maxPow*Math.pow(sumPow/maxPow, averageCE);
    }
}
