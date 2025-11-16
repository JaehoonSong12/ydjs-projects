// /*
//  * Refactored Java LWJGL Snake Game Application
//  * * This file contains a complete refactoring of the original single-file
//  * application. The primary goals of this refactor are:
//  * 1.  **Abstraction:** Separate concerns using strong design patterns.
//  * 2.  **State Management:** Replace the global 'state' integer with the 
//  * State Design Pattern (AppState, GameContext).
//  * 3.  **UI Framework:** Implement the Composite Design Pattern (UIComponent,
//  * UIContainer) to create a hierarchical UI system, as hinted at
//  * by the placeholder classes in the original file.
//  * 4.  **Single Responsibility Principle (SRP):** Each class now has a
//  * clear, distinct purpose.
//  * -   SnakeGameApp: The main application, acts as the "GameContext".
//  * -   UIWindow: Manages the GLFW window and OpenGL context.
//  * -   AppState (and implementations): Manage logic for a single
//  * game state (Menu, Playing, GameOver).
//  * -   UIComponent/UIContainer/Leafs: Manage the UI hierarchy.
//  * -   BitmapFont: A Singleton utility for rendering text.
//  * -   SnakeModel: Unchanged, already a good model class.
//  * -   Colors: Unchanged utility class.
//  * 5.  **Encapsulation:** Remove hard-coded logic from the main loop and
//  * place it within the responsible classes (e.g., rendering logic
//  * is moved into the corresponding AppState).
//  * * All code is in a single file for simplicity, matching the original format.
//  * All explanations are provided as Javadoc or inline/block comments as
//  * requested.
//  */
// package snake;

// // Imports from the original file
// import static org.lwjgl.glfw.GLFW.*;
// import static org.lwjgl.opengl.GL11.*;
// import static org.lwjgl.system.MemoryUtil.NULL;
// import org.lwjgl.glfw.GLFWErrorCallback;
// import org.lwjgl.opengl.GL;

// import java.util.Deque;
// import java.util.HashMap;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Map;
// import java.util.Random;
// import java.util.ArrayList; // Added for UIContainer


// import dev.lwjgl.ui.*;
// import dev.lwjgl.ui.components.*;

// // ========================================================================
// // 1. APPLICATION AND STATE MANAGEMENT (State Pattern)
// // ========================================================================



// /**
//  * Defines the contract for the "Context" of the State Pattern.
//  * This interface is implemented by SnakeGameApp and passed to each
//  * AppState, allowing states to access shared resources (like the window)
//  * and to request a state change (via setState).
//  */
// interface GameContext {
//     /**
//      * Requests a change to a new application state.
//      * @param newState The state to transition to.
//      */
//     void setState(AppState newState);

//     /**
//      * Gets the main application window wrapper.
//      * @return The UIWindow instance.
//      */
//     UIWindow getWindow();

//     /**
//      * Gets the current Snake game model.
//      * @return The SnakeModel instance, or null if one is not active.
//      */
//     SnakeModel getSnakeModel();

//     /**
//      * Creates a new, fresh SnakeModel.
//      */
//     void createNewSnakeModel();
    
//     // --- Accessors for game grid configuration ---
//     int getGridW();
//     int getGridH();
//     int getCellSize();
// }



// /**
//  * The main application class.
//  * This class is responsible for:
//  * - Initializing LWJGL and the UIWindow.
//  * - Running the main game loop.
//  * - Managing the current game state (State Pattern Context).
//  * - Handling and delegating input callbacks to the current state.
//  *
//  * It implements GameContext to provide states with a controlled
//  * way to access shared resources and request state changes.
//  */
// public class SnakeGameApp implements GameContext {

//     /**
//      * The main entry point for the application.
//      * Creates an instance of the app and runs it.
//      */
//     public static void main(String[] args) {
//         new SnakeGameApp().run();
//     }

//     // --- GameContext Shared Resources ---
//     private UIWindow window;
//     private AppState currentState;
//     private SnakeModel snakeModel;

//     // --- Game Configuration ---
//     private int gridW;
//     private int gridH;
//     private int cellSize;

//     /**
//      * Public method to start the application's lifecycle.
//      */
//     public void run() {
//         try {
//             init();
//             loop();
//         } catch (Exception e) {
//             System.err.println("An unrecoverable error occurred:");
//             e.printStackTrace();
//         } finally {
//             cleanup();
//         }
//     }

//     /**
//      * Initializes the application, window, and callbacks.
//      * Sets the initial game state.
//      */
//     private void init() {
//         // Init GLFW
//         GLFWErrorCallback.createPrint(System.err).set();
//         if (!glfwInit()) {
//             throw new IllegalStateException("Unable to initialize GLFW");
//         }

