package kspcc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SystemSelectors extends JPanel implements ItemListener{
    
    private JCheckBox vanillaButton;
    private JCheckBox OPMButton;
    private JCheckBox MPEButton;
    private JCheckBox solButton;
    
    private BG master;
    
    public void init(BG master){
        
        this.master = master;
        
        setBackground(master.BGCOLOR);
        setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        JLabel optionNameLabel = new JLabel("Included Systems");
        add(optionNameLabel, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0,20,0,0);
        vanillaButton = new JCheckBox("Vanilla");
        vanillaButton.setSelected(true);
        vanillaButton.addItemListener(this);
        add(vanillaButton, c);
        
        c.gridy = 2;
        OPMButton = new JCheckBox("OPM");
        OPMButton.setSelected(false);
        OPMButton.addItemListener(this);
        OPMButton.setToolTipText("Outer Planets Mod");
        add(OPMButton, c);
        
        c.gridy = 3;
        MPEButton = new JCheckBox("MPE");
        MPEButton.setSelected(false);
        MPEButton.addItemListener(this);
        MPEButton.setToolTipText("Minor Planets Expansion, due to implementation, requires OPM");
        add(MPEButton, c);
        
        c.gridy = 4;
        solButton = new JCheckBox("Sol/RSS");
        solButton.setSelected(false);
        solButton.addItemListener(this);
        solButton.setToolTipText("Using ICRF system refernce frame/equatorial luna refernce frame");
        add(solButton, c);
        
        for (int i = 0; i < getComponentCount(); i++) {
            getComponent(i).setBackground(master.BGCOLOR);
            getComponent(i).setForeground(master.FONTCOLOR);
            getComponent(i).setFont(master.GLOBALFONT.deriveFont(16));
        }
        
        optionNameLabel.setFont(master.GLOBALFONT.deriveFont(Font.BOLD, 16));
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        int index = -1;
        char which = '+';
        
        if (source == vanillaButton) {
            index = 0;
        } else if (source == OPMButton) {
            index = 1;
        } else if (source == MPEButton) {
            index = 2;
        } else if (source == solButton) {
            index = 3;
        } 

        if (e.getStateChange() == ItemEvent.DESELECTED) {
            which = '-';
        }
        char[] data = master.sysCombo.toCharArray();
        data[index] = which;
        master.sysCombo = String.copyValueOf(data);
        master.updateList();
    }
    
}
