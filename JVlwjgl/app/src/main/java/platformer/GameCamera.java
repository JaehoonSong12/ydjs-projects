package platformer;

import dev.lwjgl.Camera;

/**
 * Camera wrapper that follows the player.
 * Uses the framework Camera class for viewport management.
 */
public class GameCamera {
    private final Camera camera;
    
    public GameCamera(int screenWidth, int screenHeight, float worldWidth) {
        this.camera = new Camera(screenWidth, screenHeight, worldWidth);
    }
    
    /**
     * Follows the player by centering the camera on the player's x position.
     */
    public void follow(Player p) {
        camera.follow(Player.x);
    }
    
    /**
     * Begins the camera viewport. Call before rendering world objects.
     */
    public void begin() {
        camera.begin();
    }
    
    /**
     * Ends the camera viewport. Call after rendering world objects.
     */
    public void end() {
        camera.end();
    }
}

