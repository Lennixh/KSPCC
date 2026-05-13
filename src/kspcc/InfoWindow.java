package kspcc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InfoWindow extends JFrame
{

    private BG bg;

    private JPanel holder;

    public static final ImageIcon helpWhite = new ImageIcon(KSPCC.class.getResource("resources/helpIconWhite.png"));
    public static final ImageIcon helpBlack = new ImageIcon(KSPCC.class.getResource("resources/helpIconBlack.png"));

    public InfoWindow(KSPCC master)
    {
        setTitle("Info");

        this.bg = master.bg;

        setIconImage(helpWhite.getImage());

        holder = new JPanel();
        holder.setBackground(bg.BGCOLOR);
        holder.setForeground(bg.FONTCOLOR);
        holder.setPreferredSize(new Dimension(800,800));
        holder.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        JLabel title = new JLabel("Information");
        title.setForeground(bg.FONTCOLOR);
        title.setBackground(bg.BGCOLOR);
        title.setFont(bg.GLOBALFONT.deriveFont(Font.BOLD, 48f));
        holder.add(title, c);

        c.gridy = 1;
        JTextArea uh = new JTextArea();
        uh.setText( "You can click the moon/sun icon in the top left of the main window to switch between\n" +
                    "light and dark mode.\n\n" +
                    "The gear icon in the top right allows you to change some settings, including which\n" +
                    "bodies you can choose from, and the colors of the orbital lines.\n\n" +
                    "Orbits in the middle are displayed viewed from the northern pole of Kerbin to the\n" +
                    "southern, projected flat.\n\n" +
                    "You can, instead of choosing from preset bodies, input your own orbital parameters.\n\n" +
                    "You can toggle beteen preset and custom Orbits with theit corresponding buttons below.\n\n" +
                    "SMA stands for semi major axis, which is the long axis of the orbit/ellipse, it is\n" +
                    "equivalent to half the distance between Apopasis and Periapsis.\n" +
                    "Eccentricity is how elongated an orbit is, the closer it is to 0, the closer the orbit\n" +
                    "is to a circle.\n" +
                    "Inclination describes how tilted an orbit is, its a value from 0° to 360°.\n" +
                    "LoA stands for longitude of ascending node, it is defined by the angle between the\n" +
                    "ascending node and the refrence direction.\n" +
                    "AoP stands for argument of periapsis, its the angle between the periapsis and the LoA.\n\n" +
                    "These values can also be read from Kopernicus config(.cfg) Files. They are files, that\n" +
                    "the characteristics of each bodie in a planet pack.\n\n" +
                    "To add antennae to your craft, just click on the selectors and choose one from the list.\n" +
                    "You can add custom antennae by clicking custom, which will open an window for antenna creation.\n\n" +
                    "One Tm is one thousand Gm, one Gm is one thousand Mm, one Mm is one thousand Km,\n" +
                    "and one Km is one thousand m.");
        uh.setEditable(false);
        uh.setBackground(bg.BGCOLOR);
        uh.setForeground(bg.FONTCOLOR);
        uh.setFont(bg.GLOBALFONT.deriveFont(Font.BOLD, 16f));
        holder.add(uh, c);
        JScrollPane scroller = new JScrollPane(holder, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroller);
        pack();
        setLocation(560, 140);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                close(master.windowManager);
            }
        });
    }

    public void close(WindowManager windowManager)
    {
        windowManager.closeWindow(this);
    }
}