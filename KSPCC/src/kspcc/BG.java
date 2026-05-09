package kspcc;

import java.awt.*;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;


public class BG extends JPanel{
    
    private JFrame f;
    
    public final Font GLOBALFONT = new Font("Sans Serif", Font.PLAIN, 10);
    public Color BGCOLOR = Color.BLACK;
    public Color FONTCOLOR = Color.WHITE;
    
    private final ArrayList<String> units = new ArrayList<>(); 
    private int unit = 1;
    
    private JComboBox referenceBox;
    private JComboBox firstBodyBox;
    private JComboBox secondBodyBox;
    
    private Output output;
    
    private AntennaVisualizer av1;
    private AntennaVisualizer av2;
    
    private OrbitVisualizer dp;
        
    private OrbitCustomiser oc1;
    private OrbitCustomiser oc2;
    
    private ConfigWindow cfgWindow;
    
    private JLabel firstBodyLabel;
    private JLabel secondBodyLabel;
    
    
    private ArrayList<Body> parentBodies = new ArrayList<>();
    private ArrayList<Body> availableBodies = new ArrayList<>();
    
    public String sysCombo = "+---";
    
    private ArrayList<Body> vanillaBodies = new ArrayList<>();
    private ArrayList<Body> OPMBodies = new ArrayList<>();
    private ArrayList<Body> MPEBodies = new ArrayList<>();
    private ArrayList<Body> solBodies = new ArrayList<>();
    
    private ArrayList<Body> inSystemBodies = new ArrayList<>();

    private Body rBody;
    private Body tBody1;
    private Body tBody2;
    
    public boolean useCustom1 = false;
    public boolean useCustom2 = false;
    
    public Color color1 = Color.RED;
    public Color color2 = Color.CYAN;
    
    public boolean configOpen = false;
    public boolean darkMode = true;
    
