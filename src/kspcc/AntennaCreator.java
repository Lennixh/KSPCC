package kspcc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class AntennaCreator extends JFrame
{
    
    private JPanel elementContainer;
    
    private int screenWidth;
    private int screenHeight;

    private JTextField nameField;

    private JFormattedTextField rangeField;
    private JFormattedTextField ceField;

    public AntennaCreator(KSPCC master, AntennaVisualizer parent, Color bgColor, Color fgColor, Font font)
    {

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


        setTitle("Antenna Creator");
        setAlwaysOnTop(true);
        setPreferredSize(new Dimension(512,256));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                close(master.windowManager);
            }
        });
        
        elementContainer = new JPanel();
        elementContainer.setLayout(new GridBagLayout());
        elementContainer.setBackground(bgColor);
        
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
        nameField = new JTextField();
        nameField.setText("Antenna");
        nameField.setColumns(10);
        elementContainer.add(nameField, c);

        c.gridy = 1;
        c.gridx = 1;
        rangeField = new JFormattedTextField(doubleFormat);
        rangeField.setValue(1.0d);
        rangeField.setColumns(10);
        elementContainer.add(rangeField, c);
        
        c.gridx = 2;
        JComboBox unit = new JComboBox(units.toArray());
        unit.setSelectedIndex(1);
        elementContainer.add(unit, c);
        
        c.gridy = 2;
        c.gridx = 1;
        ceField = new JFormattedTextField(floatFormat);
        ceField.setValue(0.75f);
        ceField.setColumns(10);
        ceField.setToolTipText("Almost all antennae use 0.75");
        elementContainer.add(ceField, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(20,20,0,20);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e->
        {
            close(master.windowManager);

            parent.antennae.add(new Antenna(nameField.getText(), (double)rangeField.getValue(), unit.getSelectedIndex(), (float)ceField.getValue(), Antenna.DIRECT));
            parent.selectorStrings.add(parent.antennae.size()-1, parent.antennae.get(parent.antennae.size()-1).getName());
            parent.antennaSelectorBox.removeAllItems();
            for (String s : parent.selectorStrings)
            {
                parent.antennaSelectorBox.addItem(s);
            }
        });
        elementContainer.add(addButton, c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e->
        {
            close(master.windowManager);
        });
        elementContainer.add(cancelButton, c);
        
        for (int i = 0; i < elementContainer.getComponentCount(); i++)
        {
            elementContainer.getComponent(i).setBackground(bgColor);
            elementContainer.getComponent(i).setForeground(fgColor);
            elementContainer.getComponent(i).setFont(font.deriveFont(16f));
        }
        updateCaret(fgColor);
        add(elementContainer);
        pack();
    }
        
    public void open(WindowManager windowManager)
    {
        windowManager.openWindow(this);
    }
    
    public void close(WindowManager windowManager)
    {
        windowManager.closeWindow(this);
    }

    public void updateCaret(Color fgColor)
    {
        nameField.setCaretColor(fgColor);
        nameField.setSelectionColor(fgColor);
        rangeField.setCaretColor(fgColor);
        rangeField.setSelectionColor(fgColor);
        ceField.setCaretColor(fgColor);
        ceField.setSelectionColor(fgColor);
    }
}
