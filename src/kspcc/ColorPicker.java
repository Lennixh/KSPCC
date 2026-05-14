package kspcc;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ColorPicker extends JPanel
{
    public Color color;

    public ColorPicker(Color initColor, String place, BG master)
    {
        color = initColor;

        setBackground(master.BGCOLOR);
        setLayout(new GridBagLayout());

        NumberFormat intFormat = NumberFormat.getInstance();
        intFormat.setMaximumIntegerDigits(3);
        intFormat.setMinimumIntegerDigits(1);
        intFormat.setMaximumFractionDigits(0);


        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        JLabel colorLabel = new JLabel(place + " Color:");
        add(colorLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0,20,0,0);
        JLabel redLabel = new JLabel("Red:");
        add(redLabel, c);

        c.gridy = 2;
        JLabel greenLabel = new JLabel("Green:");
        add(greenLabel, c);

        c.gridy = 3;
        JLabel blueLabel = new JLabel("Blue:");
        add(blueLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 0;
        JFormattedTextField redIn = new JFormattedTextField(intFormat);
        redIn.setColumns(3);
        redIn.setValue(initColor.getRed());
        redIn.setCaretColor(master.FONTCOLOR);
        redIn.setFocusLostBehavior(JFormattedTextField.REVERT);
        add(redIn, c);
        redIn.addActionListener(e ->
        {
            redIn.setValue((Math.min(Integer.parseInt(redIn.getValue()+""), 255)));
            color = new Color(Integer.parseInt(redIn.getValue()+""), color.getGreen(), color.getBlue());
            updateColors(master, place);
        });

        c.gridy = 2;
        JFormattedTextField greenIn = new JFormattedTextField(intFormat);
        greenIn.setColumns(3);
        greenIn.setValue(initColor.getGreen());
        greenIn.setCaretColor(master.FONTCOLOR);
        greenIn.setFocusLostBehavior(JFormattedTextField.REVERT);
        greenIn.addActionListener(e ->
        {
            greenIn.setValue(Math.min(Integer.parseInt(greenIn.getValue()+""), 255));
            color = new Color(color.getRed(), Integer.parseInt(greenIn.getValue()+""), color.getBlue());
            updateColors(master, place);
        });
        add(greenIn, c);

        c.gridy = 3;
        JFormattedTextField blueIn = new JFormattedTextField(intFormat);
        blueIn.setColumns(3);
        blueIn.setValue(initColor.getBlue());
        blueIn.setCaretColor(master.FONTCOLOR);
        blueIn.setFocusLostBehavior(JFormattedTextField.REVERT);
        blueIn.addActionListener(e ->
        {
            blueIn.setValue(Math.min(Integer.parseInt(blueIn.getValue()+""), 255));
            color = new Color(color.getRed(), color.getGreen(), Integer.parseInt(blueIn.getValue()+""));
            updateColors(master, place);
        });
        add(blueIn, c);

        for (int i = 0; i < getComponentCount(); i++)
        {
            getComponent(i).setBackground(master.BGCOLOR);
            getComponent(i).setForeground(master.FONTCOLOR);
            getComponent(i).setFont(master.GLOBALFONT.deriveFont(16f));
        }
        colorLabel.setFont(master.GLOBALFONT.deriveFont(Font.BOLD, 16f));
    }
    
    private void updateColors(BG master, String place)
    {
        if (place.equals("First Orbit"))
        {
            master.color1 = color;
        } else
        {
            master.color2 = color;
        }
        master.updateLabels();
    }
}
