package snake;

/*
 Minimal single-file LWJGL3 app with one clickable button.
 - Package: snake
 - File: ClickButtonApp.java
 - Clicking the on-screen button prints "hello world" to stdout.
 
 Gradle notes (example):
   implementation "org.lwjgl:lwjgl:3.3.1"
   implementation "org.lwjgl:lwjgl-glfw:3.3.1"
   implementation "org.lwjgl:lwjgl-opengl:3.3.1"
   runtimeOnly "org.lwjgl:lwjgl::natives-windows"      // change for your OS
   runtimeOnly "org.lwjgl:lwjgl-glfw::natives-windows"
   runtimeOnly "org.lwjgl:lwjgl-opengl::natives-windows"
 mainClass = 'snake.ClickButtonApp'
*/

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;





class LWJGLFrame {
    private static long window;
    public static long getWindow() {
        return window;
    }
    private int winW;
    public int getWinW() {
        return winW;
    }
    public void setWinW(int w) {
        this.winW = w;
        // Update projection
        resize();
    }
    private int winH;
    public int getWinH() {
        return winH;
    }
    public void setWinH(int h) {
        this.winH = h;
        // Update projection
        resize();
    }

    private String title;
    public void setTitle(String title) {
        this.title = title;
        glfwSetWindowTitle(window, title);
    }

    public LWJGLFrame(String title, int winW, int winH) {
        this.winW = winW;
        this.winH = winH;
        this.title = title;
        initLWJGL();
    }
    
    private void initLWJGL() {
        // init GLFW
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            System.err.println("GLFW init failed");
            System.exit(-1);
        }
        // create window
        glfwDefaultWindowHints();
        long window = glfwCreateWindow(this.winW, this.winH, this.title, NULL, NULL);
        if (window == NULL) {
            System.err.println("Failed to create window");
            glfwTerminate();
            System.exit(-1);
        }
        this.window = window;
        // make context current
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        GL.createCapabilities();
        // Set a simple orthographic projection where y increases downward (match GLFW cursor coords)
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, this.winW, this.winH, 0, -1, 1); // left, right, bottom, top -> top=0 makes y go down
        glMatrixMode(GL_MODELVIEW);
    }

    private void resize() {
        glfwSetWindowSize(window, this.winW, this.winH);
        glViewport(0, 0, this.winW, this.winH);
        // Update projection
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, this.winW, this.winH, 0, -1, 1); // left, right, bottom, top -> top=0 makes y go down
        glMatrixMode(GL_MODELVIEW);
    }

    public void destroy() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}






/**
 * Single-button LWJGL app. Click the button, prints "hello world" to console.
 */
public class ClickButtonApp {

    static int state = 0;
    /*
     * 0 = main menu
     * 1 = playing
     * 2 = game over
     */
    
    // Snake game variables
    static SnakeModel snakeModel;
    static final int CELL_SIZE = 20;
    static final int GRID_W = 32;  // 640 / 20
    static final int GRID_H = 24;  // 480 / 20


    public static void main(String[] args) {
        // LWJGLFrame setup
        LWJGLFrame app = new LWJGLFrame("Single Button - click prints 'hello world'", 640, 480);
        long window = app.getWindow();
        int winW = app.getWinW();
        int winH = app.getWinH();





        // Create a single button centered
        UIButton button = new UIButton(winW/2 - 100, winH/2 - 24, 200, 48, "START SNAKE GAME", () -> {
            // action: start snake game
            System.out.println("Starting Snake Game!");
            app.setTitle("Snake Game - Use Arrow Keys!");
            snakeModel = new SnakeModel(GRID_W, GRID_H);
            state = 1; // change state to "playing"
        });
        
        UILabel myLabel = new UILabel("SNAKE GAME", 4); // title label




        // Mouse button callback: on press, check button hit
        glfwSetMouseButtonCallback(window, (win, buttonId, action, mods) -> {
            if (buttonId == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS) {
                double[] xd = new double[1], yd = new double[1];
                glfwGetCursorPos(win, xd, yd);
                int mx = (int) xd[0];
                int my = (int) yd[0];
                if (button.contains(mx, my)) {
                    button.click();
                }
            }
        });

        // Snake game objects are now handled by SnakeModel

        // Keyboard: ESC to exit, Arrow keys for snake movement
        glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                if (state == 1) {
                    state = 0; // return to menu
                    app.setTitle("Snake Game - Click to Start");
                } else {
                    glfwSetWindowShouldClose(win, true);
                }
            }

