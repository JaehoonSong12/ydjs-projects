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
    // Router Desgin Pattern
    private final JFrame frame;                     // (1) Window of the Program Session        (Stage)
    public JFrame getFrame() { return this.frame; }
    private void setFrame(String windowTitle, int width, int height) {
        this.frame.setTitle(windowTitle);
        this.frame.setSize(width, height);
    }
    private final JPanel container;                 // (2) Session holding all screens (views)  (Scene)
    private final CardLayout layout;                // (3) Layout switching all screens (views) (Pane)
    private final Map<String, JPanel> viewRoutes;   // (4) data structure to map (route) of views
    /**
     * Registers a view (JPanel) with a unique URL (key) for navigation.
     * @param key the unique URL (key) for the view (value)     e.g. https://www.google.com/account
     * @param view the JPanel instance                          e.g. new InfoView()
     */
    public void registerRoute(String url, JPanel view) {
        container.add(view, url);
        viewRoutes.put(url, view);
    }
    /**
     * Switches to the view registered with the given name and ensures the window is visible.
     * @param key the unique URL (key) of the view to show
     */
    public void showView(String url) {
        layout.show(container, url);
        JPanel view = viewRoutes.get(url);
        System.out.println(view);
        // this.setFrame(view.TITLE, view.SCREEN__WIDTH, view.SCREEN_HEIGHT);
        if (!frame.isVisible()) frame.setVisible(true);
    }





    // Singleton Desgin Pattern
    private static ViewRouter instance;
    /**
     * Private constructor for Singleton pattern. (cannot be called from outside.)
     */
    private ViewRouter() {
        this.frame = new JFrame("MVC with Singleton Navigation");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(400, 200);
        this.frame.setLocationRelativeTo(null);

        this.layout = new CardLayout();
        this.container = new JPanel(layout);
        this.viewRoutes = new HashMap<>();
        this.frame.setContentPane(container);
    }
    /**
     * Returns the singleton instance of the ViewRouter.
     * @return the ViewRouter instance
     */
    public static synchronized ViewRouter getInstance() { 
        if (instance == null) instance = new ViewRouter();
        return instance;
    }
    /**
     * dynmaic vs static
     * static (need to type): In CS, static means "compile-time" (or "before running"). So, if you make your 
     * variable or function static, they will be concretely defined (memory space actually took place 
     * within .class file by javac command) as soon as you run your program.
     * 
     * dynamic (no need to type): In CS, dynamic means "run-time" (or "after running"). So, if you make your
     * attribute or method dynamic, they will be defined only after you call "new" keyword. Moreover, 
     * "new" keyword calls the class's constructor to build up the memory space and return the completed 
     * object's address.
     */
} 