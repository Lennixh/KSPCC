package kspcc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigWindow extends JFrame
{
    
    private BG bg;
    
    private JPanel elementContainer;
    
    private ColorPicker cp1;
    private ColorPicker cp2;
    
    private SystemSelectors sysS;
    
    private int screenWidth;
    private int screenHeight;
    
    private Image image;
    
    public ConfigWindow(KSPCC master, Image image)
    {
        this.bg = master.bg;
        this.image = image;

        setTitle("Settings");
        setIconImage(image);
        setAlwaysOnTop(true);
        setPreferredSize(new Dimension(512,512));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                close(master.windowManager);
            }
        });
        
        elementContainer = new JPanel();
        elementContainer.setBackground(bg.BGCOLOR);
        elementContainer.setLayout(new GridBagLayout());
        elementContainer.setPreferredSize(new Dimension(512,512));
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 0;
        c.insets = new Insets(20,0,35,0);
        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setBackground(bg.BGCOLOR);
        settingsLabel.setForeground(bg.FONTCOLOR);
        settingsLabel.setFont(bg.GLOBALFONT.deriveFont(Font.BOLD, 30));
        elementContainer.add(settingsLabel);
        
        c.insets = new Insets(0,15,20,0);
        c.gridy = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        cp1 = new ColorPicker();
        cp1.init(bg.color1, "First Orbit", bg);
        elementContainer.add(cp1, c);

        c.gridy = 2;
        cp2 = new ColorPicker();
        cp2.init(bg.color2, "Second Orbit", bg);
        elementContainer.add(cp2, c);

        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        sysS = new SystemSelectors();
        sysS.init(bg);
        elementContainer.add(sysS, c);
	
        JScrollPane scroller = new JScrollPane(elementContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setBackground(bg.BGCOLOR);
        add(scroller);
        
        pack();
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    }
    
    public void open(WindowManager windowManager)
    {
        windowManager.openWindow(this);
        
    }
    public void close(WindowManager windowManager)
    {
        windowManager.closeWindow(this);
    }
}
