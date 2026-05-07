package kspcc;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;



public class OrbitCustomiser extends JPanel{
    
    public Body body;
    private ArrayList<String> units;
    private int unit = 1;
    
    public void init(Font font, Color bg, Color fg, BG master, boolean first){
        
        body = new Body("b", 1, 0, 0, 0, 0, null);
    
        NumberFormat doubleFormat = NumberFormat.getNumberInstance();
        doubleFormat.setGroupingUsed(false);
        doubleFormat.setMaximumFractionDigits(10);
        doubleFormat.setMaximumIntegerDigits(10);
        doubleFormat.setMinimumIntegerDigits(1);
        
        NumberFormat floatFormat = NumberFormat.getNumberInstance();
        floatFormat.setGroupingUsed(false);
        floatFormat.setMaximumFractionDigits(10);
        floatFormat.setMaximumIntegerDigits(1);
        
        setBackground(bg);
        setForeground(fg);
        setLayout(new GridBagLayout());
        
        units = new ArrayList<>();
        units.add("Tm");
        units.add("Gm");
        units.add("Mm");
        units.add("Km");
        units.add("m");
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("SMA:"),c);
        
        c.gridx = 1;
        JFormattedTextField SMAnumIn = new JFormattedTextField(doubleFormat);
        SMAnumIn.setValue(body.getSMA());
        SMAnumIn.setColumns(10);
        SMAnumIn.setSelectionColor(fg);
        SMAnumIn.setCaretColor(fg);
        SMAnumIn.addActionListener(e -> {
            body.setSMA(Double.parseDouble(SMAnumIn.getValue()+"")*Math.pow(1000, 1-unit));
            master.updateLabels();
            //System.out.println(body.getSMA() + "Gm");
        });
        add(SMAnumIn,c);
        
        c.gridx = 2;
        JComboBox SMAunit = new JComboBox(units.toArray());
        SMAunit.setSelectedIndex(unit);
        SMAunit.addActionListener(e -> {
            unit = SMAunit.getSelectedIndex();
            body.setSMA(Double.parseDouble(SMAnumIn.getValue()+"")*Math.pow(1000, 1-unit));
            master.updateLabels();
            //System.out.println(body.getSMA() + "Gm");
        });
        add(SMAunit,c);
        
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Eccentricity:"),c);
        
        c.gridx = 1;
        JFormattedTextField EccentricityIn = new JFormattedTextField(floatFormat);
        EccentricityIn.setValue(body.getE());
        EccentricityIn.setColumns(10);
        EccentricityIn.setSelectionColor(fg);
        EccentricityIn.setCaretColor(fg);
        EccentricityIn.addActionListener(e -> {
            EccentricityIn.setValue(Math.min(Double.parseDouble(EccentricityIn.getValue()+""), 1d));
            body.setE(Float.parseFloat(EccentricityIn.getValue()+""));
            master.updateLabels();
        });
        add(EccentricityIn,c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Inclination:"),c);
        
        c.gridx = 1;
        JFormattedTextField InclinationIn = new JFormattedTextField(doubleFormat);
        InclinationIn.setValue(body.getInc());
        InclinationIn.setColumns(10);       
        InclinationIn.setSelectionColor(fg);
        InclinationIn.setCaretColor(fg);
        InclinationIn.addActionListener(e -> {
            body.setInc(Double.parseDouble(InclinationIn.getValue()+"")%360d);
            InclinationIn.setValue(body.getInc());
            master.updateLabels();
        });
        add(InclinationIn,c);
        
        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("°"),c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("AoP:"),c);
        
