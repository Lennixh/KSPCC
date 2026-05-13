package kspcc;

import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class KSPCC {
    
    public JFrame f;
    private CloseConfirmWindow ccw;
    public boolean confirmOpen = false;

    public void init(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        java.net.URL feesh = KSPCC.class.getResource("resources/radarSmallWhite.png");
        ImageIcon feeshIcon = new ImageIcon(feesh);
        f = new JFrame("KSPCC");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(d);
        f.setUndecorated(true);

        f.setIconImage(feeshIcon.getImage());
        BG bg = new BG();
        bg.init(f);
        f.add(bg, BorderLayout.CENTER);

        f.pack();
  
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        ccw = new CloseConfirmWindow(this, bg);

        KeyMaster.init();
        Timer loop = new Timer();
        loop.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(KeyMaster.isEscPressed() && !confirmOpen){
                    ccw.open();
                }
            }

        }, 0, 16);
    }
    
    public static void main(String[] args) {
        KSPCC k = new KSPCC();
        k.init();
    }
    
}
