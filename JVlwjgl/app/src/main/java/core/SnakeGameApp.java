/*
 * Refactored Java LWJGL Snake Game Application
 * * This file contains a complete refactoring of the original single-file
 * application. The primary goals of this refactor are:
 * 1.  **Abstraction:** Separate concerns using strong design patterns.
 * 2.  **State Management:** Replace the global 'state' integer with the 
 * State Design Pattern (AppState, GameContext).
 * 3.  **UI Framework:** Implement the Composite Design Pattern (UIComponent,
 * UIContainer) to create a hierarchical UI system, as hinted at
 * by the placeholder classes in the original file.
 * 4.  **Single Responsibility Principle (SRP):** Each class now has a
 * clear, distinct purpose.
 * -   SnakeGameApp: The main application, acts as the "GameContext".
 * -   UIWindow: Manages the GLFW window and OpenGL context.
 * -   AppState (and implementations): Manage logic for a single
 * game state (Menu, Playing, GameOver).
 * -   UIComponent/UIContainer/Leafs: Manage the UI hierarchy.
 * -   BitmapFont: A Singleton utility for rendering text.
 * -   SnakeModel: Unchanged, already a good model class.
 * -   Colors: Unchanged utility class.
 * 5.  **Encapsulation:** Remove hard-coded logic from the main loop and
 * place it within the responsible classes (e.g., rendering logic
 * is moved into the corresponding AppState).
 * * All code is in a single file for simplicity, matching the original format.
 * All explanations are provided as Javadoc or inline/block comments as
 * requested.
 */
package core;

// Imports from the original file
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWErrorCallback;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import dev.lwjgl.ui.components.*;
import dev.lwjgl.UIWindow;
import dev.lwjgl.ui.components.controls.UIButton;

// ========================================================================
// 1. APPLICATION AND STATE MANAGEMENT (State Pattern)
// ========================================================================


/**
 * The main application class.
 * This class is responsible for:
 * - Initializing LWJGL and the UIWindow.
 * - Running the main game loop.
 * - Managing the current game state (State Pattern Context).
 * - Handling and delegating input callbacks to the current state.
 *
 * It implements GameContext to provide states with a controlled
 * way to access shared resources and request state changes.
 */
public class SnakeGameApp implements GameContext {

    /**
     * The main entry point for the application.
     * Creates an instance of the app and runs it.
     */
    public static void main(String[] args) {
        new SnakeGameApp().run();
    }

    // --- GameContext Shared Resources ---
    private UIWindow window;


    private AppState currentState;
    public AppState getCurrentState() {
        return currentState;
    }

    private SnakeModel snakeModel;

    // --- Game Configuration ---
    private int gridW;
    private int gridH;
    private int cellSize;

    /**
     * Public method to start the application's lifecycle.
     */
    public void run() {
        try {
            init();
            loop();
        } catch (Exception e) {
            System.err.println("An unrecoverable error occurred:");
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    /**
     * Initializes the application, window, and callbacks.
     * Sets the initial game state.
     */
    private void init() {
        // Init GLFW
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create the window
        this.window = new UIWindow("Snake Game", 640, 480);
        this.cellSize = 20;
        this.gridW = this.window.getWinW() / this.cellSize;
        this.gridH = this.window.getWinH() / this.cellSize;

        // Setup input callbacks to delegate to the current state
        setupCallbacks();

        // Set the initial state to the Main Menu
        this.setState(new MainMenuState());
    }

    /**
     * Configures GLFW callbacks to delegate all input
     * to the 'currentState' object.
     */
    private void setupCallbacks() {
        long windowHandle = window.getWindowHandle();

        // Key callback
        glfwSetKeyCallback(windowHandle, (win, key, scancode, action, mods) -> {
            if (this.currentState != null) {
                this.currentState.onKey(win, key, scancode, action, mods);
            }
        });

        // Mouse Button callback
        glfwSetMouseButtonCallback(windowHandle, (win, button, action, mods) -> {
            if (this.currentState != null) {
                // Get cursor position at the time of the click
                double[] xd = new double[1], yd = new double[1];
                glfwGetCursorPos(win, xd, yd);
                this.currentState.onMouse(win, button, action, mods, xd[0], yd[0]);
            }
        });
    }

    /**
     * The main game loop.
     * Calculates delta time and delegates updating and rendering
     * to the current state.
     */
    private void loop() {
        double lastTime = glfwGetTime();

        while (!glfwWindowShouldClose(window.getWindowHandle())) {
            // Calculate delta time
            double now = glfwGetTime();
            double delta = now - lastTime;
            lastTime = now;

            // --- Update ---
            // The current state handles all update logic,
            // including UI updates (like hover) and game logic.
            if (this.currentState != null) {
                this.currentState.update(delta);
            }

            // --- Render ---
            // The current state handles all rendering.
            if (this.currentState != null) {
                this.currentState.render();
            }

            // Swap buffers and poll events
            glfwSwapBuffers(window.getWindowHandle());
            glfwPollEvents();
        }
    }

    /**
     * Cleans up resources on exit.
     */
    private void cleanup() {
        // Destroy the window
        if (this.window != null) {
            this.window.destroy();
        }

        // Terminate GLFW
        glfwTerminate();
        glfwSetErrorCallback(null).free();
        System.exit(0);
    }

    // ======================================================
    // Implementation of GameContext (The "Context" in State Pattern)
    // ======================================================

    /**
     * Changes the current application state.
     * This is the core of the State Pattern.
     *
     * @param newState The new state to switch to.
     */
    @Override
    public void setState(AppState newState) {
        // Call the exit hook for the old state
        if (this.currentState != null) {
            this.currentState.onExit();
        }

        // Set the new state
        this.currentState = newState;

        // Call the init and enter hooks for the new state
        if (this.currentState != null) {
            this.currentState.init(this);
            this.currentState.onEnter();
        }
    }

    @Override
    public UIWindow getWindow() {
        return this.window;
    }

    @Override
    public SnakeModel getSnakeModel() {
        return this.snakeModel;
    }

    /**
     * Creates a new instance of the SnakeModel, typically
     * when a new game is started.
     */
    @Override
    public void createNewSnakeModel() {
        this.snakeModel = new SnakeModel(this.gridW, this.gridH);
    }

    @Override
    public int getGridW() { return this.gridW; }

    @Override
    public int getGridH() { return this.gridH; }

    @Override
    public int getCellSize() { return this.cellSize; }
}
