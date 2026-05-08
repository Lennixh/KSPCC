package kspcc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigWindow {
    
    private JFrame cfgWindow;
    
    private BG master;
    
    private JPanel elementContainer;
    
    private ColorPicker cp1;
    private ColorPicker cp2;
    
    private int screenWidth;
    private int screenHeight;
    
    private Image image;
    
    public ConfigWindow(Color bg, Color fg, BG master, Font font, Image image){
        
        this.master = master;
        this.image = image;
        cfgWindow = new JFrame("Settings");
        cfgWindow.setIconImage(image);
        cfgWindow.setAlwaysOnTop(true);
        cfgWindow.setPreferredSize(new Dimension(512,512));
        cfgWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        cfgWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                master.configOpen = false;
                close();
            }
        });
        
        elementContainer = new JPanel();
        elementContainer.setBackground(bg);
        elementContainer.setLayout(new GridBagLayout());
        elementContainer.setPreferredSize(new Dimension(512,512));
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 0;
        c.insets = new Insets(20,0,35,0);
        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setBackground(bg);
        settingsLabel.setForeground(fg);
        settingsLabel.setFont(font.deriveFont(Font.BOLD, 30));
        elementContainer.add(settingsLabel);
        
        c.insets = new Insets(0,15,20,0);
        c.gridy = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        cp1 = new ColorPicker();
        cp1.init(bg, fg, font, master.color1, "First", master);
        elementContainer.add(cp1, c);
        
        c.gridy = 2;
        c.weighty = 1;
        c.weightx = 1;
        cp2 = new ColorPicker();
        cp2.init(bg, fg, font, master.color2, "Second", master);
        elementContainer.add(cp2, c);
        
        JScrollPane scroller = new JScrollPane(elementContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setBackground(bg);
        cfgWindow.add(scroller);
        
        cfgWindow.pack();
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    }
    
    public void updateColors(){
        for (int i = 0; i < elementContainer.getComponentCount(); i++) {
            elementContainer.getComponent(i).setBackground(master.BGCOLOR);
            elementContainer.getComponent(i).setForeground(master.FONTCOLOR);
        }
        elementContainer.setBackground(master.BGCOLOR);
        elementContainer.setForeground(master.FONTCOLOR);
        cp1.updateColorMode();
        cp2.updateColorMode();
        cfgWindow.setIconImage(image);
    }
    
    public void open(){
        cfgWindow.setLocation((screenWidth-512)/2, (screenHeight-512)/2);
        cfgWindow.setState(JFrame.NORMAL);
        cfgWindow.setVisible(true);
        
    }
    public void close(){
        cfgWindow.setVisible(false);
    }
}