//         // Create the window
//         this.window = new UIWindow("Snake Game", 640, 480);
//         this.cellSize = 20;
//         this.gridW = this.window.getWinW() / this.cellSize;
//         this.gridH = this.window.getWinH() / this.cellSize;

//         // Setup input callbacks to delegate to the current state
//         setupCallbacks();

//         // Set the initial state to the Main Menu
//         this.setState(new MainMenuState());
//     }

//     /**
//      * Configures GLFW callbacks to delegate all input
//      * to the 'currentState' object.
//      */
//     private void setupCallbacks() {
//         long windowHandle = window.getWindowHandle();

//         // Key callback
//         glfwSetKeyCallback(windowHandle, (win, key, scancode, action, mods) -> {
//             if (this.currentState != null) {
//                 this.currentState.onKey(win, key, scancode, action, mods);
//             }
//         });

//         // Mouse Button callback
//         glfwSetMouseButtonCallback(windowHandle, (win, button, action, mods) -> {
//             if (this.currentState != null) {
//                 // Get cursor position at the time of the click
//                 double[] xd = new double[1], yd = new double[1];
//                 glfwGetCursorPos(win, xd, yd);
//                 this.currentState.onMouse(win, button, action, mods, xd[0], yd[0]);
//             }
//         });
//     }

//     /**
//      * The main game loop.
//      * Calculates delta time and delegates updating and rendering
//      * to the current state.
//      */
//     private void loop() {
//         double lastTime = glfwGetTime();

//         while (!glfwWindowShouldClose(window.getWindowHandle())) {
//             // Calculate delta time
//             double now = glfwGetTime();
//             double delta = now - lastTime;
//             lastTime = now;

//             // --- Update ---
//             // The current state handles all update logic,
//             // including UI updates (like hover) and game logic.
//             if (this.currentState != null) {
//                 this.currentState.update(delta);
//             }

//             // --- Render ---
//             // The current state handles all rendering.
//             if (this.currentState != null) {
//                 this.currentState.render();
//             }

//             // Swap buffers and poll events
//             glfwSwapBuffers(window.getWindowHandle());
//             glfwPollEvents();
//         }
//     }

//     /**
//      * Cleans up resources on exit.
//      */
//     private void cleanup() {
//         // Destroy the window
//         if (this.window != null) {
//             this.window.destroy();
//         }

//         // Terminate GLFW
//         glfwTerminate();
//         glfwSetErrorCallback(null).free();
//         System.exit(0);
//     }

//     // ======================================================
//     // Implementation of GameContext (The "Context" in State Pattern)
//     // ======================================================

//     /**
//      * Changes the current application state.
//      * This is the core of the State Pattern.
//      *
//      * @param newState The new state to switch to.
//      */
//     @Override
//     public void setState(AppState newState) {
//         // Call the exit hook for the old state
//         if (this.currentState != null) {
//             this.currentState.onExit();
//         }

//         // Set the new state
//         this.currentState = newState;

//         // Call the init and enter hooks for the new state
//         if (this.currentState != null) {
//             this.currentState.init(this);
//             this.currentState.onEnter();
//         }
//     }

//     @Override
//     public UIWindow getWindow() {
//         return this.window;
//     }

//     @Override
//     public SnakeModel getSnakeModel() {
//         return this.snakeModel;
//     }

//     /**
//      * Creates a new instance of the SnakeModel, typically
//      * when a new game is started.
//      */
//     @Override
//     public void createNewSnakeModel() {
//         this.snakeModel = new SnakeModel(this.gridW, this.gridH);
//     }

//     @Override
//     public int getGridW() { return this.gridW; }

//     @Override
//     public int getGridH() { return this.gridH; }

//     @Override
//     public int getCellSize() { return this.cellSize; }
// }


























// /**
//  * Defines the contract for a single "State" in the State Pattern.
//  * Each state (e.g., Main Menu, Playing) implements this interface
//  * to handle its own logic, rendering, and input.
//  */
// interface AppState {
//     /**
//      * Called once when the state is first set.
//      * @param context A reference to the main GameContext.
//      */
//     void init(GameContext context);

//     /**
//      * Called when this state becomes the active state.
//      * Use this to set up UI, change window titles, etc.
//      */
//     void onEnter();

//     /**
//      * Called when this state is no longer the active state.
//      * Use this for any cleanup.
//      */
//     void onExit();

//     /**
//      * Called every frame by the main loop.
//      * @param delta Time elapsed since the last frame.
//      */
//     void update(double delta);

//     /**
//      * Called every frame by the main loop to draw the screen.
//      */
//     void render();

//     /**
//      * Delegate method for keyboard input.
//      * Called by the main app's GLFW callback.
//      */
//     void onKey(long window, int key, int scancode, int action, int mods);

