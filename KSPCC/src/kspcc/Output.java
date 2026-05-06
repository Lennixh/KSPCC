package kspcc;


import java.awt.*;
import javax.swing.*;

public 
class Output extends JPanel{
    
    private JLabel minDistLabel;
    private JLabel maxDistLabel;
    private JLabel minDistStrengthLabel;
    private JLabel maxDistStrengthLabel;
    private JLabel maxRangeLabel;
    
    public void init(Font font, Color bg, Color fg){
        setLayout(new GridBagLayout());
        setBackground(bg);
        setForeground(fg);
        maxRangeLabel = new JLabel("Maximum distance for connection: ");
        minDistLabel = new JLabel("Minimum distance: ");
        maxDistLabel = new JLabel("Maximum distance: ");
        minDistStrengthLabel = new JLabel("Signal strength at minimum distance: ");
        maxDistStrengthLabel = new JLabel("Signal strength at maximum distance: ");
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.insets = new Insets(0,0,10,0);
        add(maxRangeLabel, c);
        c.insets = new Insets(0,0,2,0);
        c.gridy = 2;
        add(minDistLabel, c);
        c.gridy = 3;
        add(maxDistLabel, c);
        c.gridy = 4;
        add(minDistStrengthLabel, c);
        c.gridy = 5;
        c.weighty = 1.0f;
        c.anchor = GridBagConstraints.PAGE_START;
        add(maxDistStrengthLabel, c);
        for (int i = 0; i < getComponentCount(); i++) {
            Component p = getComponent(i);
            p.setBackground(bg);
            p.setForeground(fg);
            p.setFont(font);
        }
    }
    public JLabel getLabel(int ind){
        return (JLabel) getComponent(ind);
    }
}



