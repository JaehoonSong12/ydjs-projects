
package snake;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.opengl.GL;

import dev.lwjgl.ui.Colors;
import dev.lwjgl.ui.components.UIComponent;
import dev.lwjgl.ui.components.UILabel;

/**
 * Entry point for the refactored LWJGL Snake game.
 * The Game class (SnakeGame) owns the lifecycle and delegates
 * logic to a single GameState instance while UIWindow encapsulates
 * all LWJGL window interactions.
 */
public class SnakeGame {

    private UIWindow window;
    private GameState state;

    public static void main(String[] args) {
        new SnakeGame().run();
    }

    private void run() {
        try {
            init();
            loop();
        } finally {
            if (window != null) {
                window.destroy();
            }
        }
    }

    private void init() {
        this.window = new UIWindow("Snake Game", 640, 480, 20);
        this.state = new GameState(window);

        window.setKeyCallback((win, key, scancode, action, mods) -> state.onKey(key, action));
        window.setMouseButtonCallback((win, button, action, mods) -> {
            double[] cursor = new double[2];
            window.getCursorPos(cursor);
            state.onMouse(button, action, cursor[0], cursor[1]);
        });
    }

    private void loop() {
        double lastTime = window.getTime();

        while (!window.shouldClose()) {
            double now = window.getTime();
            double delta = now - lastTime;
            lastTime = now;

            state.update(delta);
            state.render();

            window.swapBuffers();
            window.pollEvents();
        }
    }
}

/**
 * A single class that encapsulates all finite state machine logic
 * for the menu, playing, and game over phases.
 */
class GameState {

    private enum Phase { MENU, PLAYING, GAME_OVER }

    private final UIWindow window;
    private final int gridW;
    private final int gridH;
    private final int cellSize;
    private final UIContainer rootView;
    private final double[] cursor = new double[2];
    private final double secPerUpdate = 1.0 / 8.0;

    private Phase phase = Phase.MENU;
    private SnakeModel snake;
    private UILabel scoreLabel;
    private double accumulator = 0.0;

    GameState(UIWindow window) {
        this.window = window;
        this.gridW = window.getGridW();
        this.gridH = window.getGridH();
        this.cellSize = window.getCellSize();
        this.rootView = new UIContainer(0, 0, window.getWinW(), window.getWinH());
        transitionTo(Phase.MENU);
    }

    void update(double delta) {
        window.getCursorPos(cursor);
        rootView.update(cursor[0], cursor[1]);

        if (phase == Phase.PLAYING && snake != null) {
            accumulator += delta;
            while (accumulator >= secPerUpdate) {
                snake.update();
                if (snake.isGameOver()) {
                    transitionTo(Phase.GAME_OVER);
                    return;
                }
                if (scoreLabel != null) {
                    scoreLabel.setText("SCORE: " + snake.getScore());
                }
                accumulator -= secPerUpdate;
            }
        }
    }

    void render() {
        if (phase == Phase.PLAYING) {
            glClearColor(0.06f, 0.06f, 0.06f, 1.0f);
        } else {
            glClearColor(0.08f, 0.08f, 0.08f, 1.0f);
        }
        glClear(GL_COLOR_BUFFER_BIT);

        if (phase == Phase.PLAYING) {
            drawGridBackground();
            drawSnake();
            drawFood();
        }

        rootView.render();
    }

    void onKey(int key, int action) {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
            if (phase == Phase.PLAYING) {
                transitionTo(Phase.MENU);
            } else {
                window.requestClose();
            }
            return;
        }

