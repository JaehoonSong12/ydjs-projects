package core;

import dev.lwjgl.UIWindow;

/**
 * Defines the contract for the "Context" of the State Pattern.
 * This interface is implemented by SnakeGameApp and passed to each
 * AppState, allowing states to access shared resources (like the window)
 * and to request a state change (via setState).
 */
public interface GameContext {
    /**
     * Requests a change to a new application state.
     *
     * @param newState The state to transition to.
     */
    void setState(AppState newState);

    /**
     * Gets the main application window wrapper.
     *
     * @return The UIWindow instance.
     */
    UIWindow getWindow();

    /**
     * Gets the current Snake game model.
     *
     * @return The SnakeModel instance, or null if one is not active.
     */
    SnakeModel getSnakeModel();

    /**
     * Creates a new, fresh SnakeModel.
     */
    void createNewSnakeModel();

    // --- Accessors for game grid configuration ---
    int getGridW();

    int getGridH();

    int getCellSize();
}