//     /**
//      * Delegate method for mouse button input.
//      * Called by the main app's GLFW callback.
//      */
//     void onMouse(long window, int button, int action, int mods, double x, double y);
// }

// /**
//  * An abstract base class for AppState implementations.
//  * It provides a default UI hierarchy (view) and automatically
//  * delegates mouse/update events to that UI.
//  */
// abstract class BaseAppState implements AppState {
//     /**
//      * A reference to the GameContext (the main app) for
//      * accessing shared resources and changing state.
//      */
//     protected GameContext context;

//     /**
//      * The root of the UI scene graph for this state.
//      * This is the "Composite" pattern's root node.
//      */
//     protected UIContainer view;

//     @Override
//     public void init(GameContext context) {
//         this.context = context;
//         // Create a root UI container that fills the entire window
//         this.view = new UIContainer(
//             0, 0,
//             context.getWindow().getWinW(),
//             context.getWindow().getWinH()
//         );
//     }

//     /**
//      * Default update behavior:
//      * Gets the current mouse position and tells the UI
//      * view to update itself (e.g., to process hover states).
//      */
//     @Override
//     public void update(double delta) {
//         // Get current cursor position for hover updates
//         double[] xd = new double[1], yd = new double[1];
//         glfwGetCursorPos(context.getWindow().getWindowHandle(), xd, yd);
        
//         // Propagate update to all UI components
//         if (this.view != null) {
//             this.view.update(xd[0], yd[0]);
//         }
//     }

//     /**
//      * Default render behavior:
//      * Clears the screen with a default color and renders the UI view.
//      * Subclasses should override this to set their own background
//      * color and perform custom rendering *before* calling super.render().
//      */
//     @Override
//     public void render() {
//         if (this.view != null) {
//             this.view.render();
//         }
//     }

//     /**
//      * Default mouse input behavior:
//      * Delegates the mouse click to the UI view, which will
//      * find which component (e.g., button) was clicked.
//      */
//     @Override
//     public void onMouse(long window, int button, int action, int mods, double x, double y) {
//         if (this.view != null) {
//             this.view.handleMouse(x, y, button, action);
//         }
//     }

//     // --- Default empty hooks ---
    
//     @Override
//     public void onEnter() {
//         // By default, do nothing on enter
//     }

//     @Override
//     public void onExit() {
//         // By default, do nothing on exit
//     }
    
//     @Override
//     public void onKey(long window, int key, int scancode, int action, int mods) {
//         // By default, do nothing on key press
//         // (but check for ESCAPE as a global quit)
//         if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
//             glfwSetWindowShouldClose(window, true);
//         }
//     }
// }

// /**
//  * Concrete State: MainMenuState
//  * Handles the main menu screen.
//  */
// class MainMenuState extends BaseAppState {

//     @Override
//     public void onEnter() {
//         // This method is called every time we ENTER this state.
//         // We set the title and build the UI from scratch.
        
//         context.getWindow().setTitle("Snake Game - Click to Start");
        
//         // Clear any old UI components
//         this.view.clear();

//         // Get window dimensions for centering
//         double winW = context.getWindow().getWinW();
//         double winH = context.getWindow().getWinH();

//         // 1. Add Title Label
//         UILabel titleLabel = new UILabel("abcdefghijklmnopqrstuvwxyz", 0, winH * 0.25, 4);
//         titleLabel.centerHorizontal(0, winW); // Center it
//         this.view.add(titleLabel);
        
        
//         UILabel titleLabel2 = new UILabel("Snake Game (Playable!)", 0, winH * 0.10, 2);
//         titleLabel2.centerHorizontal(0, winW); // Center it
//         this.view.add(titleLabel2);
        

//         // 2. Add Start Button
//         UIButton startButton = new UIButton(
//             "START SNAKE GAME",
//             winW / 2.0 - 100, // x
//             winH / 2.0 - 24,  // y
//             200,              // width
//             48,               // height
//             () -> {
//                 // Action: When clicked, change state to PlayingState
//                 System.out.println("Starting Snake Game!");
//                 context.setState(new PlayingState());
//             }
//         );
//         this.view.add(startButton);
//     }

//     @Override
//     public void render() {
//         // 1. Set background color
//         glClearColor(0.08f, 0.08f, 0.08f, 1.0f);
//         glClear(GL_COLOR_BUFFER_BIT);

//         // 2. Render the UI (title and button)
//         super.render(); // This calls view.render()
//     }
    
//     @Override
//     public void onKey(long window, int key, int scancode, int action, int mods) {
//         // Override global ESCAPE to just exit the app
//         if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
//             glfwSetWindowShouldClose(window, true);
//         }
//     }
// }

