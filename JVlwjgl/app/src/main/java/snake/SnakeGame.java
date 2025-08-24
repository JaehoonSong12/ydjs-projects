package snake;

/*
 Single-file LWJGL3 Snake (MVC) example with a Menu and UI components.
 - One source file: snake/SnakeGame.java
 - Public top-level class: SnakeGame
 - Inner static classes: Model, View, Controller, UIButton, UIMenu
 - Includes a tiny 5x7 pixel font renderer so button labels and score are visible.

 Gradle (example) dependencies (set correct LWJGL version and natives for your OS):
 repositories { mavenCentral() }
 dependencies {
   implementation "org.lwjgl:lwjgl:3.3.1"
   implementation "org.lwjgl:lwjgl-glfw:3.3.1"
   implementation "org.lwjgl:lwjgl-opengl:3.3.1"
   runtimeOnly "org.lwjgl:lwjgl::natives-windows"      // change to -linux or -macos
   runtimeOnly "org.lwjgl:lwjgl-glfw::natives-windows"
   runtimeOnly "org.lwjgl:lwjgl-opengl::natives-windows"
 }
 application {
   mainClass = 'snake.SnakeGame'
 }
*/

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.opengl.GL;

/**
 * Single-file Snake game using MVC pattern, with a clickable menu and UI components.
 *
 * Controls:
 *   - Menu: click Start / Quit
 *   - Game: Arrow keys or WASD to move
 *   - ESC to pause / return to menu
 */
public class SnakeGame {

    public static void main(String[] args) {
        // Initialize GLFW
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            System.err.println("Failed to initialize GLFW");
            System.exit(-1);
        }

        final int cellSize = 28;
        final int gridW = 20;
        final int gridH = 16;
        final int width = gridW * cellSize;
        final int height = gridH * cellSize;

        glfwDefaultWindowHints();
        long window = glfwCreateWindow(width, height, "Snake (single-file MVC + Menu) - LWJGL", NULL, NULL);
        if (window == NULL) {
            System.err.println("Failed to create window");
            glfwTerminate();
            System.exit(-1);
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        GL.createCapabilities();

        // Create MVC pieces
        Controller controller = new Controller(gridW, gridH);
        View view = new View(gridW, gridH, cellSize);

        // Setup UI: link view's menu buttons to controller actions
        view.createMenu(
            () -> { controller.startGame(); },    // Start callback
            () -> { glfwSetWindowShouldClose(window, true); } // Quit callback
        );

        // Input callbacks: keyboard -> controller
        GLFWKeyCallbackI keyCb = (win, key, scancode, action, mods) -> controller.keyCallback(win, key, scancode, action, mods);
        glfwSetKeyCallback(window, keyCb);

        // Mouse button callback -> forward to view/controller via onClick
        glfwSetMouseButtonCallback(window, (win, button, action, mods) -> {
            if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS) {
                // get cursor position
                double[] xd = new double[1], yd = new double[1];
                glfwGetCursorPos(window, xd, yd);
                // pass to view (it will run the button's Runnable which calls controller)
                view.onMouseClick((int) xd[0], (int) yd[0]);
            }
        });

        // Main loop
        double lastTime = glfwGetTime();
        double delta = 0.0;
        final double secPerUpdate = 1.0 / 8.0; // 8 updates per second

        while (!glfwWindowShouldClose(window)) {
            double now = glfwGetTime();
            delta += (now - lastTime);
            lastTime = now;

            // Poll cursor position each frame for hover effects
            double[] cx = new double[1], cy = new double[1];
            glfwGetCursorPos(window, cx, cy);
            view.setMouse((int) cx[0], (int) cy[0]);

            // Handle fixed-step updates (only when playing)
            while (delta >= secPerUpdate) {
                controller.update(); // updates model when in PLAY
                delta -= secPerUpdate;
            }

            // Render everything (pass model & controller state to view)
            view.render(controller.getModel(), controller.getState(), controller.getScore());

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwDestroyWindow(window);
        glfwTerminate();
        System.exit(0);
    }

    /* -----------------------
       Model (game state & rules)
       ----------------------- */
    public static class Model {
        public enum Direction { UP, DOWN, LEFT, RIGHT }

