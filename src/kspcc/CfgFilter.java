package kspcc;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class CfgFilter extends FileFilter
{
    public boolean accept(File f)
    {
        if (f.isDirectory())
        {
            return true;
        }
        String extension = null;
        int i = f.getName().lastIndexOf(".");
        if (i > 0 &&  i < f.getName().length() - 1)
        {
            extension = f.getName().substring(i).toLowerCase();
        }
        if (extension != null)
        {
            if (extension.equals(".cfg"))
            {
                return true;
            } else
            {
                return false;
            }
        }
        return false;
    }


    public String getDescription()
    {
        return "Kopernicus cfg Files";
    }
    
}
