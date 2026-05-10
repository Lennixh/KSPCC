package kspcc;

import java.awt.*;
import javax.swing.*;
import java.net.URL;

public class KSPCC {
    
    private JFrame f;
    public void init(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        java.net.URL feesh = KSPCC.class.getResource("resources/radarSmallWhite.png");
        ImageIcon feeshIcon = new ImageIcon(feesh);
        f = new JFrame("KSPCC");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(d);
        f.setIconImage(feeshIcon.getImage());
        BG bg = new BG();
        bg.init(f);
        f.add(bg, BorderLayout.CENTER);

        f.pack();
  
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public static void main(String[] args) {
        KSPCC k = new KSPCC();
        k.init();
    }
    
}