        private final int gridWidth, gridHeight;
        private final Deque<int[]> snake; // each int[] is {x,y}
        private Direction dir = Direction.RIGHT;
        private int[] food;
        private boolean grow = false;
        private boolean gameOver = false;
        private final Random rnd = new Random();

        public Model(int gridWidth, int gridHeight) {
            this.gridWidth = gridWidth;
            this.gridHeight = gridHeight;
            snake = new LinkedList<>();
            int centerX = gridWidth / 2;
            int centerY = gridHeight / 2;
            // initial snake length 3
            snake.add(new int[] { centerX - 1, centerY });
            snake.add(new int[] { centerX, centerY });
            snake.add(new int[] { centerX + 1, centerY });
            placeFood();
        }

        // Read-only view of snake
        public List<int[]> getSnake() { return List.copyOf(snake); }
        public int[] getFood() { return food == null ? null : new int[] { food[0], food[1] }; }
        public boolean isGameOver() { return gameOver; }
        public int getScore() { return Math.max(0, snake.size() - 3); }

        public void setDirection(Direction d) {
            if (snake.size() > 1) {
                Direction opp = oppositeOf(dir);
                if (d == opp) return; // prevent reverse
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

        public void update() {
            if (gameOver) return;
            int[] head = snake.peekLast();
            int nx = head[0], ny = head[1];
            switch (dir) {
                case UP: ny--; break;
                case DOWN: ny++; break;
                case LEFT: nx--; break;
                case RIGHT: nx++; break;
            }

            // wall collision: end game
            if (nx < 0 || nx >= gridWidth || ny < 0 || ny >= gridHeight) {
                gameOver = true;
                return;
            }

            // self-collision
            for (int[] s : snake) {
                if (s[0] == nx && s[1] == ny) {
                    gameOver = true;
                    return;
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
                grow = false; // consumed growth this update
            }
        }

        private void placeFood() {
            // naive placement: random location not on snake
            int attempts = 0;
            while (attempts++ < 1000) {
                int fx = rnd.nextInt(gridWidth);
                int fy = rnd.nextInt(gridHeight);
                boolean onSnake = false;
                for (int[] s : snake) {
                    if (s[0] == fx && s[1] == fy) { onSnake = true; break; }
                }
                if (!onSnake) { food = new int[] { fx, fy }; return; }
            }
            // fallback: no food (shouldn't happen for reasonable grid)
            food = null;
        }
    }

    /* -----------------------
       Controller (game state machine & input)
       ----------------------- */
    public static class Controller {
        public enum State { MENU, PLAY, PAUSE, GAMEOVER }

        private Model model;
        private State state = State.MENU;
        private final int gridW, gridH;

        public Controller(int gridW, int gridH) {
            this.gridW = gridW;
            this.gridH = gridH;
            this.model = new Model(gridW, gridH);
        }

        public Model getModel() { return model; }
        public State getState() { return state; }
        public int getScore() { return model == null ? 0 : model.getScore(); }

        // Called by UI to start or restart the game
        public void startGame() {
            this.model = new Model(gridW, gridH);
            this.state = State.PLAY;
        }

        public void pauseToMenu() {
            this.state = State.MENU;
        }

        public void gameOver() {
            this.state = State.GAMEOVER;
        }

        public void keyCallback(long window, int key, int scancode, int action, int mods) {
            if (action != GLFW_PRESS && action != GLFW_REPEAT) return;

            // Global keys
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                // toggle between PLAY and MENU
                if (state == State.PLAY) {
                    state = State.MENU;
                } else if (state == State.MENU) {
                    // nothing
                } else if (state == State.GAMEOVER) {
                    state = State.MENU;
                }
                return;
            }

            if (state != State.PLAY) return; // only movement keys handled in PLAY

            switch (key) {
                case GLFW_KEY_UP: case GLFW_KEY_W:
                    model.setDirection(Model.Direction.UP); break;
                case GLFW_KEY_DOWN: case GLFW_KEY_S:
                    model.setDirection(Model.Direction.DOWN); break;
                case GLFW_KEY_LEFT: case GLFW_KEY_A:
                    model.setDirection(Model.Direction.LEFT); break;
                case GLFW_KEY_RIGHT: case GLFW_KEY_D:
                    model.setDirection(Model.Direction.RIGHT); break;
                default:
                    break;
            }
        }

        // Called every fixed tick
        public void update() {
            if (state != State.PLAY) return;
            model.update();
            if (model.isGameOver()) {
                state = State.GAMEOVER;
            }
        }
    }

    /* -----------------------
       View (rendering) + UI components
       ----------------------- */
    public static class View {
        private final int gridW, gridH, cellSize;
        private int mouseX = -1, mouseY = -1;

        // UI menu
        private UIMenu mainMenu;
        private UIMenu gameOverMenu;

        // Tiny pixel font data (5x7)
        private static final Map<Character, int[]> FONT = buildFont();

        public View(int gridW, int gridH, int cellSize) {
            this.gridW = gridW;
            this.gridH = gridH;
            this.cellSize = cellSize;

            // Setup simple 2D orthographic projection
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, gridW * cellSize, gridH * cellSize, 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);

            // create UIs (positions in pixels)
            int winW = gridW * cellSize;
            int winH = gridH * cellSize;

            mainMenu = new UIMenu();
            int btnW = 200, btnH = 48;
            mainMenu.addButton(new UIButton(winW/2 - btnW/2, winH/2 - 30, btnW, btnH, "START", () -> {}));
            mainMenu.addButton(new UIButton(winW/2 - btnW/2, winH/2 + 30, btnW, btnH, "QUIT",  () -> {}));

            gameOverMenu = new UIMenu();
            gameOverMenu.addButton(new UIButton(winW/2 - btnW/2, winH/2 + 20, btnW, btnH, "RESTART", () -> {}));
            gameOverMenu.addButton(new UIButton(winW/2 - btnW/2, winH/2 + 80, btnW, btnH, "QUIT",    () -> {}));
        }

        /**
         * Hook up callbacks to UI buttons: Start and Quit for main menu.
         * The runnables should call controller.startGame(), etc.
         */
        public void createMenu(Runnable onStart, Runnable onQuit) {
            if (mainMenu.buttons.size() >= 2) {
                mainMenu.buttons.get(0).setOnClick(onStart);
                mainMenu.buttons.get(1).setOnClick(onQuit);
            }
            if (gameOverMenu.buttons.size() >= 2) {
                gameOverMenu.buttons.get(0).setOnClick(onStart); // restart as start
                gameOverMenu.buttons.get(1).setOnClick(onQuit);
            }
        }

        public void setMouse(int x, int y) {
            this.mouseX = x;
            this.mouseY = y;
            // update hover state
            mainMenu.updateHover(x, y);
            gameOverMenu.updateHover(x, y);
        }

        public void onMouseClick(int x, int y) {
            mainMenu.onClick(x, y);
            gameOverMenu.onClick(x, y);
        }

        /**
         * Render the entire scene: menu or game depending on state.
         */
        public void render(Model model, Controller.State state, int score) {
            glClearColor(0.08f, 0.08f, 0.08f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            switch (state) {
                case MENU:
                    // Draw a title
                    drawTextCentered("SNAKE", gridW * cellSize / 2, 80, 6, 1f, 1f, 1f);
                    // draw menu
                    mainMenu.render();
                    // hint
                    drawTextCentered("Click START or press arrows/WASD to play", gridW * cellSize / 2, gridH * cellSize - 40, 2, 0.8f, 0.8f, 0.8f);
                    break;
                case PLAY:
                    drawGridBackground();
                    // draw food
                    int[] f = model.getFood();
                    if (f != null) drawCell(f[0], f[1], 1.0f, 0.35f, 0.35f);
                    // draw snake
                    boolean isHead = true;
                    for (int[] s : model.getSnake()) {
                        if (isHead) {
                            drawCell(s[0], s[1], 0.2f, 0.9f, 0.2f);
                            isHead = false;
                        } else {
                            drawCell(s[0], s[1], 0.2f, 0.6f, 0.2f);
                        }
                    }
                    // score (top-left)
                    drawText("SCORE " + score, 8, 8, 2, 1f, 1f, 1f);
                    break;
                case PAUSE:
                    // show the current board dimmed + menu
                    drawGridBackground();
                    int[] ff = model.getFood();
                    if (ff != null) drawCell(ff[0], ff[1], 1.0f, 0.35f, 0.35f);
                    boolean isH = true;
                    for (int[] s : model.getSnake()) {
                        if (isH) { drawCell(s[0], s[1], 0.2f, 0.9f, 0.2f); isH = false; }
                        else drawCell(s[0], s[1], 0.2f, 0.6f, 0.2f);
                    }
                    // overlay
                    glColor4f(0f, 0f, 0f, 0.45f);
                    glBegin(GL_QUADS);
                        glVertex2i(0, 0);
                        glVertex2i(gridW * cellSize, 0);
                        glVertex2i(gridW * cellSize, gridH * cellSize);
                        glVertex2i(0, gridH * cellSize);
                    glEnd();
                    drawTextCentered("PAUSED", gridW * cellSize / 2, 120, 5, 1f, 1f, 1f);
                    mainMenu.render(); // show same menu so user can click Start or Quit (Start will reset the game)
                    break;
                case GAMEOVER:
                    drawGridBackground();
                    int[] food = model.getFood();
                    if (food != null) drawCell(food[0], food[1], 1.0f, 0.35f, 0.35f);
                    boolean ih = true;
                    for (int[] s : model.getSnake()) {
                        if (ih) { drawCell(s[0], s[1], 0.2f, 0.9f, 0.2f); ih = false; }
                        else drawCell(s[0], s[1], 0.2f, 0.6f, 0.2f);
                    }
                    drawTextCentered("GAME OVER", gridW * cellSize / 2, gridH * cellSize / 2 - 20, 5, 1f, 0.7f, 0.7f);
                    drawTextCentered("SCORE " + score, gridW * cellSize / 2, gridH * cellSize / 2 + 10, 3, 1f, 1f, 1f);
                    gameOverMenu.render();
                    break;
            }
        }

        // Helper: draw a single grid cell (filled rectangle)
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

        // Draw subtle grid background
        private void drawGridBackground() {
            // full dark background
            glColor3f(0.06f, 0.06f, 0.06f);
            glBegin(GL_QUADS);
                glVertex2i(0, 0);
                glVertex2i(gridW * cellSize, 0);
                glVertex2i(gridW * cellSize, gridH * cellSize);
                glVertex2i(0, gridH * cellSize);
            glEnd();
            // optional light grid lines
            glColor3f(0.08f, 0.08f, 0.08f);
            glBegin(GL_LINES);
            for (int i = 0; i <= gridW; i++) {
                int x = i * cellSize;
                glVertex2i(x, 0); glVertex2i(x, gridH * cellSize);
            }
            for (int j = 0; j <= gridH; j++) {
                int y = j * cellSize;
                glVertex2i(0, y); glVertex2i(gridW * cellSize, y);
            }
            glEnd();
        }

        // Draw small text at (x,y) using 5x7 pixel font (top-left anchored)
        private void drawText(String text, int x, int y, int scale, float r, float g, float b) {
            int cx = x;
            glColor3f(r, g, b);
            for (char ch : text.toUpperCase().toCharArray()) {
                if (ch == ' ') { cx += (6 * scale); continue; }
                int[] pattern = FONT.getOrDefault(ch, FONT.get('?'));
                if (pattern == null) { cx += (6 * scale); continue; }
                for (int row = 0; row < pattern.length; row++) {
                    int rowBits = pattern[row];
                    for (int col = 0; col < 5; col++) {
                        if (((rowBits >> (4 - col)) & 1) == 1) {
                            int px = cx + col * scale;
                            int py = y + row * scale;
                            glBegin(GL_QUADS);
                                glVertex2i(px, py);
                                glVertex2i(px + scale - 1, py);
                                glVertex2i(px + scale - 1, py + scale - 1);
                                glVertex2i(px, py + scale - 1);
                            glEnd();
                        }
                    }
                }
                cx += (6 * scale); // char spacing
            }
        }

        private void drawTextCentered(String text, int cx, int y, int scale, float r, float g, float b) {
            int textWidth = text.length() * 6 * scale;
            drawText(text, cx - textWidth / 2, y, scale, r, g, b);
        }

        /* -----------------------
           UI Component: UIButton
           ----------------------- */
        public static class UIButton {
            private final int x, y, w, h;
            private final String label;
            private Runnable onClick;
            private boolean hover = false;

            public UIButton(int x, int y, int w, int h, String label, Runnable onClick) {
                this.x = x; this.y = y; this.w = w; this.h = h;
                this.label = label;
                this.onClick = onClick;
            }

            public void setOnClick(Runnable r) { this.onClick = r; }

            public boolean contains(int mx, int my) {
                return mx >= x && mx <= x + w && my >= y && my <= y + h;
            }

            public void setHover(boolean b) { hover = b; }

            public void click() {
                if (onClick != null) onClick.run();
            }

            public void render(int mouseX, int mouseY) {
                // determine hover
                hover = contains(mouseX, mouseY);
                // background
                if (hover) glColor3f(0.18f, 0.45f, 0.18f);
                else glColor3f(0.12f, 0.30f, 0.12f);
                glBegin(GL_QUADS);
                    glVertex2i(x, y);
                    glVertex2i(x + w, y);
                    glVertex2i(x + w, y + h);
                    glVertex2i(x, y + h);
                glEnd();
                // border
                glColor3f(0.9f, 0.9f, 0.9f);
                glBegin(GL_LINE_LOOP);
                    glVertex2i(x+1, y+1);
                    glVertex2i(x + w-1, y+1);
                    glVertex2i(x + w-1, y + h-1);
                    glVertex2i(x+1, y + h-1);
                glEnd();
                // label (centered)
                int textScale = 3;
                int textW = label.length() * 6 * textScale;
                int tx = x + (w - textW) / 2;
                int ty = y + (h - (7 * textScale)) / 2;
                // text color
                if (hover) drawLabel(tx, ty, textScale, 1f, 1f, 1f);
                else drawLabel(tx, ty, textScale, 0.94f, 0.94f, 0.94f);
            }

            // small helper to draw label using static FONT; delegates to outer draw code via OpenGL quads.
            private void drawLabel(int tx, int ty, int scale, float r, float g, float b) {
                glColor3f(r, g, b);
                int cx = tx;
                for (char ch : label.toUpperCase().toCharArray()) {
                    if (ch == ' ') { cx += (6 * scale); continue; }
                    int[] pattern = FONT.getOrDefault(ch, FONT.get('?'));
                    if (pattern == null) { cx += (6 * scale); continue; }
                    for (int row = 0; row < pattern.length; row++) {
                        int rowBits = pattern[row];
                        for (int col = 0; col < 5; col++) {
                            if (((rowBits >> (4 - col)) & 1) == 1) {
                                int px = cx + col * scale;
                                int py = ty + row * scale;
                                glBegin(GL_QUADS);
                                    glVertex2i(px, py);
                                    glVertex2i(px + scale - 1, py);
                                    glVertex2i(px + scale - 1, py + scale - 1);
                                    glVertex2i(px, py + scale - 1);
                                glEnd();
                            }
                        }
                    }
                    cx += (6 * scale);
                }
            }
        }

        /* -----------------------
           UI Component: UIMenu (collection of buttons)
           ----------------------- */
        public static class UIMenu {
            private final LinkedList<UIButton> buttons = new LinkedList<>();

            public void addButton(UIButton b) { buttons.add(b); }

            public void updateHover(int mx, int my) {
                for (UIButton b : buttons) b.setHover(b.contains(mx, my));
            }

            public void onClick(int mx, int my) {
                for (UIButton b : buttons) {
                    if (b.contains(mx, my)) {
                        b.click();
                        break;
                    }
                }
            }

            public void render() {
                for (UIButton b : buttons) b.render(getMouseX(), getMouseY());
            }

            // getters for mouse; they are resolved at runtime through reflection to outer view
            // but since nested static can't access outer instance fields, we'll directly use GLFW cursor each render:
            private int getMouseX() {
                double[] xd = new double[1], yd = new double[1];
                glfwGetCursorPos(glfwGetCurrentContext(), xd, yd);
                return (int) xd[0];
            }
            private int getMouseY() {
                double[] xd = new double[1], yd = new double[1];
                glfwGetCursorPos(glfwGetCurrentContext(), xd, yd);
                return (int) yd[0];
            }
        }

        /* -----------------------
           Tiny 5x7 pixel font definition for a limited charset
           Each entry is an int[7], each int is 5 bits representing one row (leftmost bit is highest).
           ----------------------- */
        private static Map<Character,int[]> buildFont() {
            Map<Character,int[]> m = new HashMap<>();
            // '?' fallback
            m.put('?', new int[] {
                0b01110,
                0b10001,
                0b00010,
                0b00100,
                0b00100,
                0b00000,
                0b00100
            });
            // Letters used: S,T,A,R,Q,U,I,O,E,G,M,P,C,K
            m.put('S', new int[] {
                0b01111,
                0b10000,
                0b10000,
                0b01110,
                0b00001,
                0b00001,
                0b11110
            });
            m.put('T', new int[] {
                0b11111,
                0b00100,
                0b00100,
                0b00100,
                0b00100,
                0b00100,
                0b00100
            });
            m.put('A', new int[] {
                0b01110,
                0b10001,
                0b10001,
                0b11111,
                0b10001,
                0b10001,
                0b10001
            });
            m.put('R', new int[] {
                0b11110,
                0b10001,
                0b10001,
                0b11110,
                0b10100,
                0b10010,
                0b10001
            });
            m.put('Q', new int[] {
                0b01110,
                0b10001,
                0b10001,
                0b10001,
                0b10101,
                0b10010,
                0b01101
            });
            m.put('U', new int[] {
                0b10001,
                0b10001,
                0b10001,
                0b10001,
                0b10001,
                0b10001,
                0b01110
            });
            m.put('I', new int[] {
                0b01110,
                0b00100,
                0b00100,
                0b00100,
                0b00100,
                0b00100,
                0b01110
            });
            m.put('O', new int[] {
                0b01110,
                0b10001,
                0b10001,
                0b10001,
                0b10001,
                0b10001,
                0b01110
            });
            m.put('E', new int[] {
                0b11111,
                0b10000,
                0b10000,
                0b11110,
                0b10000,
                0b10000,
                0b11111
            });
            m.put('G', new int[] {
                0b01110,
                0b10001,
                0b10000,
                0b10000,
                0b10011,
                0b10001,
                0b01110
            });
            m.put('M', new int[] {
                0b10001,
                0b11011,
                0b10101,
                0b10101,
                0b10001,
                0b10001,
                0b10001
            });
            m.put('P', new int[] {
                0b11110,
                0b10001,
                0b10001,
                0b11110,
                0b10000,
                0b10000,
                0b10000
            });
            m.put('C', new int[] {
                0b01111,
                0b10000,
                0b10000,
                0b10000,
                0b10000,
                0b10000,
                0b01111
            });
            m.put('K', new int[] {
                0b10001,
                0b10010,
                0b10100,
                0b11000,
                0b10100,
                0b10010,
                0b10001
            });
            // digits 0-9
            m.put('0', new int[] {
                0b01110,
                0b10001,
                0b10011,
                0b10101,
                0b11001,
                0b10001,
                0b01110
            });
            m.put('1', new int[] {
                0b00100,
                0b01100,
                0b00100,
                0b00100,
                0b00100,
                0b00100,
                0b01110
            });
            m.put('2', new int[] {
                0b01110,
                0b10001,
                0b00001,
                0b00010,
                0b00100,
                0b01000,
                0b11111
            });
            m.put('3', new int[] {
                0b01110,
                0b10001,
                0b00001,
                0b00110,
                0b00001,
                0b10001,
                0b01110
            });
            m.put('4', new int[] {
                0b00010,
                0b00110,
                0b01010,
                0b10010,
                0b11111,
                0b00010,
                0b00010
            });
            m.put('5', new int[] {
                0b11111,
                0b10000,
                0b11110,
                0b00001,
                0b00001,
                0b10001,
                0b01110
            });
            m.put('6', new int[] {
                0b00110,
                0b01000,
                0b10000,
                0b11110,
                0b10001,
                0b10001,
                0b01110
            });
            m.put('7', new int[] {
                0b11111,
                0b00001,
                0b00010,
                0b00100,
                0b01000,
                0b01000,
                0b01000
            });
            m.put('8', new int[] {
                0b01110,
                0b10001,
                0b10001,
                0b01110,
                0b10001,
                0b10001,
                0b01110
            });
            m.put('9', new int[] {
                0b01110,
                0b10001,
                0b10001,
                0b01111,
                0b00001,
                0b00010,
                0b01100
            });
            // space
            m.put(' ', new int[] {
                0b00000,0b00000,0b00000,0b00000,0b00000,0b00000,0b00000
            });

            return m;
        }
    }
}
