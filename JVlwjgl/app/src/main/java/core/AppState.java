package core;

/**
 * Defines the contract for a single "State" in the State Pattern.
 * Each state (e.g., Main Menu, Playing) implements this interface
 * to handle its own logic, rendering, and input.
 */
public interface AppState {
    /**
     * Called once when the state is first set.
     *
     * @param context A reference to the main GameContext.
     */
    void init(GameContext context);

    /**
     * Called when this state becomes the active state.
     * Use this to set up UI, change window titles, etc.
     */
    void onEnter();

    /**
     * Called when this state is no longer the active state.
     * Use this for any cleanup.
     */
    void onExit();

    /**
     * Called every frame by the main loop.
     *
     * @param delta Time elapsed since the last frame.
     */
    void update(double delta);

    /**
     * Called every frame by the main loop to draw the screen.
     */
    void render();

    /**
     * Delegate method for keyboard input.
     * Called by the main app's GLFW callback.
     */
    void onKey(long window, int key, int scancode, int action, int mods);

    /**
     * Delegate method for mouse button input.
     * Called by the main app's GLFW callback.
     */
    void onMouse(long window, int button, int action, int mods, double x, double y);
}
