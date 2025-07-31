/*
 * This is a Java Swing application that 
 */
package jaydenswing;

import javax.swing.*;
import java.awt.*;

public class Pro1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("First Swing App?");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter your name: ");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("um");

        button.addActionListener(e -> {
            String name = textField.getText();
            JOptionPane.showMessageDialog(frame, "hi, " + name + "!");
        });

        frame.add(label);
        frame.add(textField);
        frame.add(button);

        frame.setVisible(true);
    }
}
