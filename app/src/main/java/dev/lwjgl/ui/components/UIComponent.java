package dev.lwjgl.ui.components;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glVertex2i;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glVertex3f;

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
    
    // ---------- GLOW ----------
    protected boolean isGlowing = false;
    protected double glowSpeed = 0.000000005;  // Speed of pulse animation (unchanged)
    protected float glowMin = 0.05f; // minimum alpha value - closer to max for smaller increments
    protected float glowMax = 0.08f; // maximum alpha value - closer to min for smaller increments
    
    protected Integer maxGlowPulses = null; // null = unlimited ????? max number of pulses
    protected int glowCounter = 0; // count the number of pulses
    
    // Core brightness pulse
    protected boolean coreBrightnessPulse = false; // if true, the core brightness will pulse
    protected double pulseAmount = 0.15;     // how much brighter (0.3 = 30%) ????? how much brighter
    protected double pulseSpeed = 0.00000001;  // Slower for smoother, more gradual brightness changes ????? time between pulses

    public void setGlowing(boolean glowing) {
        this.isGlowing = glowing;
        if (glowing) glowCounter = 0;
    }

    public void setMaxGlowPulses(Integer maxPulses) {
        this.maxGlowPulses = maxPulses;
        glowCounter = 0;
    }

    public void setCoreBrightnessPulse(boolean pulse) {
        this.coreBrightnessPulse = pulse;
    }

    /**
     * Calculates the glow pulse value (0.0 to 1.0) based on current time.
     * @return Pulse value between 0.0 and 1.0
     */
    protected float calculateGlowPulse() {
        float time = (float) (System.nanoTime() * glowSpeed);
        return (float) (Math.sin(time) * 0.5 + 0.5);
    }
    protected float shapePulse(float pulse) {
        float exponent = 4.8f; // increase for more visible steps
        return (float) Math.pow(pulse, exponent);
    }

    /**
     * Calculates the core brightness multiplier based on coreBrightnessPulse setting.
     * @return Brightness multiplier (1.0f if pulse is disabled, otherwise varies)
     */
    protected float calculateCoreBrightness() {
        if (!coreBrightnessPulse) {
            return 1.0f;
        }
        float coreTime = (float) (System.nanoTime() * pulseSpeed);
        float pulse = (float) (Math.sin(coreTime) * 0.5 + 0.5); // 0  1  0
        return 1.0f + (float)(pulseAmount * pulse);
    }

    /**
     * Updates glow state based on pulse. Should be called by children after rendering glow.
     * @param pulse The current pulse value (0.0 to 1.0)
     */
    protected void updateGlowState(float pulse) {
        if (pulse > 0.95f) glowCounter++;
        if (maxGlowPulses != null && glowCounter >= maxGlowPulses) isGlowing = false;
    }


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