// /**
//  * Concrete State: PlayingState
//  * Handles the main game logic and rendering.
//  */
// class PlayingState extends BaseAppState {

//     private double timeAccumulator = 0.0;
//     private final double secPerUpdate = 1.0 / 8.0; // 8 updates per second
    
//     private UILabel scoreLabel; // The only UI component in this state

//     @Override
//     public void onEnter() {
//         context.getWindow().setTitle("Snake Game - Use Arrow Keys!");
        
//         // Create a new snake model for this game session
//         context.createNewSnakeModel(); 
        
//         // Build the UI
//         this.view.clear();
//         this.scoreLabel = new UILabel("SCORE: 0", 10, 10, 2);
//         this.view.add(scoreLabel);
//     }

//     @Override
//     public void update(double delta) {
//         // We don't call super.update() because we don't need
//         // mouse hover events for the simple score label.
        
//         // --- Fixed-step update for game logic ---
//         this.timeAccumulator += delta;
        
//         SnakeModel model = context.getSnakeModel();
//         if (model == null) return; // Should not happen

//         while (this.timeAccumulator >= this.secPerUpdate) {
//             // Update the game model
//             model.update();

//             // Check for game over
//             if (model.isGameOver()) {
//                 context.setState(new GameOverState());
//                 return; // Stop updating
//             }
            
//             // Update score UI
//             this.scoreLabel.setText("SCORE: " + model.getScore());
            
//             // Consume the time slice
//             this.timeAccumulator -= this.secPerUpdate;
//         }
//     }

//     @Override
//     public void render() {
//         // 1. Set background color
//         glClearColor(0.06f, 0.06f, 0.06f, 1.0f);
//         glClear(GL_COLOR_BUFFER_BIT);

//         // 2. Draw the game world
//         drawGridBackground();
//         drawSnake();
//         drawFood();

//         // 3. Render the UI (score label)
//         super.render(); // This calls view.render()
//     }

//     @Override
//     public void onKey(long window, int key, int scancode, int action, int mods) {
//         SnakeModel model = context.getSnakeModel();
//         if (model == null) return;
        
//         // Handle ESCAPE key to return to menu
//         if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
//             context.setState(new MainMenuState());
//             return; // Don't process other keys
//         }

//         // Handle snake movement keys
//         if (action == GLFW_PRESS || action == GLFW_REPEAT) {
//             switch (key) {
//                 case GLFW_KEY_UP:
//                 case GLFW_KEY_W:
//                     model.setDirection(SnakeModel.Direction.UP);
//                     break;
//                 case GLFW_KEY_DOWN:
//                 case GLFW_KEY_S:
//                     model.setDirection(SnakeModel.Direction.DOWN);
//                     break;
//                 case GLFW_KEY_LEFT:
//                 case GLFW_KEY_A:
//                     model.setDirection(SnakeModel.Direction.LEFT);
//                     break;
//                 case GLFW_KEY_RIGHT:
//                 case GLFW_KEY_D:
//                     model.setDirection(SnakeModel.Direction.RIGHT);
//                     break;
//             }
//         }
//     }
    
//     // --- Private Rendering Helpers (Moved from old main class) ---

//     private void drawGridBackground() {
//         int winW = context.getWindow().getWinW();
//         int winH = context.getWindow().getWinH();
//         int cellSize = context.getCellSize();
        
//         // Optional light grid lines
//         glColor3f(0.08f, 0.08f, 0.08f);
//         glBegin(GL_LINES);
//         for (int i = 0; i <= context.getGridW(); i++) {
//             int x = i * cellSize;
//             glVertex2i(x, 0); glVertex2i(x, winH);
//         }
//         for (int j = 0; j <= context.getGridH(); j++) {
//             int y = j * cellSize;
//             glVertex2i(0, y); glVertex2i(winW, y);
//         }
//         glEnd();
//     }
    
//     private void drawSnake() {
//         SnakeModel model = context.getSnakeModel();
//         if (model == null) return;
        
//         int cellSize = context.getCellSize();
//         float r = 0.2f;
//         float g = 0.6f; // gradually increment up to 0.9f.
//         float g_increment = (model.getSnakeSize() > 0) ? (0.3f / model.getSnakeSize()) : 0;
//         float b = 0.2f;
        
//         for (int[] segment : model.getSnake()) {
//             drawCell(segment[0], segment[1], cellSize, r, g, b);
//             g += g_increment;
//         }
//     }

//     private void drawFood() {
//         SnakeModel model = context.getSnakeModel();
//         if (model == null) return;
        
//         int[] food = model.getFood();
//         if (food != null) {
//             drawCell(food[0], food[1], context.getCellSize(), 1.0f, 0.35f, 0.35f);
//         }
//     }
    
