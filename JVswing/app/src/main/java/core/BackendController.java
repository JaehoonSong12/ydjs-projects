package core;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BackendController {
    private BackendView view;

    public BackendController(BackendView view) {
        this.view = view;
        
        
        view.getBtnGo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String raw = view.getTxfUrl().getText();
                if (!raw.equals(BackendView.PLACEHOLDER) && !raw.isBlank()) {
                    JOptionPane.showMessageDialog(
                        view,
                        "URL set to: " + raw,
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        view,
                        "Please enter a valid URL.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
    }
}