    public void init(JFrame f){
        
        this.f = f;
        
        units.add("Tm");
        units.add("Gm");
        units.add("Mm");
        units.add("Km");
        units.add("m");
        
        parentBodies.add(PrebuiltSystems.Sun);
        
        rBody = PrebuiltSystems.Sun;
        tBody1 = PrebuiltSystems.vanillaBodies[3];  //Kerbin
        tBody2 = PrebuiltSystems.vanillaBodies[6];  //Duna
        
        //VANILLA
        vanillaBodies.addAll(Arrays.asList(PrebuiltSystems.vanillaBodies));
        
        //OPM
        OPMBodies.addAll(Arrays.asList(PrebuiltSystems.OPMBodies));
        
        //MPE
        MPEBodies.addAll(Arrays.asList(PrebuiltSystems.MPEBodies));
        
        //Sol/RSS  Dactyl is really weird, ask ballisticfox about it these values are for ICRF system refernce frame, and equatorial luna refernce frame
        solBodies.addAll(Arrays.asList(PrebuiltSystems.solBodies)); //if they are wrong, thats too damn bad
        
        setBackground(BGCOLOR);
        
        setPreferredSize(f.getPreferredSize());
        setBackground(BGCOLOR);
        setLayout(new GridBagLayout());
        
        //The title
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        JLabel title = new JLabel("KSP Communications Calcuator");
        title.setFont(GLOBALFONT.deriveFont(Font.BOLD, 25));
        title.setBackground(BGCOLOR);
        title.setForeground(FONTCOLOR);
        add(title, c);
        
        //The Reference Body Selector Label
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.weighty = 0.00;
        c.anchor = GridBagConstraints.PAGE_END;
        JLabel refDescriptor = new JLabel("Reference Body: ");
        refDescriptor.setFont(GLOBALFONT.deriveFont(15f));
        refDescriptor.setBackground(BGCOLOR);
        refDescriptor.setForeground(FONTCOLOR);
        add(refDescriptor, c);
        
        //The Reference Body Selector
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0.1;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.PAGE_START;
        referenceBox = new JComboBox();
        referenceBox.setFont(GLOBALFONT.deriveFont(15f));
        referenceBox.addActionListener(e -> {
            for (Body b : parentBodies) {
                if (b.getName() == referenceBox.getSelectedItem()) {
                    rBody = b;
                }
            }
            updateChildList(0,1);
            tBody1 = availableBodies.get(0);
            tBody2 = availableBodies.get(1);
                    
            updateLabels();
        });
        referenceBox.setBackground(BGCOLOR);
        referenceBox.setForeground(FONTCOLOR);
        add(referenceBox, c);
        
        //The First Body Selector Label
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_END;
        firstBodyLabel = new JLabel("First body: ");
        firstBodyLabel.setFont(GLOBALFONT.deriveFont(15f));
        firstBodyLabel.setBackground(BGCOLOR);
        firstBodyLabel.setForeground(color1);
        add(firstBodyLabel, c);
        
        //The First Body Selector
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.PAGE_START;
        firstBodyBox = new JComboBox();
        firstBodyBox.setFont(GLOBALFONT.deriveFont(15f));
        firstBodyBox.addActionListener(e -> {
            for (Body b : availableBodies) {
                if (b.getName() == firstBodyBox.getSelectedItem()) {
                    tBody1 = b;
                }
            }
            updateLabels();
        });
        firstBodyBox.setBackground(BGCOLOR);
        firstBodyBox.setForeground(color1);
        add(firstBodyBox, c);
        
        //The Second Body Selector Label
        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_END;
        secondBodyLabel = new JLabel("Second body: ");
        secondBodyLabel.setFont(GLOBALFONT.deriveFont(15f));
        secondBodyLabel.setBackground(BGCOLOR);
        secondBodyLabel.setForeground(color2);
        add(secondBodyLabel, c);
        
        //The Second Body Selector
        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 2;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.PAGE_START;
        secondBodyBox = new JComboBox();
        secondBodyBox.setFont(GLOBALFONT.deriveFont(15f));
        secondBodyBox.addActionListener(e -> {
            for (Body b : availableBodies) {
                if (b.getName() == secondBodyBox.getSelectedItem()) {
                    tBody2 = b;
                }
            }
            updateLabels();
        });
        secondBodyBox.setBackground(BGCOLOR);
        secondBodyBox.setForeground(color2);
        add(secondBodyBox, c);
        
        //the orbital display
        dp = new OrbitVisualizer();
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 3;
        c.weighty = 1f;
        dp.init(new Dimension(480,480), tBody1, tBody2, this);
        dp.setBackground(BGCOLOR);
        dp.setForeground(FONTCOLOR);
        add(dp,c);
        
        //The Output
        output = new Output();
        output.init(this);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 4;
        c.weighty = 0.0;
        c.insets = new Insets(0,0,30,0);
        c.anchor = GridBagConstraints.PAGE_END;
        add(output, c);
        
        //The Antenna selection stuff
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 2;
        c.insets = new Insets(0,0,30,0);
        c.anchor = GridBagConstraints.PAGE_END;
        av1 = new AntennaVisualizer();
        av1.init(GLOBALFONT.deriveFont(Font.PLAIN, 12f), f, this);
        add(av1,c);
        
        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 3;
        c.gridheight = 2;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0,0,30,0);
        av2 = new AntennaVisualizer();
        av2.init(GLOBALFONT.deriveFont(Font.PLAIN, 12f), f, this);
        add(av2,c);

        //Custom orbit stuff
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        oc1 = new OrbitCustomiser();
        oc1.init(GLOBALFONT.deriveFont(Font.PLAIN, 16f), color1, this, true);
        add(oc1,c);
        
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 3;
        oc2 = new OrbitCustomiser();
        oc2.init(GLOBALFONT.deriveFont(Font.PLAIN, 16f), color2, this, false);
        add(oc2, c);

        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        JButton configButton = new JButton();
        configButton.setBackground(BGCOLOR);
        configButton.setForeground(FONTCOLOR);
        configButton.setPreferredSize(new Dimension(32,32));
        configButton.setBorderPainted(false);
        java.net.URL configWhiteURL = KSPCC.class.getResource("resources/configIconWhite.png");
        java.net.URL configBlackURL = KSPCC.class.getResource("resources/configIconBlack.png");
        ImageIcon configWhite = new ImageIcon(configWhiteURL);
        ImageIcon configBlack = new ImageIcon(configBlackURL);
        configButton.setIcon(configWhite);
        cfgWindow = new ConfigWindow(this, configWhite.getImage());
        configButton.addActionListener(e->{
            configOpen = !configOpen;
            if (configOpen) {
                cfgWindow.open();
            } else {
                cfgWindow.close();
            }
        });
        
