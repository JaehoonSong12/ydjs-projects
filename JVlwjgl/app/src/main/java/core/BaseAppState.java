package core;

import dev.lwjgl.ui.components.UIContainer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * An abstract base class for AppState implementations.
 * It provides a default UI hierarchy (view) and automatically
 * delegates mouse/update events to that UI.
 */
public abstract class BaseAppState implements AppState {
    /**
     * A reference to the GameContext (the main app) for
     * accessing shared resources and changing state.
     */
    protected GameContext context;

    /**
     * The root of the UI scene graph for this state.
     * This is the "Composite" pattern's root node.
     */
    protected UIContainer view;

    @Override
    public void init(GameContext context) {
        this.context = context;
        // Create a root UI container that fills the entire window
        this.view = new UIContainer(
                0, 0,
                context.getWindow().getWinW(),
                context.getWindow().getWinH()
        );
    }

    /**
     * Default update behavior:
     * Gets the current mouse position and tells the UI
     * view to update itself (e.g., to process hover states).
     */
    @Override
    public void update(double delta) {
        // Get current cursor position for hover updates
        double[] xd = new double[1], yd = new double[1];
        glfwGetCursorPos(context.getWindow().getWindowHandle(), xd, yd);

        // Propagate update to all UI components
        if (this.view != null) {
            this.view.update(xd[0], yd[0]);
        }
    }

    /**
     * Default render behavior:
     * Clears the screen with a default color and renders the UI view.
     * Subclasses should override this to set their own background
     * color and perform custom rendering *before* calling super.render().
     */
    @Override
    public void render() {
        if (this.view != null) {
            this.view.render();
        }
    }

    /**
     * Default mouse input behavior:
     * Delegates the mouse click to the UI view, which will
     * find which component (e.g., button) was clicked.
     */
    @Override
    public void onMouse(long window, int button, int action, int mods, double x, double y) {
        if (this.view != null) {
            this.view.handleMouse(x, y, button, action);
        }
    }

    // --- Default empty hooks ---

    @Override
    public void onEnter() {
        // By default, do nothing on enter
    }

    @Override
    public void onExit() {
        // By default, do nothing on exit
    }

    @Override
    public void onKey(long window, int key, int scancode, int action, int mods) {
        // By default, do nothing on key press
        // (but check for ESCAPE as a global quit)
        if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
            glfwSetWindowShouldClose(window, true);
        }
    }
}
