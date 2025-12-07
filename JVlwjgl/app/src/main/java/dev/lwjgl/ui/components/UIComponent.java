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




    protected void renderPolygon(int n, double x, double y, double radius, double rotationalAngle) {
        glBegin(GL_POLYGON);
        double rot = Math.toRadians(rotationalAngle);
        for (int i = 0; i < n; i++) {
            double angle = rot + (2 * Math.PI * i / n);
            double vx = x + radius * Math.cos(angle);
            double vy = y + radius * Math.sin(angle);
            glVertex2d(vx, vy);
        }
        glEnd();
    }

    protected void renderStar(int n, double x, double y, double radius, double rotationalAngle) {
        double rot = Math.toRadians(rotationalAngle);
        
        // Inner radius for star valleys
        double innerRadius = radius * 0.4;

        // Render star using triangles from center - guarantees all tips are pointy
        glBegin(GL_TRIANGLES);
        
        for (int i = 0; i < n; i++) {
            // Current tip
            double tipAngle = rot + (2 * Math.PI * i / n);
            double tipX = x + radius * Math.cos(tipAngle);
            double tipY = y + radius * Math.sin(tipAngle);
            
            // Valley before this tip (between previous tip and current tip)
            double valleyAngle1 = rot + (2 * Math.PI * (i - 0.5) / n);
            double valleyX1 = x + innerRadius * Math.cos(valleyAngle1);
            double valleyY1 = y + innerRadius * Math.sin(valleyAngle1);
            
            // Valley after this tip (between current tip and next tip)
            double valleyAngle2 = rot + (2 * Math.PI * (i + 0.5) / n);
            double valleyX2 = x + innerRadius * Math.cos(valleyAngle2);
            double valleyY2 = y + innerRadius * Math.sin(valleyAngle2);
            
            // Render two triangles for this star arm: center -> valley1 -> tip, and center -> tip -> valley2
            glVertex2d(x, y);           // Center
            glVertex2d(valleyX1, valleyY1); // Valley before tip
            glVertex2d(tipX, tipY);      // Tip (pointy!)
            
            glVertex2d(x, y);           // Center
            glVertex2d(tipX, tipY);      // Tip (pointy!)
            glVertex2d(valleyX2, valleyY2); // Valley after tip
        }
        
        glEnd();
    }




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

