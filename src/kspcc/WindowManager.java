package kspcc;

import javax.swing.*;
import javax.accessibility.*;
import java.awt.*;
import java.util.ArrayList;

class WindowManager
{
    public ArrayList<JFrame> windowList;
    public ArrayList<Boolean> openMask;

    public WindowManager()
    {
        windowList = new ArrayList<>();
        openMask = new ArrayList<>();
    }

    public void addWindow(JFrame window)
    {
        windowList.add(window);
        openMask.add(false);
    }

    public void openWindow(JFrame window)
    {
        window.setVisible(true);
        window.setState(Frame.NORMAL);
        openMask.set(windowList.indexOf(window), true);
    }

    public void closeWindow(JFrame window)
    {
        window.setVisible(false);
        openMask.set(windowList.indexOf(window), false);
    }

    public void toggleWindow(JFrame window)
    {
        if (window.isVisible() && window.getState() == Frame.NORMAL)
        {
            closeWindow(window);
        }else
        {
            openWindow(window);
        }
    }

    public void openWindow(int index)
    {
        windowList.get(index).setVisible(true);
        windowList.get(index).setState(Frame.NORMAL);
        openMask.set(index, true);
    }

    public void closeWindow(int index)
    {
        windowList.get(index).setVisible(false);
        openMask.set(index, false);
    }

    public void toggleWindow(int index)
    {
        if (windowList.get(index).isVisible() && windowList.get(index).getState() == Frame.NORMAL)
        {
            closeWindow(index);
        } else
        {
            openWindow(index);
        }
    }

    public boolean isWindowOpen(JFrame window)
    {
        return window.isVisible();
    }

    public boolean isWindowOpen(int index)
    {
        return openMask.get(index);
    }

    public void destroyAllWindows()
    {
        while (!windowList.isEmpty())
        {
            windowList.get(0).dispose();
            windowList.remove(0);
        }
    }

    public void updateAllWindowColors(BG bg)
    {
        for (JFrame window : windowList)
        {
            recurseUpdateWindowColors(bg, window);
        }
    }

    public void recurseUpdateWindowColors(BG bg, Container container)
    {
        container.setBackground(bg.BGCOLOR);
        container.setForeground(bg.FONTCOLOR);
        if (container.getComponentCount() <= 0)
        {
            return;
        }
        for (Component component : container.getComponents())
        {
            if (component instanceof Container)
            {
                recurseUpdateWindowColors(bg, (Container) component);
            }
        }
    }

}