            // Snake movement only when playing
            if (state == 1 && snakeModel != null && (action == GLFW_PRESS || action == GLFW_REPEAT)) {
                switch (key) {
                    case GLFW_KEY_UP:
                    case GLFW_KEY_W:
                        snakeModel.setDirection(SnakeModel.Direction.UP);
                        break;
                    case GLFW_KEY_DOWN:
                    case GLFW_KEY_S:
                        snakeModel.setDirection(SnakeModel.Direction.DOWN);
                        break;
                    case GLFW_KEY_LEFT:
                    case GLFW_KEY_A:
                        snakeModel.setDirection(SnakeModel.Direction.LEFT);
                        break;
                    case GLFW_KEY_RIGHT:
                    case GLFW_KEY_D:
                        snakeModel.setDirection(SnakeModel.Direction.RIGHT);
                        break;
                }
            }
        });

        // Main loop with snake game timing
        double lastTime = glfwGetTime();
        double delta = 0.0;
        final double secPerUpdate = 1.0 / 8.0; // 8 updates per second for snake

        while (!glfwWindowShouldClose(window)) {
            double now = glfwGetTime();
            delta += (now - lastTime);
            lastTime = now;

            // Handle fixed-step updates for snake game
            if (state == 1 && snakeModel != null) {
                while (delta >= secPerUpdate) {
                    snakeModel.update();
                    if (snakeModel.isGameOver()) {
                        state = 2; // game over
                        app.setTitle("Game Over! Score: " + snakeModel.getScore());
                    }
                    delta -= secPerUpdate;
                }
            }

            if (state == 0) {
                // update hover state from cursor
                double[] xd = new double[1], yd = new double[1];
                glfwGetCursorPos(window, xd, yd);
                int mx = (int) xd[0];
                int my = (int) yd[0];
                button.setHover(button.contains(mx, my));
                // render
                glClearColor(0.08f, 0.08f, 0.08f, 1.0f);
                glClear(GL_COLOR_BUFFER_BIT);

                // draw button and title
                button.render();
                myLabel.render(winW/2 - myLabel.getPixelWidth()/2, 50);
            }
            else if (state == 1) {
                // playing state - render snake game
                glClearColor(0.06f, 0.06f, 0.06f, 1.0f);
                glClear(GL_COLOR_BUFFER_BIT);
                
                // draw grid background
                drawGridBackground(winW, winH, CELL_SIZE);
                
                // draw snake
                if (snakeModel != null) {
                    boolean isHead = true;
                    for (int[] segment : snakeModel.getSnake()) {
                        if (isHead) {
                            drawCell(segment[0], segment[1], CELL_SIZE, 0.2f, 0.9f, 0.2f);
                            isHead = false;
                        } else {
                            drawCell(segment[0], segment[1], CELL_SIZE, 0.2f, 0.6f, 0.2f);
                        }
                    }
                    
                    // draw food
                    int[] food = snakeModel.getFood();
                    if (food != null) {
                        drawCell(food[0], food[1], CELL_SIZE, 1.0f, 0.35f, 0.35f);
                    }
                    
                    // draw score
                    UILabel scoreLabel = new UILabel("SCORE: " + snakeModel.getScore(), 2);
                    scoreLabel.render(10, 10);
                }
            }
            else if (state == 2) {
                // game over state
                glClearColor(0.08f, 0.08f, 0.08f, 1.0f);
                glClear(GL_COLOR_BUFFER_BIT);
                
                UILabel gameOverLabel = new UILabel("GAME OVER", 4);
                UILabel scoreLabel = new UILabel("FINAL SCORE: " + (snakeModel != null ? snakeModel.getScore() : 0), 3);
                UILabel restartLabel = new UILabel("Click button to play again", 2);
                
                gameOverLabel.render(winW/2 - gameOverLabel.getPixelWidth()/2, winH/2 - 50);
                scoreLabel.render(winW/2 - scoreLabel.getPixelWidth()/2, winH/2);
                restartLabel.render(winW/2 - restartLabel.getPixelWidth()/2, winH/2 + 50);
                
                // show button for restart
                button.render();
            }

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        // cleanup
        app.destroy();
        System.exit(0);
    }

    // Helper methods for snake game rendering
    private static void drawCell(int gx, int gy, int cellSize, float r, float g, float b) {
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

    private static void drawGridBackground(int winW, int winH, int cellSize) {
        // full dark background
        glColor3f(0.06f, 0.06f, 0.06f);
        glBegin(GL_QUADS);
            glVertex2i(0, 0);
            glVertex2i(winW, 0);
            glVertex2i(winW, winH);
            glVertex2i(0, winH);
        glEnd();
        // optional light grid lines
        glColor3f(0.08f, 0.08f, 0.08f);
        glBegin(GL_LINES);
        for (int i = 0; i <= winW / cellSize; i++) {
            int x = i * cellSize;
            glVertex2i(x, 0); glVertex2i(x, winH);
        }
        for (int j = 0; j <= winH / cellSize; j++) {
            int y = j * cellSize;
            glVertex2i(0, y); glVertex2i(winW, y);
        }
        glEnd();
    }

}

