        add(configButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        JButton lightDarkButton = new JButton();
        lightDarkButton.setBackground(BGCOLOR);
        lightDarkButton.setForeground(FONTCOLOR);
        lightDarkButton.setPreferredSize(new Dimension(32,32));
        lightDarkButton.setBorderPainted(false);
        java.net.URL moonImageURL = KSPCC.class.getResource("resources/moonSmall.png");
        java.net.URL sunImageURL = KSPCC.class.getResource("resources/sunSmall.png");
        ImageIcon moonIcon = new ImageIcon(moonImageURL);
        ImageIcon sunIcon = new ImageIcon(sunImageURL);
        lightDarkButton.setIcon(moonIcon);
        lightDarkButton.addActionListener(e->{
            darkMode = !darkMode;
            if (darkMode) {
                lightDarkButton.setIcon(moonIcon);
                configButton.setIcon(configWhite);
            } else {
                lightDarkButton.setIcon(sunIcon);
                configButton.setIcon(configBlack);
            }
            updateColors();
        });
        add(lightDarkButton, c);

        updateList();
    }
    
    public void updateLabels(){
        
        dp.orbits.clear();
        
        if (!useCustom1) {
            dp.addOrbit(tBody1);
        } else {
            dp.addOrbit(oc1.body);
        }
        
        if (!useCustom2) {
            dp.addOrbit(tBody2);
        } else {
            dp.addOrbit(oc2.body);
        }
        
        
        dp.repaint();
        
        oc1.setFontColor(color1);
        firstBodyLabel.setForeground(color1);
        firstBodyBox.setForeground(color1);
        
        oc2.setFontColor(color2);
        secondBodyLabel.setForeground(color2);
        secondBodyBox.setForeground(color2);
       
        double[] dists = new double[2];
        if (rBody == tBody1 && !useCustom1) {
            dists[0] = tBody2.getPE();
            dists[1] = tBody2.getAP();
        } else if (rBody == tBody2 && !useCustom2){
            dists[0] = tBody1.getPE();
            dists[1] = tBody1.getAP();
        } else {
            dists = dp.getDists();
        }
        double minDist = dists[0];
        double maxDist = dists[1];
        double comPower1 = av1.getTotalAntennaPower(); //all in GM
        double comPower2 = av2.getTotalAntennaPower();
        double maxRange = Math.sqrt(comPower1*comPower2);
        double minRDist = 1-(minDist/maxRange);
        double maxRDist = 1-(maxDist/maxRange);
        double minDistStrength = 0, maxDistStrength = 0;
        if (minRDist > 0) {
            minDistStrength = (3-(2*minRDist))*(minRDist*minRDist);
        }
        if (maxRDist > 0) {
            maxDistStrength = (3-(2*maxRDist))*(maxRDist*maxRDist);
        }
        
        unit = getGoodUnit(maxRange);
        output.getLabel(0).setText("Maximum communications range: " + roundToDigits(maxRange/Math.pow(1000, 1-unit),2) + units.get(unit));
        unit = getGoodUnit(minDist);
        output.getLabel(1).setText("Minimum distance: " + roundToDigits(minDist/Math.pow(1000, 1-unit),2) + units.get(unit));
        unit = getGoodUnit(maxDist);
        output.getLabel(2).setText("Maximum distance: " + roundToDigits(maxDist/Math.pow(1000, 1-unit),2) + units.get(unit));
        output.getLabel(3).setText("Signal strength at minimum distance: " + roundToDigits(minDistStrength*100,2) + "%");
        output.getLabel(4).setText("Signal strength at maximum distance: " + roundToDigits(maxDistStrength*100,2) + "%");
        
        
    }
    
