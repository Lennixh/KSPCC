package kspcc;

import java.awt.*;
import javax.swing.*;

public class KSPCC {
    
    private JFrame f;
    public void init(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        f = new JFrame("KSPCC");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(d);
        
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
