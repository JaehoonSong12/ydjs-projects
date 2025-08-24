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

/**
 * Single-button LWJGL app. Click the button, prints "hello world" to console.
 */
public class ClickButtonApp {

    public static void main(String[] args) {
        // init GLFW
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            System.err.println("GLFW init failed");
            System.exit(-1);
        }

        final int winW = 420;
        final int winH = 260;

        glfwDefaultWindowHints();
        long window = glfwCreateWindow(winW, winH, "Single Button - click prints 'hello world'", NULL, NULL);
        if (window == NULL) {
            System.err.println("Failed to create window");
            glfwTerminate();
            System.exit(-1);
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        GL.createCapabilities();

        // Set a simple orthographic projection where y increases downward (match GLFW cursor coords)
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, winW, winH, 0, -1, 1); // left, right, bottom, top -> top=0 makes y go down
        glMatrixMode(GL_MODELVIEW);

        // Create a single button centered
        UIButton button = new UIButton(winW/2 - 100, winH/2 - 24, 200, 48, "CLICK ME", () -> {
            // action: print hello world
            System.out.println("hello world");
        });

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

        // Keyboard: ESC to exit
        glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) glfwSetWindowShouldClose(win, true);
        });

        // Main loop
        while (!glfwWindowShouldClose(window)) {
            // update hover state from cursor
            double[] xd = new double[1], yd = new double[1];
            glfwGetCursorPos(window, xd, yd);
            int mx = (int) xd[0];
            int my = (int) yd[0];
            button.setHover(button.contains(mx, my));

            // render
            glClearColor(0.08f, 0.08f, 0.08f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            // draw button
            button.render();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwDestroyWindow(window);
        glfwTerminate();
        System.exit(0);
    }

    // Very small UI button class (simple immediate-mode rendering)
    public static class UIButton {
        private final int x, y, w, h;
        private final String label;
        private boolean hover = false;
        private final Runnable onClick;

        public UIButton(int x, int y, int w, int h, String label, Runnable onClick) {
            this.x = x; this.y = y; this.w = w; this.h = h;
            this.label = label;
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

            // simple label (centered) - we draw a very tiny monospaced block-letter approximation
            // For readability we draw each character as a 3x5 block font using quads.
            drawLabel(label, x + (w - label.length() * 6 * 3) / 2, y + (h - 5*3) / 2, 3);
        }

        // crude 3x5 block letters for A-Z, 0-9 (very small subset). If char missing, draw empty space.
        private void drawLabel(String text, int startX, int startY, int scale) {
            // small patterns for A,C,E,H,I,K,L,O,P,R,S,T,U (only what might be used). Missing -> blank.
            java.util.Map<Character,int[]> glyphs = new java.util.HashMap<>();
            glyphs.put('C', new int[]{0b111,0b100,0b100,0b100,0b111});
            glyphs.put('L', new int[]{0b100,0b100,0b100,0b100,0b111});
            glyphs.put('I', new int[]{0b111,0b010,0b010,0b010,0b111});
            glyphs.put('K', new int[]{0b101,0b110,0b100,0b110,0b101});
            glyphs.put('O', new int[]{0b111,0b101,0b101,0b101,0b111});
            glyphs.put('P', new int[]{0b111,0b101,0b111,0b100,0b100});
            glyphs.put('S', new int[]{0b111,0b100,0b111,0b001,0b111});
            glyphs.put('T', new int[]{0b111,0b010,0b010,0b010,0b010});
            glyphs.put('R', new int[]{0b111,0b101,0b111,0b110,0b101});
            glyphs.put('A', new int[]{0b010,0b101,0b111,0b101,0b101});
            glyphs.put('E', new int[]{0b111,0b100,0b110,0b100,0b111});
            glyphs.put('H', new int[]{0b101,0b101,0b111,0b101,0b101});
            glyphs.put('U', new int[]{0b101,0b101,0b101,0b101,0b111});
            glyphs.put('M', new int[]{0b101,0b111,0b111,0b101,0b101});
            glyphs.put(' ', new int[]{0,0,0,0,0});
            glyphs.put('C', glyphs.get('C')); // keep
            // digits small subset
            glyphs.put('1', new int[]{0b010,0b110,0b010,0b010,0b111});
            glyphs.put('2', new int[]{0b111,0b001,0b111,0b100,0b111});
            glyphs.put('3', new int[]{0b111,0b001,0b011,0b001,0b111});
            glyphs.put('0', new int[]{0b111,0b101,0b101,0b101,0b111});

            int cx = startX;
            glColor3f(0.96f, 0.96f, 0.96f);
            for (char ch : text.toUpperCase().toCharArray()) {
                int[] pattern = glyphs.getOrDefault(ch, glyphs.get(' '));
                for (int row = 0; row < 5; row++) {
                    int bits = pattern[row];
                    for (int col = 0; col < 3; col++) {
                        if (((bits >> (2 - col)) & 1) == 1) {
                            int px = cx + col * scale;
                            int py = startY + row * scale;
                            glBegin(GL_QUADS);
                                glVertex2i(px, py);
                                glVertex2i(px + scale, py);
                                glVertex2i(px + scale, py + scale);
                                glVertex2i(px, py + scale);
                            glEnd();
                        }
                    }
                }
                cx += 4 * scale; // spacing
            }
        }
    }
}
