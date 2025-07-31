package core;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * ViewRouter is a Singleton responsible for centralized navigation 
 * between views (JPanels) in the application.
 * <p>
 * <b>Design Pattern:</b> Singleton, Router (Controller in MVC for navigation)
 * <br>
 * 
 * The Router design pattern centralizes the decision of "which handler should 
 * process this request" by mapping incoming requests 
 * (messages, UI events, HTTP calls, etc.) to the appropriate 
 * processing component. It's widely used in web frameworks 
 * (Express, Spring MVC), in UI navigation (React Router), and 
 * in message-driven systems.
 * 
 * It owns the main JFrame and CardLayout container, allowing controllers 
 * and views to switch screens and manage the window.
 */
public class ViewRouter {
    /** Singleton instance of the ViewRouter. */
    private static ViewRouter instance;

    /** The main application window. */
    private final JFrame frame;
    /** The CardLayout used for switching views. */
    private final CardLayout layout;
    /** The CardLayout container holding all views. */
    private final JPanel container;
    /** Map of view names to their JPanel instances. */
    private final Map<String, JPanel> views = new HashMap<>();

    /**
     * Private constructor for Singleton pattern. Initializes the JFrame and layout.
     */
    private ViewRouter() {
        frame = new JFrame("MVC with Singleton Navigation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        layout = new CardLayout();
        container = new JPanel(layout);
        frame.setContentPane(container);
    }

    /**
     * Returns the singleton instance of the ViewRouter.
     * @return the ViewRouter instance
     */
    public static synchronized ViewRouter getInstance() {
        if (instance == null) {
            instance = new ViewRouter();
        }
        return instance;
    }

    /**
     * Registers a view (JPanel) with a unique name for navigation.
     * @param name the unique name for the view
     * @param view the JPanel instance
     */
    public void addView(String name, JPanel view) {
        views.put(name, view);
        container.add(view, name);
    }

    /**
     * Registers a view (JPanel) with a unique name for navigation.
     * @param key the unique key (URL) for the view     e.g. https://www.google.com/account
     * @param view the JPanel instance                    e.g. new InfoView()
     */
    public void routeView(String key, JPanel value) {
        container.add(value, key);
    }


    /**
     * Switches to the view registered with the given name and ensures the window is visible.
     * @param name the name of the view to show
     */
    public void showView(String name) {
        layout.show(container, name);
        if (!frame.isVisible()) frame.setVisible(true);
    }

    /**
     * Returns the main application JFrame for window management.
     * @return the JFrame
     */
    public JFrame getFrame() {
        return frame;
    }


    public void setFrame(String windowTitle, int width, int height) {
        frame.setTitle(windowTitle);
        frame.setSize(width, height);
    }
} 