        c.gridx = 1;
        JFormattedTextField AoPIn = new JFormattedTextField(doubleFormat);
        AoPIn.setValue(body.getAoP());
        AoPIn.setColumns(10);       
        AoPIn.setSelectionColor(fg);
        AoPIn.setCaretColor(fg);
        AoPIn.addActionListener(e -> {
            body.setAoP(Double.parseDouble(AoPIn.getValue()+"")%360d);
            AoPIn.setValue(body.getAoP());
            master.updateLabels();
        });
        add(AoPIn,c);
        
        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("°"),c);
        
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("LoA:"),c);
        
        c.gridx = 1;
        JFormattedTextField LoAIn = new JFormattedTextField(doubleFormat);
        LoAIn.setValue(body.getLoA());
        LoAIn.setColumns(10);       
        LoAIn.setSelectionColor(fg);
        LoAIn.setCaretColor(fg);
        LoAIn.addActionListener(e -> {
            body.setLoA(Double.parseDouble(LoAIn.getValue()+"")%360d);
            LoAIn.setValue(body.getLoA());
            master.updateLabels();
        });
        add(LoAIn,c);
        
        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("°"),c);
        
        c.gridy = 5;
        c.gridx = 0;
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipady = 10;
        add(new JLabel("Read from .cfg file:"),c);
        
        c.gridy = 5;
        c.gridx = 1;
        c.ipady = 0;
        c.anchor = GridBagConstraints.LINE_START;
        JButton KopernicusConfigSelctor = new JButton("Open...");
        JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new CfgFilter());
        
        JButton activator = new JButton("");
        if (first) {
            activator.setText("Using first vanilla orbit");
        } else {
            activator.setText("Using second vanilla orbit");
        }
        activator.addActionListener(e ->{
            if (first) {
                master.useCustom1 = !master.useCustom1;
                if (master.useCustom1) {
                    activator.setText("Using first custom orbit");
                } else {
                    activator.setText("Using first vanilla orbit");
                }
            } else {
                master.useCustom2 = !master.useCustom2;
                if (master.useCustom2) {
                    activator.setText("Using second custom orbit");
                } else {
                    activator.setText("Using second vanilla orbit");
                }
            }
            
            master.updateLabels();
        });

        
        KopernicusConfigSelctor.addActionListener(e -> {
            if (fc.showOpenDialog(master) == JFileChooser.APPROVE_OPTION) {
                File cfg = fc.getSelectedFile();
                try(Scanner fileReader = new Scanner(cfg);) {
                    String s;
                    while (fileReader.hasNextLine()) {
                        s = fileReader.nextLine();
                        if (s.contains("Orbit")) {
                            body = getBodyFromFile(fileReader, new Body());
                            if (first) {
                                master.useCustom1 = true;
                                activator.setText("Using first custom orbit");
                            } else {
                                master.useCustom2 = true;
                                activator.setText("Using second custom orbit");
                            }
                            SMAnumIn.setValue(body.getSMA());
                            EccentricityIn.setValue(body.getE());
                            InclinationIn.setValue(body.getInc());
                            AoPIn.setValue(body.getAoP());
                            LoAIn.setValue(body.getLoA());
                            unit = 1;
                            SMAunit.setSelectedIndex(1);
                            master.updateLabels();
                            master.repaint();
                            break;
                        }
                    }
                    
                    
                    //System.out.println(indexOfComponent(KopernicusConfigSelctor.getParent().getParent().getComponents(), KopernicusConfigSelctor.getParent()) + "");
                } catch (FileNotFoundException ex) {
                    System.out.println("Oopsie woopsie the file disappeared");
                }
            }
        });
        add(KopernicusConfigSelctor,c);
        
        c.gridy = 6;
        c.gridx = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10,0,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(activator, c);
        
        
        
        for (int i = 0; i < getComponentCount(); i++) {
            getComponent(i).setBackground(bg);
            getComponent(i).setForeground(fg);
            getComponent(i).setFont(font);
        }
    }

    
    private Body getBodyFromFile(Scanner sc, Body b) {
        String s = sc.nextLine();
        if (s.contains("}")) {
            return b;
        }
        if (s.contains("semiMajorAxis")) {
            b.setSMA(Double.parseDouble(s.substring(s.lastIndexOf("=") + 1))/1_000_000_000);
            //System.out.println(s);
            //System.out.println(b.getSMA());
        }
        if (s.contains("eccentricity")) {
            b.setE(Float.parseFloat(s.substring(s.lastIndexOf("=") + 1)));
            //System.out.println(s);
            //System.out.println(b.getE());
        }
        if (s.contains("inclination")) {
            b.setInc(Float.parseFloat(s.substring(s.lastIndexOf("=") + 1)));
            //System.out.println(s);
            //System.out.println(b.getInc());
        }
        if (s.contains("argumentOfPeriapsis")) {
            b.setAoP(Double.parseDouble(s.substring(s.lastIndexOf("=") + 1)));
            //System.out.println(s);
            //System.out.println(b.getAoP());
        }
        if (s.contains("longitudeOfAscendingNode")) {
            b.setLoA(Double.parseDouble(s.substring(s.lastIndexOf("=") + 1)));
            //System.out.println(s);
            //System.out.println(b.getLoA());
        }
        
        return getBodyFromFile(sc, b);
    }
    
    public void setFontColor(Color c){
        for (int i = 0; i < getComponentCount(); i++) {
            getComponent(i).setForeground(c);
        }
    }
}