package core;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerLoader {
    private ViewLoader view;

    public ControllerLoader(ViewLoader view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getBtnRefresh().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDownloads();
            }
        });

        view.getBtnOpen().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSelectedVideo();
            }
        });

        view.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedVideo();
            }
        });
    }

    private void loadDownloads() {
        // Empty for now - will be implemented when download system is ready
        DefaultTableModel model = (DefaultTableModel) view.getTableDownloads().getModel();
        model.setRowCount(0);
    }

    private void openSelectedVideo() {
        int row = view.getTableDownloads().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Please select a video to open.");
        }
    }

    private void deleteSelectedVideo() {
        int row = view.getTableDownloads().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Please select a video to delete.");
        }
    }
}