class UILabel {
    private final String text;
    private final double scale;

    public UILabel(String text, double scale) {
        this.text = text;
        this.scale = scale;
    }

    public void render(double x, double y) {
        drawLabel(text, x, y, scale);
    }

    private void drawLabel(String text, double startX, double startY, double scale) {
        java.util.Map<Character,int[]> glyphs = new java.util.HashMap<>();
        // (same glyph definitions as before… A-Z, 0-9, space)
         glyphs.put('A', new int[]{0b010,0b101,0b111,0b101,0b101});
        glyphs.put('B', new int[]{0b110,0b101,0b110,0b101,0b110});
        glyphs.put('C', new int[]{0b01110,0b10001,0b10000,0b10000,0b10000,0b10001,0b01110});
        glyphs.put('D', new int[]{0b110,0b101,0b101,0b101,0b110});
        glyphs.put('E', new int[]{0b111,0b100,0b111,0b100,0b111});
        glyphs.put('F', new int[]{0b111,0b100,0b111,0b100,0b100});
        glyphs.put('G', new int[]{0b1111,0b1000,0b1011,0b1001,0b1111});
        glyphs.put('H', new int[]{0b1001,0b1001,0b1111,0b1001,0b1001});
        glyphs.put('I', new int[]{0b111,0b010,0b010,0b010,0b111});
        glyphs.put('J', new int[]{0b111,0b001,0b001,0b101,0b111});
        glyphs.put('K', new int[]{0b101,0b101,0b110,0b101,0b101});
        glyphs.put('L', new int[]{0b100,0b100,0b100,0b100,0b111});
        glyphs.put('M', new int[]{0b10001,0b11011,0b10101,0b10001,0b10001});
        glyphs.put('N', new int[]{0b10001,0b11001,0b10101,0b10011,0b10001});
        glyphs.put('O', new int[]{0b01110,0b10001,0b10001,0b10001,0b01110});
        glyphs.put('P', new int[]{0b1111,0b1001,0b1111,0b1000,0b1000});
        glyphs.put('Q', new int[]{0b111,0b101,0b111,0b10,0b011});
        glyphs.put('R', new int[]{0b111,0b101,0b111,0b110,0b101});
        glyphs.put('S', new int[]{0b011,0b100,0b010,0b001,0b110});
        glyphs.put('T', new int[]{0b111,0b010,0b010,0b010,0b010});
        glyphs.put('U', new int[]{0b1001,0b1001,0b1001,0b1001,0b0110});
        glyphs.put('V', new int[]{0b10001,0b10001,0b01010,0b01010,0b00100});
        glyphs.put('W', new int[]{0b10001,0b10001,0b10101,0b10101,0b01010});
        glyphs.put('X', new int[]{0b10001,0b01010,0b00100,0b01010,0b10001});
        glyphs.put('Y', new int[]{0b10001,0b01010,0b00100,0b00100,0b00100});
        glyphs.put('Z', new int[]{0b11111,0b00010,0b00100,0b01000,0b11111});
        glyphs.put(' ', new int[]{0b000,0b000,0b000,0b000,0b000});
        // digits small subset
        glyphs.put('0', new int[]{0b111,0b101,0b101,0b101,0b111});
        glyphs.put('1', new int[]{0b010,0b110,0b010,0b010,0b111});
        glyphs.put('2', new int[]{0b111,0b001,0b111,0b100,0b111});
        glyphs.put('3', new int[]{0b111,0b001,0b011,0b001,0b111});
        glyphs.put('4', new int[]{0b1001,0b1001,0b1111,0b0001,0b0001});
        glyphs.put('5', new int[]{0b1111,0b1000,0b1111,0b0001,0b1111});
        glyphs.put('6', new int[]{0b11111,0b10000,0b11111,0b10001,0b11111});
        glyphs.put('7', new int[]{0b1111,0b0010,0b0010,0b0100,0b0100}); // 5 rows
        glyphs.put('8', new int[]{0b11111,0b10001,0b10001,0b11111,0b10001,0b10001,0b11111}); // 7 rows
        glyphs.put('9', new int[]{0b1111,0b1001,0b1111,0b0001,0b1111});

        // punctuations
        glyphs.put(',', new int[]{0b00000,0b00000,0b00000,0b00000,0b00000,0b01000,0b01000,0b10000});
        glyphs.put('=', new int[]{0b00000,0b01110,0b00000,0b01110});
        glyphs.put(':', new int[]{0b000,0b000,0b010,0b000,0b010,0b000,});
        glyphs.put(';', new int[]{0b000,0b000,0b010,0b000,0b010,0b100,});
        glyphs.put('[', new int[]{0b01110,0b01000,0b01000,0b01000,0b01000,0b01110,});
        glyphs.put(']', new int[]{0b01110,0b00010,0b00010,0b00010,0b00010,0b01110,});
        glyphs.put('.', new int[]{0b00000,0b00000,0b00000,0b00000,0b00000,0b00000,0b00110,0b0110});
        glyphs.put('!', new int[]{0b0010,0b0010,0b0010,0b0010,0b00000,0b00000,0b0010,0b00110});
        glyphs.put('?', new int[]{0b01110,0b10001,0b0010,0b0010,0b00000,0b00000,0b00110,0b00110});

        double cx = startX;
        glColor3f(0.96f, 0.96f, 0.96f);
        for (char ch : text.toUpperCase().toCharArray()) {
            int[] pattern = glyphs.getOrDefault(ch, glyphs.get(' '));
            int num_rows = getMaxNumRow(pattern);
            int num_cols = getMaxNumCol(pattern);
            double Hscale = (scale*7.0)/num_rows;
            double Lscale = (scale*5.0)/num_cols;
            for (int row = 0; row < num_rows; row++) {
                int bits = pattern[row];
                for (int col = 0; col < num_cols; col++) {
                    if (((bits >> (num_cols - col - 1)) & 1) == 1) {
                        double px = cx + col * Lscale;
                        double py = startY + row * Hscale;
                        glBegin(GL_QUADS);
                            glVertex2d(px, py);
                            glVertex2d(px + Lscale, py);
                            glVertex2d(px + Lscale, py + Hscale);
                            glVertex2d(px, py + Hscale);
                        glEnd();
                    }
                }
            }
            cx += (scale*6); // spacing
        }
    }