        if (phase == Phase.PLAYING && snake != null &&
            (action == GLFW_PRESS || action == GLFW_REPEAT)) {
            switch (key) {
                case GLFW_KEY_UP:
                case GLFW_KEY_W:
                    snake.setDirection(SnakeModel.Direction.UP);
                    break;
                case GLFW_KEY_DOWN:
                case GLFW_KEY_S:
                    snake.setDirection(SnakeModel.Direction.DOWN);
                    break;
                case GLFW_KEY_LEFT:
                case GLFW_KEY_A:
                    snake.setDirection(SnakeModel.Direction.LEFT);
                    break;
                case GLFW_KEY_RIGHT:
                case GLFW_KEY_D:
                    snake.setDirection(SnakeModel.Direction.RIGHT);
                    break;
                default:
                    break;
            }
        }
    }

    void onMouse(int button, int action, double x, double y) {
        rootView.handleMouse(x, y, button, action);
    }

    private void transitionTo(Phase newPhase) {
        this.phase = newPhase;
        switch (newPhase) {
            case MENU -> buildMenuUI();
            case PLAYING -> buildPlayingUI();
            case GAME_OVER -> buildGameOverUI();
        }
    }

    private void buildMenuUI() {
        window.setTitle("Snake Game - Click to Start");
        rootView.clear();

        double winW = window.getWinW();
        double winH = window.getWinH();

        UILabel motif = new UILabel("abcdefghijklmnopqrstuvwxyz", 0, winH * 0.25, 4);
        motif.centerHorizontal(0, winW);
        rootView.add(motif);

        UILabel title = new UILabel("Snake Game (Playable!)", 0, winH * 0.10, 2);
        title.centerHorizontal(0, winW);
        rootView.add(title);

        UIButton startButton = new UIButton(
            "START SNAKE GAME",
            winW / 2.0 - 100,
            winH / 2.0 - 24,
            200,
            48,
            () -> transitionTo(Phase.PLAYING)
        );
        rootView.add(startButton);
    }

    private void buildPlayingUI() {
        window.setTitle("Snake Game - Use Arrow Keys!");
        rootView.clear();
        this.snake = new SnakeModel(gridW, gridH);
        this.accumulator = 0.0;

        this.scoreLabel = new UILabel("SCORE: 0", 10, 10, 2);
        rootView.add(scoreLabel);
    }

    private void buildGameOverUI() {
        int score = (snake != null) ? snake.getScore() : 0;
        window.setTitle("Game Over! Score: " + score);
        rootView.clear();

        double winW = window.getWinW();
        double winH = window.getWinH();

        UILabel gameOverLabel = new UILabel("GAME OVER", 0, winH * 0.25, 4);
        gameOverLabel.centerHorizontal(0, winW);
        rootView.add(gameOverLabel);

        UILabel scoreLabel = new UILabel("FINAL SCORE: " + score, 0, winH * 0.25 + 50, 3);
        scoreLabel.centerHorizontal(0, winW);
        rootView.add(scoreLabel);

        UILabel restartLabel = new UILabel("Click button to play again", 0, winH * 0.25 + 100, 2);
        restartLabel.centerHorizontal(0, winW);
        rootView.add(restartLabel);

        UIButton restartButton = new UIButton(
            "RESTART",
            winW / 2.0 - 100,
            winH / 2.0 + 48,
            200,
            48,
            () -> transitionTo(Phase.MENU)
        );
        rootView.add(restartButton);
    }

    private void drawGridBackground() {
        int winW = window.getWinW();
        int winH = window.getWinH();

        glColor3f(0.08f, 0.08f, 0.08f);
        glBegin(GL_LINES);
        for (int i = 0; i <= gridW; i++) {
            int x = i * cellSize;
            glVertex2i(x, 0);
            glVertex2i(x, winH);
        }
        for (int j = 0; j <= gridH; j++) {
            int y = j * cellSize;
            glVertex2i(0, y);
            glVertex2i(winW, y);
        }
        glEnd();
    }

    private void drawSnake() {
        if (snake == null) return;

        float r = 0.2f;
        float g = 0.6f;
        float b = 0.2f;
        float gIncrement = (snake.getSnakeSize() > 0) ? (0.3f / snake.getSnakeSize()) : 0;

        for (int[] segment : snake.getSnake()) {
            drawCell(segment[0], segment[1], r, g, b);
            g += gIncrement;
        }
    }

    private void drawFood() {
        if (snake == null) return;
        int[] food = snake.getFood();
        if (food != null) {
            drawCell(food[0], food[1], 1.0f, 0.35f, 0.35f);
        }
    }

    private void drawCell(int gx, int gy, float r, float g, float b) {
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

/**
 * Wrapper around GLFW window management so core game logic is
 * insulated from direct LWJGL calls.
 */
class UIWindow {

    private final long windowHandle;
    private final GLFWErrorCallback errorCallback;
    private int winW;
    private int winH;
    private final int cellSize;
    private String title;
    private final double[] cursorX = new double[1];
    private final double[] cursorY = new double[1];

    UIWindow(String title, int winW, int winH, int cellSize) {
        this.title = title;
        this.winW = winW;
        this.winH = winH;
        this.cellSize = cellSize;

        errorCallback = GLFWErrorCallback.createPrint(System.err);
        errorCallback.set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        long handle = glfwCreateWindow(winW, winH, title, NULL, NULL);
        if (handle == NULL) {
            glfwTerminate();
            throw new IllegalStateException("Failed to create GLFW window");
        }
        this.windowHandle = handle;

        glfwMakeContextCurrent(windowHandle);
        glfwSwapInterval(1);
        GL.createCapabilities();

        setOrthoProjection();
    }

    private void setOrthoProjection() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, winW, winH, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    void setTitle(String title) {
        this.title = title;
        glfwSetWindowTitle(windowHandle, title);
    }

    void resize(int newW, int newH) {
        this.winW = newW;
        this.winH = newH;
        glfwSetWindowSize(windowHandle, winW, winH);
        glViewport(0, 0, winW, winH);
        setOrthoProjection();
    }

    boolean shouldClose() {
        return glfwWindowShouldClose(windowHandle);
    }

    void requestClose() {
        glfwSetWindowShouldClose(windowHandle, true);
    }

    void swapBuffers() {
        glfwSwapBuffers(windowHandle);
    }

    void pollEvents() {
        glfwPollEvents();
    }

    double getTime() {
        return glfwGetTime();
    }

    void setKeyCallback(GLFWKeyCallbackI callback) {
        glfwSetKeyCallback(windowHandle, callback);
    }

    void setMouseButtonCallback(GLFWMouseButtonCallbackI callback) {
        glfwSetMouseButtonCallback(windowHandle, callback);
    }

    void getCursorPos(double[] target) {
        if (target == null || target.length < 2) {
            throw new IllegalArgumentException("Target array must have length >= 2");
        }
        glfwGetCursorPos(windowHandle, cursorX, cursorY);
        target[0] = cursorX[0];
        target[1] = cursorY[0];
    }

    int getWinW() {
        return winW;
    }

    int getWinH() {
        return winH;
    }

    int getCellSize() {
        return cellSize;
    }

    int getGridW() {
        return winW / cellSize;
    }

    int getGridH() {
        return winH / cellSize;
    }

    void destroy() {
        glfwDestroyWindow(windowHandle);
        glfwTerminate();
        if (errorCallback != null) {
            errorCallback.free();
        }
    }
}

/**
 * Composite container node for UI components.
 */
class UIContainer extends UIComponent {

    private final List<UIComponent> children = new ArrayList<>();

    UIContainer(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    void add(UIComponent component) {
        if (component != null) {
            children.add(component);
        }
    }

    void clear() {
        children.clear();
    }

    @Override
    public void render() {
        if (!visible) return;
        for (UIComponent child : children) {
            child.render();
        }
    }

    @Override
    public void update(double mouseX, double mouseY) {
        if (!visible || !enabled) return;
        for (UIComponent child : children) {
            child.update(mouseX, mouseY);
        }
    }

    @Override
    public boolean handleMouse(double mouseX, double mouseY, int button, int action) {
        if (!visible || !enabled) return false;
        for (int i = children.size() - 1; i >= 0; i--) {
            UIComponent child = children.get(i);
            if (child.handleMouse(mouseX, mouseY, button, action)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Basic button that displays a text label and executes a callback.
 */
class UIButton extends UIComponent {

    private final UILabel label;
    private final Runnable onClick;
    private boolean hover = false;
    private final float[] bgColor = {0.12f, 0.30f, 0.12f};
    private final float[] hoverColor = {0.18f, 0.45f, 0.18f};
    private final float[] borderColor = {0.95f, 0.95f, 0.95f};

    UIButton(String text, double x, double y, double w, double h, Runnable onClick) {
        super(x, y, w, h);
        this.onClick = onClick;
        this.label = new UILabel(text, 0, 0, 3);
        centerLabel();
    }

    private void centerLabel() {
        label.centerHorizontal(this.x, this.w);
        label.centerVertical(this.y, this.h);
    }

    @Override
    public void render() {
        if (!visible) return;

        if (hover && enabled) {
            Colors.setColor(hoverColor);
        } else {
            Colors.setColor(bgColor);
        }

        glBegin(GL_QUADS);
        glVertex2d(x, y);
        glVertex2d(x + w, y);
        glVertex2d(x + w, y + h);
        glVertex2d(x, y + h);
        glEnd();

        Colors.setColor(borderColor);
        glLineWidth(1.5f);
        glBegin(GL_LINE_LOOP);
        glVertex2d(x + 1, y + 1);
        glVertex2d(x + w - 1, y + 1);
        glVertex2d(x + w - 1, y + h - 1);
        glVertex2d(x + 1, y + h - 1);
        glEnd();

        label.render();
    }

    @Override
    public void update(double mouseX, double mouseY) {
        if (!visible || !enabled) {
            hover = false;
            return;
        }
        hover = contains(mouseX, mouseY);
    }

    @Override
    public boolean handleMouse(double mouseX, double mouseY, int button, int action) {
        if (!visible || !enabled) return false;
        if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS && contains(mouseX, mouseY)) {
            if (onClick != null) {
                onClick.run();
            }
            return true;
        }
        return false;
    }
}

/**
 * Simple rectangle helper used by some UI widgets.
 */
class UIRectangle {

    public int x, y, w, h;
    private float[] color;

    UIRectangle(int x, int y, int w, int h) {
        this(x, y, w, h, Colors.DARK_RED);
    }

    UIRectangle(int x, int y, int w, int h, float[] color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render() {
        Colors.setColor(this.color);
        glBegin(GL_QUADS);
        glVertex2i(x, y);
        glVertex2i(x + w, y);
        glVertex2i(x + w, y + h);
        glVertex2i(x, y + h);
        glEnd();
    }
}

/**
 * Model that encapsulates all Snake game rules.
 */
class SnakeModel {

    enum Direction { UP, DOWN, LEFT, RIGHT }

    private final int gridWidth, gridHeight;
    private final Deque<int[]> snake;
    private Direction dir = Direction.RIGHT;
    private int[] food;
    private boolean grow = false;
    private boolean gameOver = false;
    private final Random rnd = new Random();

    SnakeModel(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.snake = new LinkedList<>();

        int centerX = gridWidth / 3;
        int centerY = gridHeight / 2;

        snake.add(new int[] { centerX - 2, centerY });
        snake.add(new int[] { centerX - 1, centerY });
        snake.add(new int[] { centerX, centerY });
        dir = Direction.RIGHT;

        placeFood();
    }

    List<int[]> getSnake() {
        return List.copyOf(snake);
    }

    int getSnakeSize() {
        return snake.size();
    }

    int[] getFood() {
        return food == null ? null : new int[] { food[0], food[1] };
    }

    boolean isGameOver() {
        return gameOver;
    }

    int getScore() {
        return Math.max(0, snake.size() - 3);
    }

    void setDirection(Direction d) {
        if (snake.size() > 1) {
            Direction opposite = oppositeOf(dir);
            if (d == opposite) {
                return;
            }
        }
        dir = d;
    }

    private Direction oppositeOf(Direction d) {
        switch (d) {
            case UP: return Direction.DOWN;
            case DOWN: return Direction.UP;
            case LEFT: return Direction.RIGHT;
            default: return Direction.LEFT;
        }
    }

    void update() {
        if (gameOver) return;
        int[] head = snake.peekLast();
        if (head == null) {
            gameOver = true;
            return;
        }

        int nx = head[0];
        int ny = head[1];
        switch (dir) {
            case UP: ny--; break;
            case DOWN: ny++; break;
            case LEFT: nx--; break;
            case RIGHT: nx++; break;
        }

        if (nx < 0 || nx >= gridWidth || ny < 0 || ny >= gridHeight) {
            gameOver = true;
            return;
        }

        for (int[] s : snake) {
            if (s[0] == nx && s[1] == ny) {
                int[] tail = snake.peekFirst();
                if (!(nx == tail[0] && ny == tail[1] && !grow)) {
                    gameOver = true;
                    return;
                }
            }
        }

        snake.addLast(new int[] { nx, ny });

        if (food != null && nx == food[0] && ny == food[1]) {
            grow = true;
            placeFood();
        }

        if (!grow) {
            snake.removeFirst();
        } else {
            grow = false;
        }
    }

    private void placeFood() {
        int attempts = 0;
        final int maxAttempts = gridWidth * gridHeight;
        while (attempts++ < maxAttempts) {
            int fx = rnd.nextInt(gridWidth);
            int fy = rnd.nextInt(gridHeight);
            boolean onSnake = false;
            for (int[] s : snake) {
                if (s[0] == fx && s[1] == fy) {
                    onSnake = true;
                    break;
                }
            }
            if (!onSnake) {
                food = new int[] { fx, fy };
                return;
            }
        }
        food = null;
    }
}