//     private void drawCell(int gx, int gy, int cellSize, float r, float g, float b) {
//         int x = gx * cellSize;
//         int y = gy * cellSize;
//         glColor3f(r, g, b);
//         glBegin(GL_QUADS);
//             glVertex2i(x + 1, y + 1);
//             glVertex2i(x + cellSize - 1, y + 1);
//             glVertex2i(x + cellSize - 1, y + cellSize - 1);
//             glVertex2i(x + 1, y + cellSize - 1);
//         glEnd();
//     }
// }

// /**
//  * Concrete State: GameOverState
//  * Handles the game over screen.
//  */
// class GameOverState extends BaseAppState {

//     @Override
//     public void onEnter() {
//         int score = (context.getSnakeModel() != null) ? context.getSnakeModel().getScore() : 0;
//         context.getWindow().setTitle("Game Over! Score: " + score);
        
//         this.view.clear();

//         double winW = context.getWindow().getWinW();
//         double winH = context.getWindow().getWinH();
        
//         // 1. "GAME OVER" Label
//         UILabel gameOverLabel = new UILabel("GAME OVER", 0, winH * 0.25, 4);
//         gameOverLabel.centerHorizontal(0, winW);
//         this.view.add(gameOverLabel);
        
//         // 2. "FINAL SCORE" Label
//         UILabel scoreLabel = new UILabel("FINAL SCORE: " + score, 0, winH * 0.25 + 50, 3);
//         scoreLabel.centerHorizontal(0, winW);
//         this.view.add(scoreLabel);

//         // 3. "Click button" Label
//         UILabel restartLabel = new UILabel("Click button to play again", 0, winH * 0.25 + 100, 2);
//         restartLabel.centerHorizontal(0, winW);
//         this.view.add(restartLabel);
        
//         // 4. "RESTART" Button (looks identical to the main menu button)
//         UIButton restartButton = new UIButton(
//             "RESTART",
//             winW / 2.0 - 100, // x
//             winH / 2.0 + 48,  // y (lower down)
//             200,              // width
//             48,               // height
//             () -> {
//                 // Action: Return to the Main Menu
//                 context.setState(new MainMenuState());
//             }
//         );
//         this.view.add(restartButton);
//     }
    
//     @Override
//     public void render() {
//         // 1. Set background color
//         glClearColor(0.08f, 0.08f, 0.08f, 1.0f);
//         glClear(GL_COLOR_BUFFER_BIT);

//         // 2. Render the UI
//         super.render();
//     }
    
//     @Override
//     public void onKey(long window, int key, int scancode, int action, int mods) {
//         // Allow ESCAPE to return to menu
//         if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
//             context.setState(new MainMenuState());
//         }
//     }
// }


























// // ========================================================================
// // 2. WINDOW MANAGEMENT
// // ========================================================================

// /**
//  * A wrapper class for the GLFW window.
//  * This class encapsulates all window creation and management logic.
//  * It is no longer a static utility, but a proper object.
//  */
// class UIWindow {
    
//     /**
//      * The handle to the native GLFW window.
//      */
//     private long windowHandle;
//     public long getWindowHandle() {
//         return windowHandle;
//     }

//     private int winW;
//     public int getWinW() {
//         return winW;
//     }
    
//     private int winH;
//     public int getWinH() {
//         return winH;
//     }

//     private String title;

//     /**
//      * Creates and initializes a new UIWindow.
//      * @param title The initial window title.
//      * @param winW The initial window width.
//      * @param winH The initial window height.
//      */
//     public UIWindow(String title, int winW, int winH) {
//         this.winW = winW;
//         this.winH = winH;
//         this.title = title;
//         initLWJGL();
//     }
    
//     /**
//      * Handles the low-level GLFW and OpenGL setup.
//      */
//     private void initLWJGL() {
//         // GLFW is assumed to be initialized by the main app
        
//         // create window
//         glfwDefaultWindowHints();
//         long handle = glfwCreateWindow(this.winW, this.winH, this.title, NULL, NULL);
//         if (handle == NULL) {
//             glfwTerminate();
//             throw new RuntimeException("Failed to create the GLFW window");
//         }
//         this.windowHandle = handle;
        
//         // make context current
//         glfwMakeContextCurrent(windowHandle);
//         // Enable v-sync
//         glfwSwapInterval(1);
        
//         // Init GL
//         GL.createCapabilities();
        
//         // Set a simple orthographic projection where y increases downward
//         // This matches GLFW cursor coordinates.
//         setOrthoProjection();
//     }