    static int getMaxNumCol(int[] sample) {
        int max_num_cols = -1;
        for (int index = 0; index < sample.length; index++) {
            int answer = 0;
            int exponent_of_two = 1;
            while (true) {
                int digitBound = (1 << exponent_of_two) - 1;
                if (sample[index] <= digitBound) {
                    answer = exponent_of_two;
                    break;
                }
                exponent_of_two++;
            }
            if (max_num_cols < answer) max_num_cols = answer;
        }
        return max_num_cols;
    }

    static int getMaxNumRow(int[] sample) {
        return sample.length;
    }

    public double getPixelWidth() {
        return text.length() * 6 * scale;
    }

    public double getPixelHeight() {
        return 5 * scale;
    }
}




class UIButton {
    private final int x, y, w, h;
    private final UILabel label;
    private boolean hover = false;
    private final Runnable onClick;

    public UIButton(int x, int y, int w, int h, String labelText, Runnable onClick) {
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.label = new UILabel(labelText, 3); // default scale 3
        this.onClick = onClick;
    }

    public boolean contains(int mx, int my) {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }

    public void setHover(boolean hover) { this.hover = hover; }

    public void click() {
        if (onClick != null) onClick.run();
    }

    public void render() {
        // background
        if (hover) glColor3f(0.18f, 0.45f, 0.18f);
        else       glColor3f(0.12f, 0.30f, 0.12f);
        glBegin(GL_QUADS);
            glVertex2i(x, y);
            glVertex2i(x + w, y);
            glVertex2i(x + w, y + h);
            glVertex2i(x, y + h);
        glEnd();

        // border
        glColor3f(0.95f, 0.95f, 0.95f);
        glLineWidth(1.5f);
        glBegin(GL_LINE_LOOP);
            glVertex2i(x + 1, y + 1);
            glVertex2i(x + w - 1, y + 1);
            glVertex2i(x + w - 1, y + h - 1);
            glVertex2i(x + 1, y + h - 1);
        glEnd();

        // position label in center
        double startX = x + (w - label.getPixelWidth()) / 2.0;
        double startY = y + (h - label.getPixelHeight()) / 2.0;
        label.render(startX, startY);
    }
}







