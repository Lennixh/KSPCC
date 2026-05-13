package kspcc;

import java.awt.*;
import java.awt.event.*;

public class KeyMaster
{
    private static volatile boolean escPressed = false;

    public static boolean isEscPressed()
    {
        synchronized (KeyMaster.class)
        {
            return escPressed;
        }
    }

    public static void init ()
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher()
        {

            public boolean dispatchKeyEvent(KeyEvent ke)
            {
                synchronized (KeyMaster.class)
                {
                    switch (ke.getID())
                    {
                        case KeyEvent.KEY_PRESSED:
                            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
                            {
                                escPressed = true;
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
                            {
                                escPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            }
        });
    }
}