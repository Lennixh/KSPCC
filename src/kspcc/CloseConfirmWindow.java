package kspcc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class CloseConfirmWindow extends JFrame {

    private KSPCC master;

    public CloseConfirmWindow(KSPCC daddy, BG bg) {

        master = daddy;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                master.confirmOpen = false;
                close();
            }
        });

        setTitle("Confirm closing?");
        JPanel elementContainer = new JPanel();
        elementContainer.setBackground(bg.BGCOLOR);
        elementContainer.setForeground(bg.FONTCOLOR);
        elementContainer.setPreferredSize(new Dimension(200, 100));

        JTextArea question = new JTextArea("Do you really want to\n" +
                                           "exit the application?");
        question.setBackground(bg.BGCOLOR);
        question.setForeground(bg.FONTCOLOR);
        question.setFont(bg.GLOBALFONT.deriveFont(Font.BOLD, 16f));

        elementContainer.add(question);

        JButton closeButton = new JButton("Confirm");
        closeButton.setBackground(bg.BGCOLOR);
        closeButton.setForeground(bg.FONTCOLOR);
        closeButton.setFont(bg.GLOBALFONT.deriveFont(Font.BOLD, 16f));
        closeButton.addActionListener(e -> {
            daddy.f.dispose();
            dispose();
        });

        elementContainer.add(closeButton);

        add(elementContainer);
        pack();
        setLocation(860, 440);
    }

    public void open() {
        setVisible(true);
        master.confirmOpen = true;
    }

    public void close() {
        setVisible(false);
        master.confirmOpen = false;
    }

}