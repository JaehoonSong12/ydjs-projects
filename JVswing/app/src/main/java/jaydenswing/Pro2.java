/*
 * This is a Java Swing application that 
 */
package jaydenswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pro2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Try to close");
        frame.setSize(800,800);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                // force window back to fixed location
                frame.setLocation(500, 300);
            }
        });


        JLabel label = new JLabel("Guess the key: ");
        JTextField textField = new JTextField(20);
        JButton exitButton = new JButton("Exit");
        

        exitButton.addActionListener(e -> {
            String key = textField.getText();
            if (key.equals("abc123")) {
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(frame, "Try again jaja");
            }
        });

        frame.addWindowStateListener(e -> {
            if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                frame.setState(Frame.NORMAL);
            }
        });

        frame.add(label);
        frame.add(textField);
        frame.add(exitButton);

        frame.setVisible(true);
    }
}