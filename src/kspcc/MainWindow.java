package kspcc;

import javax.swing.*;
import java.awt.*;

class MainWindow extends JFrame
{

    public MainWindow()
    {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        java.net.URL mainWindowURL = KSPCC.class.getResource("resources/radarSmallWhite.png");
        ImageIcon mainWindowIcon = new ImageIcon(mainWindowURL);

        setTitle("KSPCC");
        setPreferredSize(d);
        setIconImage(mainWindowIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

    }
}