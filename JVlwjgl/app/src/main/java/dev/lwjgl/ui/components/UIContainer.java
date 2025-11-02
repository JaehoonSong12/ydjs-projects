package dev.lwjgl.ui.components;

import dev.lwjgl.ui.Colors;

import java.util.ArrayList;
import java.util.List;

/**
 * A UIComponent that can contain other UIComponents. (Composite)
 * This is the "Composite" in the Composite Design Pattern.
 * Its render, update, and handleMouse methods delegate
 * to all of its children.
 */
public class UIContainer extends UIComponent {

    /**
     * The list of child components.
     */
    protected List<UIComponent> children = new ArrayList<>();

    public UIContainer(double x, double y, double w, double h) {
        super(x, y, w, h, Colors.GRAY);
    }

    /**
     * Adds a child component to this container.
     */
    public void add(UIComponent component) {
        if (component != null) {
            children.add(component);
        }
    }

    /**
     * Removes a child component from this container.
     */
    public void remove(UIComponent component) {
        children.remove(component);
    }

    /**
     * Removes all child components.
     */
    public void clear() {
        children.clear();
    }

    /**
     * Renders this container (optional) and all visible children.
     */
    @Override
    public void render() {
        if (!visible) return;

        // --- Optional: Render container background ---
        // (We leave this transparent by default)
        // glColor3f(0.1f, 0.1f, 0.1f);
        // glBegin(GL_QUADS);
        // ...
        // glEnd();

        // --- Render all children ---
        for (UIComponent child : children) {
            child.render();
        }
    }

    /**
     * Updates all enabled children.
     */
    @Override
    public void update(double mouseX, double mouseY) {
        if (!visible || !enabled) return;

        for (UIComponent child : children) {
            child.update(mouseX, mouseY);
        }
    }

    /**
     * Passes mouse events to children.
     * Iterates in reverse so that components rendered
     * "on top" (added last) get the click event first.
     */
    @Override
    public boolean handleMouse(double mouseX, double mouseY, int button, int action) {
        if (!visible || !enabled) return false;

        // Iterate in reverse (top-most components first)
        for (int i = children.size() - 1; i >= 0; i--) {
            UIComponent child = children.get(i);
            if (child.handleMouse(mouseX, mouseY, button, action)) {
                // If a child consumed the event, stop propagating
                return true;
            }
        }

        // If no child consumed it, check if this container
        // itself should (e.g., for a "click-off" event).
        // Default: no.
        return false;
    }
}
