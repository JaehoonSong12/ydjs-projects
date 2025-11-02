package dev.lwjgl.ui.components.controls;

import dev.lwjgl.ui.Colors;
import dev.lwjgl.ui.components.UIComponent;
import dev.lwjgl.ui.components.UILabel;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.opengl.GL11.*;

/**
 * A clickable button component. (Leaf)
 * This is a "Leaf" in the Composite Design Pattern.
 * It contains a UILabel, handles hover states, and
 * executes a callback when clicked.
 */
public class UIButton extends UIComponent {

    private UILabel label;
    private boolean hover = false;
    private final Runnable onClick; // The action to perform on click

    // --- Style properties ---
    private final float[] bgColor = {0.12f, 0.30f, 0.12f};
    private final float[] hoverColor = {0.18f, 0.45f, 0.18f};
    private final float[] borderColor = {0.95f, 0.95f, 0.95f};

    /**
     * Creates a new UIButton.
     *
     * @param labelText The text to display on the button.
     * @param x         The X position.
     * @param y         The Y position.
     * @param w         The width.
     * @param h         The height.
     * @param onClick   A Runnable to execute when the button is clicked.
     */
    public UIButton(String labelText, double x, double y, double w, double h, Runnable onClick) {
        super(x, y, w, h, Colors.GRAY);
        this.onClick = onClick;

        // Create a label, but its position will be managed
        // by the button's centerLabel() method.
        this.label = new UILabel(labelText, 0, 0, 3);
        centerLabel();
    }

    /**
     * Internal helper to (re)center the label inside the button.
     */
    private void centerLabel() {
        this.label.centerHorizontal(this.x, this.w);
        this.label.centerVertical(this.y, this.h);
    }

    /**
     * Renders the button's background, border, and text label.
     */
    @Override
    public void render() {
        if (!visible) return;

        // 1. Draw background
        if (hover && enabled) {
            Colors.setColor(hoverColor);
        } else {
            Colors.setColor(bgColor);
        }

        glBegin(GL_QUADS);
        glVertex2d(x, y);
        glVertex2d(x + w, y);
        glVertex2d(x + w, y + h);
        glVertex2d(x, y + h);
        glEnd();

        // 2. Draw border
        Colors.setColor(borderColor);
        glLineWidth(1.5f);
        glBegin(GL_LINE_LOOP);
        glVertex2d(x + 1, y + 1);
        glVertex2d(x + w - 1, y + 1);
        glVertex2d(x + w - 1, y + h - 1);
        glVertex2d(x + 1, y + h - 1);
        glEnd();

        // 3. Draw the label (which is a child component)
        this.label.render();
    }

    /**
     * Updates the button's hover state.
     */
    @Override
    public void update(double mouseX, double mouseY) {
        if (!visible || !enabled) {
            this.hover = false;
            return;
        }
        this.hover = contains(mouseX, mouseY);
    }

    /**
     * Handles mouse clicks. If this button is clicked,
     * it executes its onClick callback and consumes the event.
     */
    @Override
    public boolean handleMouse(double mouseX, double mouseY, int button, int action) {
        if (!visible || !enabled) return false;

        // Check for a left-click press inside our bounds
        if (button == GLFW_MOUSE_BUTTON_LEFT &&
                action == GLFW_PRESS &&
                contains(mouseX, mouseY)) {
            // We were clicked!
            if (onClick != null) {
                onClick.run();
            }
            // Consume the event so no components "under" this button get it.
            return true;
        }

        // Event was not for us
        return false;
    }
}
