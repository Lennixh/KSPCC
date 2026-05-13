package kspcc;

import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class KSPCC
{
    public WindowManager windowManager;

    public JFrame frame;

    public BG bg;

    private CloseConfirmWindow ccw;

    public KSPCC()
    {
        windowManager = new WindowManager();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        java.net.URL mainWindowIcon = KSPCC.class.getResource("resources/radarSmallWhite.png");
        ImageIcon feeshIcon = new ImageIcon(mainWindowIcon);

        frame = new JFrame("KSPCC");
        frame.setIconImage(feeshIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(d);
        frame.setUndecorated(true);


        bg = new BG();
        bg.init(this);
        frame.add(bg, BorderLayout.CENTER);

        frame.pack();

        windowManager.addWindow(frame);

        windowManager.openWindow(frame);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        ccw = new CloseConfirmWindow(this, bg);

        windowManager.addWindow(ccw);

        KeyMaster.init();
        Timer loop = new Timer();
        loop.scheduleAtFixedRate(new TimerTask() {
            public void run()
            {
                if(KeyMaster.isEscPressed() && !windowManager.isWindowOpen(ccw))
                {
                    windowManager.openWindow(ccw);
                }
                ccw.setState(JFrame.NORMAL);
                ccw.setAlwaysOnTop(true);
            }

        }, 0, 16);
    }
    
    public static void main(String[] args)
    {
        KSPCC k = new KSPCC();
    }
    
}
