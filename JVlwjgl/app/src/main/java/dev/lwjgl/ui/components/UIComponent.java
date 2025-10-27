package dev.lwjgl.ui.components;

import static org.lwjgl.opengl.GL11.*;


import dev.lwjgl.ui.*;

/**
 * The abstract base class for all UI elements. (Component)
 * This is the "Component" in the Composite Design Pattern.
 * It defines the common interface for both leaf nodes (like UIButton)
 * and composite nodes (UIContainer).
 */
public abstract class UIComponent {
    protected double x, y, w, h;
    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return w; }
    public double getHeight() { return h; }

    protected boolean visible = true;
    public boolean isVisible() { return this.visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    protected boolean enabled = true;
    public boolean isEnabled() { return this.enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    

    /**
     * Constructs a new UIComponent with its position and size.
     */
    public UIComponent(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    // --- Abstract methods to be implemented by all children ---
    /**
     * Renders the component to the screen.
     */
    public abstract void render();

    
    protected void renderRect(double x, double y, double w, double h) {
        glBegin(GL_QUADS);
            glVertex2d(x, y);
            glVertex2d(x + w, y);
            glVertex2d(x + w, y + h);
            glVertex2d(x, y + h);
        glEnd();
    }
    // -------- Overloads for float (2D)
    protected void renderRect(float x, float y, float w, float h) {
        glBegin(GL_QUADS);
            glVertex2f(x, y);
            glVertex2f(x + w, y);
            glVertex2f(x + w, y + h);
            glVertex2f(x, y + h);
        glEnd();
    }
    // -------- Overloads for int (2D)
    protected void renderRect(int x, int y, int w, int h) {
        glBegin(GL_QUADS);
            glVertex2i(x, y);
            glVertex2i(x + w, y);
            glVertex2i(x + w, y + h);
            glVertex2i(x, y + h);
        glEnd();
    }



    // -------- 3D versions (float)
    protected void renderRect3D(float x, float y, float z, float w, float h) {
        glBegin(GL_QUADS);
            glVertex3f(x, y, z);
            glVertex3f(x + w, y, z);
            glVertex3f(x + w, y + h, z);
            glVertex3f(x, y + h, z);
        glEnd();
    }
    // -------- 3D versions (double)
    protected void renderRect3D(double x, double y, double z, double w, double h) {
        glBegin(GL_QUADS);
            glVertex3d(x, y, z);
            glVertex3d(x + w, y, z);
            glVertex3d(x + w, y + h, z);
            glVertex3d(x, y + h, z);
        glEnd();
    }




    // --- Virtual methods (default behavior) ---

    /**
     * Updates the component's state (e.g., for hover effects).
     * @param mouseX Current mouse X position.
     * @param mouseY Current mouse Y position.
     */
    public void update(double mouseX, double mouseY) { }

    /**
     * Handles a mouse button event.
     * @param mouseX X position of the click.
     * @param mouseY Y position of the click.
     * @param button The mouse button (e.g., GLFW_MOUSE_BUTTON_LEFT).
     * @param action The action (e.g., GLFW_PRESS).
     * @return true if the event was consumed, false otherwise.
     */
    public boolean handleMouse(double mouseX, double mouseY, int button, int action) {
        // Default: do nothing, do not consume event
        return false;
    }

    // --- Concrete helper methods ---

    /**
     * Checks if a point (mx, my) is within the bounds of this component.
     */
    public boolean contains(double mx, double my) {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }
    
}