    public void updateList(){
        
        ActionListener temp = referenceBox.getActionListeners()[0];
        referenceBox.removeActionListener(temp);
        
        parentBodies.clear();
        availableBodies.clear();
        inSystemBodies.clear();
        referenceBox.removeAllItems();
        
        //adds selectable reference bodies
        if (sysCombo.charAt(0) == '+') {
            for (Body b : vanillaBodies) {
                if (!parentBodies.contains(b.getParent())) {
                    parentBodies.add(b.getParent());
                }
                inSystemBodies.add(b);
            }
        }
        if (sysCombo.charAt(1) == '+') {
            for (Body b : OPMBodies) {
                if (!parentBodies.contains(b.getParent())) {
                    parentBodies.add(b.getParent());
                }
                inSystemBodies.add(b);
            }
        }
        if (sysCombo.charAt(2) == '+') {
            for (Body b : MPEBodies) {
                if (!parentBodies.contains(b.getParent())) {
                    parentBodies.add(b.getParent());
                }
                inSystemBodies.add(b);
            }
        }
        if (sysCombo.charAt(3) == '+') {
            for (Body b : solBodies) {
                if (!parentBodies.contains(b.getParent())) {
                    parentBodies.add(b.getParent());
                }
                inSystemBodies.add(b);
            }
        }
        for (Body b : parentBodies) {
            referenceBox.addItem(b.getName());
        }
        referenceBox.setSelectedIndex(parentBodies.indexOf(rBody));

        referenceBox.addActionListener(temp);
        
        updateChildList(0,0);
        
        updateLabels();
    }
    
    public void updateChildList(int ind1, int ind2) {
        
        ActionListener temp1 = firstBodyBox.getActionListeners()[0];
        ActionListener temp2= secondBodyBox.getActionListeners()[0];
        firstBodyBox.removeActionListener(temp1);
        secondBodyBox.removeActionListener(temp2);
        
        availableBodies.clear();
        firstBodyBox.removeAllItems();
        secondBodyBox.removeAllItems();
        
        availableBodies.add(rBody);
        firstBodyBox.addItem(rBody.getName());
        secondBodyBox.addItem(rBody.getName());
        
        //adds available bodies
        for (Body b : inSystemBodies) {
            if (b.getParent() == rBody) {
                availableBodies.add(b);
                firstBodyBox.addItem(b.getName());
                secondBodyBox.addItem(b.getName());
            }
        }
        firstBodyBox.setSelectedIndex(ind1);
        secondBodyBox.setSelectedIndex(ind2);
        
        firstBodyBox.addActionListener(temp1);
        secondBodyBox.addActionListener(temp2);
    }
    
    public void updateColors(){
        Color temp = BGCOLOR;
        BGCOLOR = FONTCOLOR;
        FONTCOLOR = temp;
        setBackground(BGCOLOR);
        setForeground(FONTCOLOR);
        for (int i = 0; i < getComponentCount(); i++) {
            getComponent(i).setBackground(BGCOLOR);
            getComponent(i).setForeground(FONTCOLOR);
        }
        firstBodyLabel.setForeground(color1);
        firstBodyBox.setForeground(color1);
        secondBodyLabel.setForeground(color2);
        secondBodyBox.setForeground(color2);
        output.updateColors();
        av1.updateColors();
        av2.updateColors();
        oc1.updateColors();
        oc2.updateColors();
        cfgWindow.updateColors();
    }
    
    //value in GM
    private int getGoodUnit(double value){
        if (value == 0) {
            return 1;
        }
        return 2-Math.min((int)Math.ceil(Math.log10(value)/3),2);
    }
    
    private double roundToDigits(double val, int n){
        return (double) Math.round(val*Math.pow(10, n))/Math.pow(10, n);
    }
    
}
