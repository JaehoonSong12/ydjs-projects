package core;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewLoader extends JPanel implements Displayable {
    public static String getUrl() {
        // return new ViewLinker().getPath();
        String path = "/" + new Object() {}.getClass().getEnclosingClass().getName().replace('.', '/') + ".class";
        System.out.println("URL (registered statically): " + path);
        return path;
    }

    @Override
    public String getTitle() { 
        return TITLE; 
    }
    private static final String TITLE = "Downloaded Videos";

    @Override
    public int getWidth() { 
        return SCREEN_WIDTH; 
    }
    private static final int SCREEN_WIDTH = 680;

    @Override
    public int getHeight() { 
        return SCREEN_HEIGHT; 
    }
    private static final int SCREEN_HEIGHT = 480;

    private JTable tableDownloads;
    public JTable getTableDownloads() { return tableDownloads; }

    private JButton btnOpen;
    public JButton getBtnOpen() { return btnOpen; }

    private JButton btnDelete;
    public JButton getBtnDelete() { return btnDelete; }

    private JButton btnRefresh;
    public JButton getBtnRefresh() { return btnRefresh; }

    public ViewLoader() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top title panel
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel lblTitle = new JLabel("Downloaded Videos");
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 24f));
        top.add(lblTitle);
        add(top, BorderLayout.NORTH);

        // Table for downloads - empty initially
        String[] columns = {"Title", "Duration", "Size", "Date Downloaded"};
        Object[][] data = {};
        tableDownloads = new JTable(new DefaultTableModel(data, columns));
        tableDownloads.setFillsViewportHeight(true);
        add(new JScrollPane(tableDownloads), BorderLayout.CENTER);

        // Buttons at the bottom
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnOpen = new JButton("Open");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");
        bottom.add(btnOpen);
        bottom.add(btnDelete);
        bottom.add(btnRefresh);
        add(bottom, BorderLayout.SOUTH);
    }
}