// Color definition constants
class Colors {
    // Basic colors (RGB values 0.0f to 1.0f)
    public static final float[] RED = {1.0f, 0.0f, 0.0f};
    public static final float[] GREEN = {0.0f, 1.0f, 0.0f};
    public static final float[] BLUE = {0.0f, 0.0f, 1.0f};
    public static final float[] WHITE = {1.0f, 1.0f, 1.0f};
    public static final float[] BLACK = {0.0f, 0.0f, 0.0f};
    public static final float[] GRAY = {0.5f, 0.5f, 0.5f};
    public static final float[] YELLOW = {1.0f, 1.0f, 0.0f};
    public static final float[] CYAN = {0.0f, 1.0f, 1.0f};
    public static final float[] MAGENTA = {1.0f, 0.0f, 1.0f};
    
    // Custom colors
    public static final float[] DARK_RED = {0.8f, 0.2f, 0.2f};
    public static final float[] LIGHT_BLUE = {0.7f, 0.9f, 1.0f};
    public static final float[] ORANGE = {1.0f, 0.5f, 0.0f};
    public static final float[] PURPLE = {0.5f, 0.0f, 0.5f};
    
    // Helper method to set color from array
    public static void setColor(float[] color) {
        glColor3f(color[0], color[1], color[2]);
    }
}



// Snake Game Model (merged from SnakeGame)
class SnakeModel {
    public enum Direction { UP, DOWN, LEFT, RIGHT }

    private final int gridWidth, gridHeight;
    private final Deque<int[]> snake; // each int[] is {x,y}
    private Direction dir = Direction.RIGHT;
    private int[] food;
    private boolean grow = false;
    private boolean gameOver = false;
    private final Random rnd = new Random();

    public SnakeModel(int gridWidth, int gridHeight) {
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


class UIRectangle {
    public int x, y, w, h;
    private float[] color;
    
    // Constructor with default color
    public UIRectangle(int x, int y, int w, int h) {
        this.x = x; 
        this.y = y; 
        this.w = w; 
        this.h = h;
        this.color = Colors.DARK_RED; // default color
    }
    public UIRectangle(int x, int y, int w, int h, float[] color) {
        this.x = x; 
        this.y = y; 
        this.w = w; 
        this.h = h;
        this.color = color;
    }

        // Method to move the rectangle
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    // Method to set position
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
        
    public void render() {
        // Use the stored color
        Colors.setColor(this.color);
        glBegin(GL_QUADS);
            glVertex2i(x, y);
            glVertex2i(x + w, y);
            glVertex2i(x + w, y + h);
            glVertex2i(x, y + h);
        glEnd();
    }
}

