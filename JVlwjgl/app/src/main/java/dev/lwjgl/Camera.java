package dev.lwjgl;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

/**
 * General-purpose 2D camera that manages viewport and projection.
 * Can follow a target entity or be positioned manually.
 */
public class Camera {
    private final int screenW, screenH;
    private final float worldW;
    private float x;
    
    public Camera(int screenWidth, int screenHeight, float worldWidth) {
        this.screenW = screenWidth;
        this.screenH = screenHeight;
        this.worldW = worldWidth;
        this.x = 0;
    }
    
    /**
     * Follows a target by centering the camera on the target's x position.
     * @param targetX The x position of the target to follow
     */
    public void follow(float targetX) {
        x = targetX - screenW / 2f;
        if (x < 0) x = 0;
        if (x > worldW - screenW) x = worldW - screenW;
    }
    
    /**
     * Sets the camera position directly.
     */
    public void setPosition(float x) {
        this.x = x;
        if (this.x < 0) this.x = 0;
        if (this.x > worldW - screenW) this.x = worldW - screenW;
    }
    
    /**
     * Gets the current camera x position.
     */
    public float getX() {
        return x;
    }
    
    /**
     * Begins the camera viewport. Call before rendering world objects.
     */
    public void begin() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(x, x + screenW, 0, screenH, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }
    
    /**
     * Ends the camera viewport. Call after rendering world objects.
     */
    public void end() {
        // Nothing needed - can be overridden for custom cleanup
    }
}

