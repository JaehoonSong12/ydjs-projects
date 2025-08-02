/**
INSTRUCTIONS: 
    This is a Java Swing application that 

COMPILE & EXECUTE & CLEANUP (Java):

     gradle build
     gradle run
     gradle clean
 */
package example;

import java.util.HashMap;
import java.util.Map;






/**
 * Defines the contract for any renderable UI component.
 * Implementations must provide their own rendering logic.
 */
interface Component {
    /**
     * Render this component to the console (or any display medium).
     */
    void render();
}


// ----- Demo Components -----

/**
 * A demo component representing a Home screen.
 */
class HomeComponent implements Component {
    /**
     * Render the Home screen message.
     */
    @Override
    public void render() {
        System.out.println("[HOME] Welcome!");
    }
}

/**
 * A demo component representing Settings.
 */
class SettingsComponent implements Component {
    /**
     * Render the Settings screen message.
     */
    @Override
    public void render() {
        System.out.println("[SETTINGS] Toggle options here.");
    }
}

/**
 * A demo component representing an About dialog.
 */
class AboutComponent implements Component {
    /**
     * Render the About screen message.
     */
    @Override
    public void render() {
        System.out.println("[ABOUT] Version 1.0");
    }
}
















/**
 * Strategy interface for layout management.
 * 
 * <p>Clients (e.g., {@link Container}) hold a reference to a LayoutManager
 * and delegate calls to it. This is the Strategy pattern:
 * different layout managers can be plugged in at runtime.</p>
 */
interface LayoutManager {
    /**
     * Add a component to the layout under a given name.
     * 
     * @param name a unique key identifying the component within this layout
     * @param comp the component to register
     */
    void addLayoutComponent(String name, Component comp);

    /**
     * Show (activate) the component registered under the given name.
     * 
     * @param parent the container that holds this layout
     * @param name   the name of the component to display
     */
    void show(Container parent, String name);
}

/**
 * A plain-Java implementation of a card-based layout strategy.
 * Keeps a map from name to Component and only renders the active one.
 * 
 * <p>This is a ConcreteStrategy in the Strategy pattern.</p>
 */
class CardLayout implements LayoutManager {
    private final Map<String, Component> cards = new HashMap<>();
    private String currentKey;

    /**
     * Registers a new card under the specified name. If this is the first
     * card, it becomes the current selection by default.
     *
     * @param name a unique identifier for the card
     * @param comp the Component to associate with that name
     */
    @Override
    public void addLayoutComponent(String name, Component comp) {
        cards.put(name, comp);
        if (currentKey == null) currentKey = name;
    }

    /**
     * Activates and renders the component registered under the given name.
     * If no component is found, prints an error message.
     *
     * @param parent the Container using this layout manager
     * @param name   the name of the card to show
     */
    @Override
    public void show(Container parent, String name) {
        Component c = cards.get(name);
        if (c == null) {
            System.out.println("No card under \"" + name + "\"");
            return;
        }
        currentKey = name;
        c.render();
    }
}









/**
 * A UI container that holds Components and delegates layout operations
 * to its current LayoutManager.
 * 
 * <p>This is the Context in the Strategy pattern; it is configured
 * with a LayoutManager and delegates add/show operations to it.</p>
 */
class Container {
    private LayoutManager layout;

    /**
     * Sets the layout manager (strategy) for this container.
     *
     * @param layout a LayoutManager implementation
     */
    public void setLayout(LayoutManager layout) {
        this.layout = layout;
    }

    /**
     * Add a component to this container under the given name.
     * Delegates to the current LayoutManager.
     *
     * @param comp the Component to add
     * @param name the name under which to register it
     */
    public void add(Component comp, String name) {
        layout.addLayoutComponent(name, comp);
    }

    /**
     * Returns the layout manager currently used by this container.
     * Callers can use it to invoke show().
     *
     * @return the LayoutManager assigned to this container
     */
    public LayoutManager getLayout() {
        return layout;
    }
}















// ----- Demo Main -----

/**
 * Demonstrates usage of the CardLayout strategy within a Container.
 * 
 * <p>Steps:
 * <ol>
 *   <li>Create a CardLayout and a Container.</li>
 *   <li>Set the CardLayout as the container's layout manager.</li>
 *   <li>Register several Components under string keys.</li>
 *   <li>Show each card by its key (including an invalid key to show error handling).</li>
 * </ol>
 * </p>
 */
public class Starategy {
    public static void main(String[] args) {
        // 1) Create layout and container
        CardLayout cardLayout = new CardLayout();
        Container container   = new Container();
        container.setLayout(cardLayout);

        // 2) Register components
        container.add(new HomeComponent(),     "home");
        container.add(new SettingsComponent(), "settings");
        container.add(new AboutComponent(),    "about");

        // 3) Flip cards exactly like Swing:
        cardLayout.show(container, "home");      // prints Home
        cardLayout.show(container, "settings");  // prints Settings
        cardLayout.show(container, "about");     // prints About
        cardLayout.show(container, "missing");   // prints error
    }
}
