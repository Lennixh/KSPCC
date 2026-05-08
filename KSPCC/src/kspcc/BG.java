package kspcc;

import java.awt.*;

import java.util.ArrayList;
import javax.swing.*;


public class BG extends JPanel{
    
    private JFrame f;
    
    private final Font GLOBALFONT = new Font("Sans Serif", Font.PLAIN, 10);
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
    
    private final ArrayList<Body> standardBodies = new ArrayList<>();
    private final ArrayList<Body> progenitorialBodies = new ArrayList<>();
    private final ArrayList<String> progenitorialNames = new ArrayList<>();
    private final ArrayList<Body> availableBodies = new ArrayList<>();
    private final ArrayList<String> availableNames = new ArrayList<>();
    
    private final Body    Sun = new Body("   Sun", 0.0000000000, 0.000f, 0.000, 0.000, 0.000, null);
    private final Body   Moho = new Body("  Moho", 5.2631383040, 0.200f, 7.000, 15.00, 70.00, Sun);
    private final Body    Eve = new Body("   Eve", 9.8326845440, 0.010f, 2.100, 0.000, 15.00, Sun);
    private final Body  Gilly = new Body(" Gilly", 0.0315000000, 0.550f, 12.00, 10.00, 10.00, Eve);
    private final Body Kerbin = new Body("Kerbin", 13.599840256, 0.000f, 0.000, 0.000, 0.000, Sun);
    private final Body    Mun = new Body("   Mun", 0.0120000000, 0.000f, 0.000, 0.000, 0.000, Kerbin);
    private final Body Minmus = new Body("Minmus", 0.0470000000, 0.000f, 6.000, 38.00, 78.00, Kerbin);
    private final Body   Duna = new Body("  Duna", 20.726155264, 0.051f, 0.060, 0.000, 135.5, Sun);
    private final Body    Ike = new Body("   Ike", 0.0032000000, 0.030f, 0.020, 0.000, 0.000, Duna);
    private final Body   Dres = new Body("  Dres", 40.839348203, 0.145f, 5.000, 90.00, 280.0, Sun);
    private final Body   Jool = new Body("  Jool", 68.773560320, 0.050f, 1.304, 0.000, 52.00, Sun);
    private final Body Laythe = new Body("Laythe", 0.0271840000, 0.000f, 0.000, 0.000, 0.000, Jool);
    private final Body   Vall = new Body("  Vall", 0.0431520000, 0.000f, 0.000, 0.000, 0.000, Jool);
    private final Body   Tylo = new Body("  Tylo", 0.0685000000, 0.000f, 0.025, 0.000, 0.000, Jool);
    private final Body    Bop = new Body("   Bop", 0.1285000000, 0.235f, 15.00, 25.00, 10.00, Jool);
    private final Body    Pol = new Body("   Pol", 0.1798900000, 0.171f, 4.250, 15.00, 2.000, Jool);
    private final Body  Eeloo = new Body(" Eeloo", 90.118820000, 0.260f, 6.150, 260.0, 50.00, Sun);
    
    private Body rBody = Sun;
    private Body tBody1 = Moho;
    private Body tBody2 = Eve;
    
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
        standardBodies.add(Sun);
        standardBodies.add(Moho);
        standardBodies.add(Eve);
        standardBodies.add(Gilly);
        standardBodies.add(Kerbin);
        standardBodies.add(Mun);
        standardBodies.add(Minmus);
        standardBodies.add(Duna);
        standardBodies.add(Ike);
        standardBodies.add(Dres);
        standardBodies.add(Jool);
        standardBodies.add(Laythe);
        standardBodies.add(Vall);
        standardBodies.add(Tylo);
        standardBodies.add(Bop);
        standardBodies.add(Pol);
        standardBodies.add(Eeloo);
        
        for (Body b : standardBodies){
            if (b.hasChildren()) {
                progenitorialBodies.add(b);
                progenitorialNames.add(b.getName());
            }
        }
        availableBodies.add(rBody);
        availableNames.add(rBody.getName());
        for (Body b : rBody.getDirectChildren()){
            availableBodies.add(b);
            availableNames.add(b.getName());
        }
        setBackground(BGCOLOR);
        
        setPreferredSize(f.getPreferredSize());
        setMinimumSize(f.getMinimumSize());
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
        referenceBox = new JComboBox(progenitorialNames.toArray());
        referenceBox.setFont(GLOBALFONT.deriveFont(15f));
        referenceBox.setSelectedIndex(0);
        referenceBox.addActionListener(e -> {
            for (Body b : progenitorialBodies) {
                if (b.getName() == referenceBox.getSelectedItem()) {
                    rBody = b;
                }
            }
            availableBodies.clear();
            availableNames.clear();
            firstBodyBox.removeAllItems();
            secondBodyBox.removeAllItems();
            availableBodies.add(rBody);
            availableNames.add(rBody.getName());
            firstBodyBox.addItem(rBody.getName());
            secondBodyBox.addItem(rBody.getName());
            for (Body b : rBody.getDirectChildren()) {
                availableBodies.add(b);
                availableNames.add(b.getName());
                firstBodyBox.addItem(b.getName());
                secondBodyBox.addItem(b.getName());
            }
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
        firstBodyBox = new JComboBox(availableNames.toArray());
        firstBodyBox.setFont(GLOBALFONT.deriveFont(15f));
        firstBodyBox.setSelectedIndex(1);
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
        secondBodyBox = new JComboBox(availableNames.toArray());
        secondBodyBox.setFont(GLOBALFONT.deriveFont(15f));
        secondBodyBox.setSelectedIndex(2);
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
        output.init(GLOBALFONT.deriveFont(Font.BOLD, 16f),BGCOLOR,FONTCOLOR);
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
        cfgWindow = new ConfigWindow(BGCOLOR, FONTCOLOR, this, GLOBALFONT.deriveFont(Font.PLAIN, 16f), configWhite.getImage());
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
        
        updateLabels();
    }
    
    public void updateLabels(){
        
        dp.scale = 1;
        dp.orbits.clear();
        dp.orbitShapes.clear();
        dp.orbitColors.clear();
        dp.orbitColors.add(color1);
        dp.orbitColors.add(color2);
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
        
       
        //dp.generateEllipse(tBody1.getSMA(), tBody1.getSMB(), tBody1.getAoP(), tBody1.getLoA(), tBody1.getInc());
        //dp.generateEllipse(tBody2.getSMA(), tBody2.getSMB(), tBody2.getAoP(), tBody2.getLoA(), tBody2.getInc());
        
        unit = getGoodUnit(maxRange);
        output.getLabel(0).setText("Maximum communications range: " + roundToDigits(maxRange/Math.pow(1000, 1-unit),2) + units.get(unit));
        unit = getGoodUnit(minDist);
        output.getLabel(1).setText("Minimum distance: " + roundToDigits(minDist/Math.pow(1000, 1-unit),2) + units.get(unit));
        unit = getGoodUnit(maxDist);
        output.getLabel(2).setText("Maximum distance: " + roundToDigits(maxDist/Math.pow(1000, 1-unit),2) + units.get(unit));
        output.getLabel(3).setText("Signal strength at minimum distance: " + roundToDigits(minDistStrength*100,2) + "%");
        output.getLabel(4).setText("Signal strength at maximum distance: " + roundToDigits(maxDistStrength*100,2) + "%");
        
        
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
        return 2-(int)Math.ceil(Math.log10(value)/3);
    }
    
    private double roundToDigits(double val, int n){
        return (double) Math.round(val*Math.pow(10, n))/Math.pow(10, n);
    }
}
