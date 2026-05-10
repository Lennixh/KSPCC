package kspcc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class AntennaCreator {
    
    private JFrame creatorWindow;
    
    private BG master;
    
    private JPanel elementContainer;
    
    private int screenWidth;
    private int screenHeight;

    
    public AntennaCreator(BG master, AntennaVisualizer av){
        
        ArrayList<String> units = new ArrayList<>();
        units.add("Tm");
        units.add("Gm");
        units.add("Mm");
        units.add("Km");
        units.add("m");
        
        NumberFormat doubleFormat = NumberFormat.getNumberInstance();
        doubleFormat.setGroupingUsed(false);
        doubleFormat.setMaximumFractionDigits(10);
        doubleFormat.setMaximumIntegerDigits(10);
        doubleFormat.setMinimumIntegerDigits(1);
        
        NumberFormat floatFormat = NumberFormat.getNumberInstance();
        floatFormat.setGroupingUsed(false);
        floatFormat.setMaximumFractionDigits(10);
        floatFormat.setMaximumIntegerDigits(1);
        
        this.master = master;
        
        creatorWindow = new JFrame("Antenna Creator");
        creatorWindow.setAlwaysOnTop(true);
        creatorWindow.setPreferredSize(new Dimension(512,256));
        creatorWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        creatorWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                master.creatorOpen = false;
                close();
            }
        });
        
        elementContainer = new JPanel();
        elementContainer.setLayout(new GridBagLayout());
        elementContainer.setBackground(master.BGCOLOR);
        
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(0,0,0,10);
        elementContainer.add(new JLabel("Name:"), c);
        c.gridy = 1;
        elementContainer.add(new JLabel("Range:"), c);
        c.gridy = 2;
        JLabel ceLabel = new JLabel("Compatability Exponent:");
        ceLabel.setToolTipText("This determines how welll antennae combine, higher is better");
        elementContainer.add(ceLabel, c);
        
        c.insets = new Insets(0,0,0,0);
        c.gridy = 0;
        c.gridx = 1;
        JTextField nameField = new JTextField();
        nameField.setText("Antenna");
        nameField.setColumns(10);
        nameField.setCaretColor(master.FONTCOLOR);
        nameField.setSelectionColor(master.FONTCOLOR);
        elementContainer.add(nameField, c);

        c.gridy = 1;
        c.gridx = 1;
        JFormattedTextField rangeField = new JFormattedTextField(doubleFormat);
        rangeField.setValue(1.0d);
        rangeField.setColumns(10);
        rangeField.setCaretColor(master.FONTCOLOR);
        rangeField.setSelectionColor(master.FONTCOLOR);
        elementContainer.add(rangeField, c);
        
        c.gridx = 2;
        JComboBox unit = new JComboBox(units.toArray());
        unit.setSelectedIndex(1);
        elementContainer.add(unit, c);
        
        c.gridy = 2;
        c.gridx = 1;
        JFormattedTextField ceField = new JFormattedTextField(floatFormat);
        ceField.setValue(0.75f);
        ceField.setColumns(10);
        ceField.setCaretColor(master.FONTCOLOR);
        ceField.setSelectionColor(master.FONTCOLOR);
        ceField.setToolTipText("Almost all antennae use 0.75");
        elementContainer.add(ceField, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(20,20,0,20);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e->{
            close();
            av.antennae.add(new Antenna(nameField.getText(), (double)rangeField.getValue(), unit.getSelectedIndex(), (float)ceField.getValue()));
            av.selectorStrings.add(av.antennae.size()-1, av.antennae.get(av.antennae.size()-1).getName());
            av.antennaSelectorBox.removeAllItems();
            for (String s : av.selectorStrings) {
                av.antennaSelectorBox.addItem(s);
            }
        });
        elementContainer.add(addButton, c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e->{
            close();
        });
        elementContainer.add(cancelButton, c);
        
        for (int i = 0; i < elementContainer.getComponentCount(); i++) {
            elementContainer.getComponent(i).setBackground(master.BGCOLOR);
            elementContainer.getComponent(i).setForeground(master.FONTCOLOR);
            elementContainer.getComponent(i).setFont(master.GLOBALFONT.deriveFont(16f));
        }
        
        creatorWindow.add(elementContainer);
        creatorWindow.pack();
    }
        
    public void open(){
        creatorWindow.setVisible(true);
        master.creatorOpen = true;
    }
    
    public void close(){
        creatorWindow.setVisible(false);
        master.creatorOpen = false;
    }
}
