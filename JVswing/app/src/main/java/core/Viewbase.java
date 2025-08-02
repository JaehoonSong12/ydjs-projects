package core;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;



/**
 * The view constructs and exposes UI components.
 */
public class Viewbase extends JPanel {
    public String getPath() {
        String path = "";
        try {
            path = "/" + getClass().getName().replace('.', '/') + ".class";
            // // 3A. Locate via Class.getResource (absolute lookup)
            // java.net.URL urlViaClass = ClassLocationDemo.class.getResource(resourcePath);
            // System.out.println("URL via Class.getResource: " + urlViaClass);
            System.out.println("URL (registered): " + path);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return path;
    }
}