//     /**
//      * Sets the OpenGL projection matrix for 2D rendering.
//      */
//     private void setOrthoProjection() {
//         glMatrixMode(GL_PROJECTION);
//         glLoadIdentity();
//         // left, right, bottom, top -> (top=0, bottom=winH) makes Y go down
//         glOrtho(0, this.winW, this.winH, 0, -1, 1); 
//         glMatrixMode(GL_MODELVIEW);
//         glLoadIdentity();
//     }

//     /**
//      * Updates the window title.
//      * @param title The new title.
//      */
//     public void setTitle(String title) {
//         this.title = title;
//         glfwSetWindowTitle(windowHandle, title);
//     }
    
//     /**
//      * Resizes the window and updates the OpenGL viewport/projection.
//      * Note: This is not hooked up to a callback, but could be.
//      */
//     public void resize(int newW, int newH) {
//         this.winW = newW;
//         this.winH = newH;
        
//         glfwSetWindowSize(windowHandle, this.winW, this.winH);
//         glViewport(0, 0, this.winW, this.winH);
        
//         // Update projection to match new size
//         setOrthoProjection();
//     }

//     /**
//      * Destroys the native window resource.
//      */
//     public void destroy() {
//         if (windowHandle != NULL) {
//             glfwDestroyWindow(windowHandle);
//         }
//     }
// }






// // ========================================================================
// // 3. UI FRAMEWORK (Composite Pattern)
// // ========================================================================

// /**
//  * A UIComponent that can contain other UIComponents. (Composite)
//  * This is the "Composite" in the Composite Design Pattern.
//  * Its render, update, and handleMouse methods delegate
//  * to all of its children.
//  */
// class UIContainer extends UIComponent {
    
//     /**
//      * The list of child components.
//      */
//     protected List<UIComponent> children = new ArrayList<>();

//     public UIContainer(double x, double y, double w, double h) {
//         super(x, y, w, h);
//     }

//     /**
//      * Adds a child component to this container.
//      */
//     public void add(UIComponent component) {
//         if (component != null) {
//             children.add(component);
//         }
//     }

//     /**
//      * Removes a child component from this container.
//      */
//     public void remove(UIComponent component) {
//         children.remove(component);
//     }

//     /**
//      * Removes all child components.
//      */
//     public void clear() {
//         children.clear();
//     }

//     /**
//      * Renders this container (optional) and all visible children.
//      */
//     @Override
//     public void render() {
//         if (!visible) return;

//         // --- Optional: Render container background ---
//         // (We leave this transparent by default)
//         // glColor3f(0.1f, 0.1f, 0.1f);
//         // glBegin(GL_QUADS);
//         // ...
//         // glEnd();

//         // --- Render all children ---
//         for (UIComponent child : children) {
//             child.render();
//         }
//     }

//     /**
//      * Updates all enabled children.
//      */
//     @Override
//     public void update(double mouseX, double mouseY) {
//         if (!visible || !enabled) return;

//         for (UIComponent child : children) {
//             child.update(mouseX, mouseY);
//         }
//     }

//     /**
//      * Passes mouse events to children.
//      * Iterates in reverse so that components rendered
//      * "on top" (added last) get the click event first.
//      */
//     @Override
//     public boolean handleMouse(double mouseX, double mouseY, int button, int action) {
//         if (!visible || !enabled) return false;

//         // Iterate in reverse (top-most components first)
//         for (int i = children.size() - 1; i >= 0; i--) {
//             UIComponent child = children.get(i);
//             if (child.handleMouse(mouseX, mouseY, button, action)) {
//                 // If a child consumed the event, stop propagating
//                 return true; 
//             }
//         }
        
//         // If no child consumed it, check if this container
//         // itself should (e.g., for a "click-off" event).
//         // Default: no.
//         return false;
//     }
// }







// /**
//  * A clickable button component. (Leaf)
//  * This is a "Leaf" in the Composite Design Pattern.
//  * It contains a UILabel, handles hover states, and
//  * executes a callback when clicked.
//  */
// class UIButton extends UIComponent {

//     private UILabel label;
//     private boolean hover = false;
//     private final Runnable onClick; // The action to perform on click

//     // --- Style properties ---
//     private final float[] bgColor = {0.12f, 0.30f, 0.12f};
//     private final float[] hoverColor = {0.18f, 0.45f, 0.18f};
//     private final float[] borderColor = {0.95f, 0.95f, 0.95f};

//     /**
//      * Creates a new UIButton.
//      * @param labelText The text to display on the button.
//      * @param x The X position.
//      * @param y The Y position.
//      * @param w The width.
//      * @param h The height.
//      * @param onClick A Runnable to execute when the button is clicked.
//      */
//     public UIButton(String labelText, double x, double y, double w, double h, Runnable onClick) {
//         super(x, y, w, h);
//         this.onClick = onClick;
        
