package core;

import dev.lwjgl.ui.components.UILabel;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Concrete State: PlayingState
 * Handles the main game logic and rendering.
 */
public class PlayingState extends BaseAppState {

    private double timeAccumulator = 0.0;
    private final double secPerUpdate = 1.0 / 8.0; // 8 updates per second

    private UILabel scoreLabel; // The only UI component in this state

    @Override
    public void onEnter() {
        context.getWindow().setTitle("Snake Game - Use Arrow Keys!");

        // Create a new snake model for this game session
        context.createNewSnakeModel();

        // Build the UI
        this.view.clear();
        this.scoreLabel = new UILabel("SCORE: 0", 10, 10, 2);
        this.view.add(scoreLabel);
    }

    @Override
    public void update(double delta) {
        // We don't call super.update() because we don't need
        // mouse hover events for the simple score label.

        // --- Fixed-step update for game logic ---
        this.timeAccumulator += delta;

        SnakeModel model = context.getSnakeModel();
        if (model == null) return; // Should not happen

        while (this.timeAccumulator >= this.secPerUpdate) {
            // Update the game model
            model.update();

            // Check for game over
            if (model.isGameOver()) {
                context.setState(new GameOverState());
                return; // Stop updating
            }

            // Update score UI
            this.scoreLabel.setText("SCORE: " + model.getScore());

            // Consume the time slice
            this.timeAccumulator -= this.secPerUpdate;
        }
    }

    @Override
    public void render() {
        // 1. Set background color
        glClearColor(0.06f, 0.06f, 0.06f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        // 2. Draw the game world
        drawGridBackground();
        drawSnake();
        drawFood();

        // 3. Render the UI (score label)
        super.render(); // This calls view.render()
    }

    @Override
    public void onKey(long window, int key, int scancode, int action, int mods) {
        SnakeModel model = context.getSnakeModel();
        if (model == null) return;

        // Handle ESCAPE key to return to menu
        if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
            context.setState(new MainMenuState());
            return; // Don't process other keys
        }

        // Handle snake movement keys
        if (action == GLFW_PRESS || action == GLFW_REPEAT) {
            switch (key) {
                case GLFW_KEY_UP:
                case GLFW_KEY_W:
                    model.setDirection(SnakeModel.Direction.UP);
                    break;
                case GLFW_KEY_DOWN:
                case GLFW_KEY_S:
                    model.setDirection(SnakeModel.Direction.DOWN);
                    break;
                case GLFW_KEY_LEFT:
                case GLFW_KEY_A:
                    model.setDirection(SnakeModel.Direction.LEFT);
                    break;
                case GLFW_KEY_RIGHT:
                case GLFW_KEY_D:
                    model.setDirection(SnakeModel.Direction.RIGHT);
                    break;
            }
        }
    }

    // --- Private Rendering Helpers (Moved from old main class) ---

    private void drawGridBackground() {
        int winW = context.getWindow().getWinW();
        int winH = context.getWindow().getWinH();
        int cellSize = context.getCellSize();

        // Optional light grid lines
        glColor3f(0.08f, 0.08f, 0.08f);
        glBegin(GL_LINES);
        for (int i = 0; i <= context.getGridW(); i++) {
            int x = i * cellSize;
            glVertex2i(x, 0);
            glVertex2i(x, winH);
        }
        for (int j = 0; j <= context.getGridH(); j++) {
            int y = j * cellSize;
            glVertex2i(0, y);
            glVertex2i(winW, y);
        }
        glEnd();
    }

    private void drawSnake() {
        SnakeModel model = context.getSnakeModel();
        if (model == null) return;

        int cellSize = context.getCellSize();
        float r = 0.2f;
        float g = 0.6f; // gradually increment up to 0.9f.
        float g_increment = (model.getSnakeSize() > 0) ? (0.3f / model.getSnakeSize()) : 0;
        float b = 0.2f;

        for (int[] segment : model.getSnake()) {
            drawCell(segment[0], segment[1], cellSize, r, g, b);
            g += g_increment;
        }
    }

    private void drawFood() {
        SnakeModel model = context.getSnakeModel();
        if (model == null) return;

        int[] food = model.getFood();
        if (food != null) {
            drawCell(food[0], food[1], context.getCellSize(), 1.0f, 0.35f, 0.35f);
        }
    }

    private void drawCell(int gx, int gy, int cellSize, float r, float g, float b) {
        int x = gx * cellSize;
        int y = gy * cellSize;
        glColor3f(r, g, b);
        glBegin(GL_QUADS);
        glVertex2i(x + 1, y + 1);
        glVertex2i(x + cellSize - 1, y + 1);
        glVertex2i(x + cellSize - 1, y + cellSize - 1);
        glVertex2i(x + 1, y + cellSize - 1);
        glEnd();
    }
}
