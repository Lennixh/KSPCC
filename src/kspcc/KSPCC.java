package kspcc;

import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class KSPCC
{
    public WindowManager windowManager;

    public MainWindow mainWindow;

    public BG bg;

    private CloseConfirmWindow ccw;

    public KSPCC()
    {
        windowManager = new WindowManager();

        mainWindow = new MainWindow();

        bg = new BG();
        bg.init(this);

        mainWindow.add(bg, BorderLayout.CENTER);

        mainWindow.pack();

        windowManager.addWindow(mainWindow);

        windowManager.openWindow(mainWindow);

        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

        ccw = new CloseConfirmWindow(this, bg);
        windowManager.addWindow(ccw);
        ccw.setAlwaysOnTop(true);

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
            }

        }, 0, 16);
    }
    
    public static void main(String[] args)
    {
        KSPCC k = new KSPCC();
    }
    
}