//         // Create a label, but its position will be managed
//         // by the button's centerLabel() method.
//         this.label = new UILabel(labelText, 0, 0, 3);
//         centerLabel();
//     }

//     /**
//      * Internal helper to (re)center the label inside the button.
//      */
//     private void centerLabel() {
//         this.label.centerHorizontal(this.x, this.w);
//         this.label.centerVertical(this.y, this.h);
//     }

//     /**
//      * Renders the button's background, border, and text label.
//      */
//     @Override
//     public void render() {
//         if (!visible) return;

//         // 1. Draw background
//         if (hover && enabled) {
//             Colors.setColor(hoverColor);
//         } else {
//             Colors.setColor(bgColor);
//         }
        
//         glBegin(GL_QUADS);
//             glVertex2d(x, y);
//             glVertex2d(x + w, y);
//             glVertex2d(x + w, y + h);
//             glVertex2d(x, y + h);
//         glEnd();

//         // 2. Draw border
//         Colors.setColor(borderColor);
//         glLineWidth(1.5f);
//         glBegin(GL_LINE_LOOP);
//             glVertex2d(x + 1, y + 1);
//             glVertex2d(x + w - 1, y + 1);
//             glVertex2d(x + w - 1, y + h - 1);
//             glVertex2d(x + 1, y + h - 1);
//         glEnd();

//         // 3. Draw the label (which is a child component)
//         this.label.render();
//     }

//     /**
//      * Updates the button's hover state.
//      */
//     @Override
//     public void update(double mouseX, double mouseY) {
//         if (!visible || !enabled) {
//             this.hover = false;
//             return;
//         }
//         this.hover = contains(mouseX, mouseY);
//     }

//     /**
//      * Handles mouse clicks. If this button is clicked,
//      * it executes its onClick callback and consumes the event.
//      */
//     @Override
//     public boolean handleMouse(double mouseX, double mouseY, int button, int action) {
//         if (!visible || !enabled) return false;

//         // Check for a left-click press inside our bounds
//         if (button == GLFW_MOUSE_BUTTON_LEFT &&
//             action == GLFW_PRESS &&
//             contains(mouseX, mouseY))
//         {
//             // We were clicked!
//             if (onClick != null) {
//                 onClick.run();
//             }
//             // Consume the event so no components "under" this button get it.
//             return true;
//         }
        
//         // Event was not for us
//         return false;
//     }
// }


// // ========================================================================
// // 4. RENDERING UTILITIES
// // ========================================================================

// /**
//  * A simple rectangle drawing class.
//  * (Unchanged from original, but with Javadoc added)
//  */
// class UIRectangle {
//     public int x, y, w, h;
//     private float[] color;
    
//     /**
//      * Constructs a rectangle with a default color (Dark Red).
//      */
//     public UIRectangle(int x, int y, int w, int h) {
//         this.x = x; 
//         this.y = y; 
//         this.w = w; 
//         this.h = h;
//         this.color = Colors.DARK_RED; // default color
//     }
    
//     /**
//      * Constructs a rectangle with a specific color.
//      */
//     public UIRectangle(int x, int y, int w, int h, float[] color) {
//         this.x = x; 
//         this.y = y; 
//         this.w = w; 
//         this.h = h;
//         this.color = color;
//     }

//     /**
//      * Moves the rectangle by a relative amount.
//      */
//     public void move(int dx, int dy) {
//         this.x += dx;
//         this.y += dy;
//     }

//     /**
//      * Sets the rectangle's absolute position.
//      */
//     public void setPosition(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
        
//     /**
//      * Renders the rectangle as a filled quad.
//      */
//     public void render() {
//         // Use the stored color
//         Colors.setColor(this.color);
//         glBegin(GL_QUADS);
//             glVertex2i(x, y);
//             glVertex2i(x + w, y);
//             glVertex2i(x + w, y + h);
//             glVertex2i(x, y + h);
//         glEnd();
//     }
// }

// // ========================================================================
// // 5. GAME MODEL (MVC "Model")
// // ========================================================================

// /**
//  * The "Model" for the Snake game. (Model-View-Controller)
//  * This class encapsulates all game logic, rules, and data,
//  * completely separate from any rendering or input handling.
//  * (Unchanged from original, but with Javadoc added)
//  */
// class SnakeModel {
    
//     /**
//      * Represents the four possible directions of movement.
//      */
//     public enum Direction { UP, DOWN, LEFT, RIGHT }

//     private final int gridWidth, gridHeight;
//     /**
//      * The snake's body, represented as a Deque of [x,y] coordinates.
//      * The head is at the end (peekLast), tail is at the front (peekFirst).
//      */
//     private final Deque<int[]> snake; 
//     private Direction dir = Direction.RIGHT;
//     private int[] food;
//     private boolean grow = false;
//     private boolean gameOver = false;
//     private final Random rnd = new Random();

