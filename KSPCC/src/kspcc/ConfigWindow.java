package kspcc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigWindow {
    
    private JFrame cfgWindow;
    private int screenWidth;
    private int screenHeight;
    
    public ConfigWindow(Color bg, Color fg, BG master, Font font){
        cfgWindow = new JFrame("Settings");
        cfgWindow.setPreferredSize(new Dimension(512,512));
        cfgWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        cfgWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cfgWindow.setVisible(false);
                close();
            }
        });
        
        JPanel elementContainer = new JPanel();
        elementContainer.setBackground(bg);
        elementContainer.setLayout(new GridBagLayout());
        elementContainer.setPreferredSize(new Dimension(512,512));
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 30;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 1;
        c.weighty = 0;
        ColorPicker cp1 = new ColorPicker();
        cp1.init(bg, fg, font, master.color1, "First", master);
        elementContainer.add(cp1, c);
        
        c.gridy = 1;
        ColorPicker cp2 = new ColorPicker();
        cp2.init(bg, fg, font, master.color2, "Second", master);
        elementContainer.add(cp2, c);
        cfgWindow.add(elementContainer);
        
        cfgWindow.pack();
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    }
    
    public void open(){
        cfgWindow.setLocation((screenWidth-512)/2, (screenHeight-512)/2);
        cfgWindow.setVisible(true);
        
    }
    public void close(){
        cfgWindow.setVisible(false);
    }
}