//     /**
//      * Creates a new SnakeModel for a grid of the given size.
//      */
//     public SnakeModel(int gridWidth, int gridHeight) {
//         this.gridWidth = gridWidth;
//         this.gridHeight = gridHeight;
//         this.snake = new LinkedList<>();
        
//         // Start snake in the middle-left
//         int centerX = gridWidth / 3;
//         int centerY = gridHeight / 2;
        
//         // initial snake length 3
//         snake.add(new int[] { centerX - 2, centerY });
//         snake.add(new int[] { centerX - 1, centerY });
//         snake.add(new int[] { centerX, centerY });
//         dir = Direction.RIGHT; // Ensure starting direction
        
//         placeFood();
//     }

//     // --- Public Getters for State (Read-only) ---

//     /**
//      * Gets a read-only copy of the snake's segments.
//      */
//     public List<int[]> getSnake() { return List.copyOf(snake); }
//     public int getSnakeSize() { return snake.size(); }
//     /**
//      * Gets a copy of the food's position.
//      */
//     public int[] getFood() { return food == null ? null : new int[] { food[0], food[1] }; }
//     public boolean isGameOver() { return gameOver; }
//     public int getScore() { return Math.max(0, snake.size() - 3); } // Initial length is 3

//     /**
//      * Sets the snake's new direction, preventing 180-degree turns.
//      */
//     public void setDirection(Direction d) {
//         if (snake.size() > 1) {
//             Direction opp = oppositeOf(dir);
//             if (d == opp) {
//                 return; // prevent reverse
//             }
//         }
//         dir = d;
//     }

//     /**
//      * Helper to find the opposite of a direction.
//      */
//     private Direction oppositeOf(Direction d) {
//         switch (d) {
//             case UP: return Direction.DOWN;
//             case DOWN: return Direction.UP;
//             case LEFT: return Direction.RIGHT;
//             default: return Direction.LEFT;
//         }
//     }

//     /**
//      * The main update tick for the game logic.
//      * Moves the snake, checks for collisions, and handles food.
//      */
//     public void update() {
//         if (gameOver) return;

//         // 1. Calculate new head position
//         int[] head = snake.peekLast();
//         if (head == null) { // Should not happen
//             gameOver = true;
//             return;
//         }
        
//         int nx = head[0], ny = head[1];
//         switch (dir) {
//             case UP: ny--; break;
//             case DOWN: ny++; break;
//             case LEFT: nx--; break;
//             case RIGHT: nx++; break;
//         }

//         // 2. Check for wall collision
//         if (nx < 0 || nx >= gridWidth || ny < 0 || ny >= gridHeight) {
//             gameOver = true;
//             return;
//         }

//         // 3. Check for self-collision
//         // (Iterate all but the tail, which will move)
//         for (int[] s : snake) {
//             if (s[0] == nx && s[1] == ny) {
//                 // Special case: if we are moving into the tail's old spot
//                 // and we are not growing, it's NOT a collision.
//                 int[] tail = snake.peekFirst();
//                 if (nx == tail[0] && ny == tail[1] && !grow) {
//                     // This is ok, the tail will move
//                 } else {
//                     gameOver = true;
//                     return;
//                 }
//             }
//         }

//         // 4. Add the new head
//         snake.addLast(new int[] { nx, ny });

//         // 5. Check for food collision
//         if (food != null && nx == food[0] && ny == food[1]) {
//             grow = true; // Flag to grow on the *next* update
//             placeFood();
//         }

//         // 6. Remove tail if not growing
//         if (!grow) {
//             snake.removeFirst();
//         } else {
//             // We grew, reset the flag
//             grow = false; 
//         }
//     }

//     /**
//      * Finds a new random location for the food that is not
//      * on the snake's body.
//      */
//     private void placeFood() {
//         // Naive placement: random location not on snake
//         int attempts = 0;
//         final int maxAttempts = gridWidth * gridHeight; // Max possible
        
//         while (attempts++ < maxAttempts) {
//             int fx = rnd.nextInt(gridWidth);
//             int fy = rnd.nextInt(gridHeight);
            
//             // Check if (fx, fy) is on the snake
//             boolean onSnake = false;
//             for (int[] s : snake) {
//                 if (s[0] == fx && s[1] == fy) { 
//                     onSnake = true; 
//                     break; 
//                 }
//             }
            
//             if (!onSnake) { 
//                 food = new int[] { fx, fy }; 
//                 return; 
//             }
//         }
        
//         // Fallback: no food (grid is full)
//         food = null;
//         // This could also trigger a "win" condition
//     }
// }