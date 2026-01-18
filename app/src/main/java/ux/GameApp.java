// Embed Orbitron TTF into the JAR and switch to STB baked-font (guaranteed consistent output).

// Add hover/pressed visuals and hand cursor (glfwSetCursor) for buttons.

// Replace immediate-mode rendering with a simple VBO/shader renderer (more modern GL).

// Implement a small LRU eviction for the JS2D text texture cache (to keep memory bounded).
package ux;






/**
 * for LWJGL apps the most common clean approach is a 
 * Screen / Game-State pattern combined with MVC (or a lightweight variant of MVC).
 * 
 * Reason: games need an explicit game loop and state management (screens), and 
 * MVC gives clear separation of model (state), view (render), and 
 * controller (input + business logic) inside each screen. 
 * 
 * ECS is common for entity/game logic, but for UI/navigation and screens 
 * MVC + ScreenManager is simpler and widely used.
 */


// File: GameApp.java
// Single-file LWJGL application with integrated FontRendererEfficient (Java2D -> GL textures).
// - multiple top-level classes, no static nested classes
// - uses system "Orbitron" if available, otherwise falls back to STB easy font
// - MVC + ScreenManager, clickable buttons, transitions, pause overlay with alpha
//
// Requires LWJGL (glfw, opengl, stb) in Gradle dependencies.

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.stb.STBEasyFont;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;


/* ------------------------- Public application entry ------------------------- */
public class GameApp {

    public static void main(String[] args) {
        new GameApp().run();
    }

    private final int WINDOW_W = 1280;
    private final int WINDOW_H = 720;
    private long window;
    private ScreenManager manager;

    public void run() {
        initGLFW();
        loop();
        // dispose fonts / GL textures before terminating
        if (manager != null && manager.getFont() != null) manager.getFont().dispose();
        glfwTerminate();
    }

    private void initGLFW() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        // NON-RESIZABLE window
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = glfwCreateWindow(WINDOW_W, WINDOW_H, "Pursuit of the Prism (LWJGL + Orbitron)", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create GLFW window");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        GL.createCapabilities();

        // blending for text & overlays
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // initialize manager with FontRendererEfficient (prefer Orbitron)
        manager = new ScreenManager(window, WINDOW_W, WINDOW_H,
                new FontRendererEfficient("Orbitron", 32, 512, null));

        // key callback -> forward to manager
        glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override public void invoke(long win, int key, int scancode, int action, int mods) {
                if (action == GLFW_PRESS) manager.onKeyPressed(key, mods);
                if (action == GLFW_RELEASE) manager.onKeyReleased(key, mods);
            }
        });

        // mouse button callback
        glfwSetMouseButtonCallback(window, (win, button, action, mods) -> {
            double[] x = new double[1], y = new double[1];
            glfwGetCursorPos(win, x, y);
            manager.onMouseButton(button, action, x[0], y[0]);
        });

        // cursor movement (optional hover handling)
        glfwSetCursorPosCallback(window, (win, xpos, ypos) -> manager.onMouseMove(xpos, ypos));

        // initial screen
        manager.setScreen(ScreenFactory.createMainScreen(manager));
    }

    private void loop() {
        double last = glfwGetTime();
        while (!glfwWindowShouldClose(window)) {
            double now = glfwGetTime();
            float dt = (float) (now - last);
            last = now;

            manager.update(dt);
            manager.setWindowSize(WINDOW_W, WINDOW_H);

            glViewport(0, 0, WINDOW_W, WINDOW_H);
            glClearColor(0.12f, 0.12f, 0.12f, 1f);
            glClear(GL_COLOR_BUFFER_BIT);

            manager.render(WINDOW_W, WINDOW_H);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }
}

/* ------------------------- MVC interfaces & Screen container ------------------------- */

interface Model { void update(float dt); }

interface View {
    void render(int w, int h, float alpha);
    default void onEnter() {}
    default void onExit() {}
}

interface Controller {
    default void update(float dt) {}
    default void onKeyPressed(int key, int mods) {}
    default void onKeyReleased(int key, int mods) {}
    default void onMouseButton(int button, int action, double x, double y) {}
    default void onMouseMove(double x, double y) {}
    default void onEnter() {}
    default void onExit() {}
}

class Screen {
    final String name;
    final Model model;
    final View view;
    final Controller controller;
    Screen(String name, Model m, View v, Controller c) { this.name = name; model = m; view = v; controller = c; }
    void enter() { if (view != null) view.onEnter(); if (controller != null) controller.onEnter(); }
    void exit()  { if (controller != null) controller.onExit(); if (view != null) view.onExit(); }
}

/* ------------------------- ScreenManager & Transitions ------------------------- */

enum TransitionType { NONE, FADE, SLIDE }

class ScreenManager {
    private final long glfwWindow;
    private Screen current, next;
    private TransitionType transition = TransitionType.NONE;
    private float duration = 0f;
    private float t = 0f;
    private boolean transitioning = false;

    private int windowW, windowH;
    private final FontRendererEfficient font;

    ScreenManager(long glfwWindow, int initialW, int initialH, FontRendererEfficient font) {
        this.glfwWindow = glfwWindow;
        this.windowW = initialW;
        this.windowH = initialH;
        this.font = font;
    }

    FontRendererEfficient getFont() { return font; }

    void setWindowSize(int w, int h) { this.windowW = w; this.windowH = h; }

    int getWindowWidth() { return windowW; }
    int getWindowHeight() { return windowH; }

    void setScreen(Screen s) {
        if (current != null) current.exit();
        current = s;
        if (current != null) current.enter();
        transitioning = false;
        updateTitle();
    }

    void changeScreen(Screen s, TransitionType type, float dur) {
        if (s == null) return;
        if (current == null || dur <= 0f || type == TransitionType.NONE) { setScreen(s); return; }
        next = s;
        transition = type;
        duration = Math.max(1e-4f, dur);
        t = 0f;
        transitioning = true;
    }

    void update(float dt) {
        if (current != null) {
            if (current.model != null) current.model.update(dt);
            if (current.controller != null) current.controller.update(dt);
        }
        if (transitioning) {
            if (next != null) {
                if (next.model != null) next.model.update(dt);
                if (next.controller != null) next.controller.update(dt);
            }
            t += dt;
            if (t >= duration) {
                if (current != null) current.exit();
                current = next;
                next = null;
                transitioning = false;
                transition = TransitionType.NONE;
                if (current != null) current.enter();
                updateTitle();
            }
        }
    }

    void render(int w, int h) {
        if (!transitioning) {
            if (current != null && current.view != null) current.view.render(w, h, 1f);
            return;
        }
        float p = clamp(t / duration, 0f, 1f);
        switch (transition) {
            case FADE:
                if (current != null && current.view != null) current.view.render(w, h, 1f - p);
                if (next != null && next.view != null) next.view.render(w, h, p);
                break;
            case SLIDE:
                float px = p * w;
                glPushMatrix();
                glTranslatef(-pxToNdc(px, w), 0f, 0f);
                if (current != null && current.view != null) current.view.render(w, h, 1f);
                glPopMatrix();

                glPushMatrix();
                glTranslatef(pxToNdc(w - px, w), 0f, 0f);
                if (next != null && next.view != null) next.view.render(w, h, 0.9f + 0.1f * p);
                glPopMatrix();
                break;
            default:
                if (next != null && next.view != null) next.view.render(w, h, 1f);
        }
    }

    void onKeyPressed(int key, int mods) {
        if (key == GLFW_KEY_ESCAPE) { closeWindow(); return; }
        if (transitioning && next != null) next.controller.onKeyPressed(key, mods);
        if (current != null) current.controller.onKeyPressed(key, mods);
    }

    void onKeyReleased(int key, int mods) {
        if (transitioning && next != null) next.controller.onKeyReleased(key, mods);
        if (current != null) current.controller.onKeyReleased(key, mods);
    }

    void onMouseButton(int button, int action, double x, double y) {
        if (transitioning && next != null) next.controller.onMouseButton(button, action, x, y);
        if (current != null) current.controller.onMouseButton(button, action, x, y);
    }

    void onMouseMove(double x, double y) {
        if (transitioning && next != null) next.controller.onMouseMove(x, y);
        if (current != null) current.controller.onMouseMove(x, y);
    }

    Screen getCurrentScreen() { return current; }

    void closeWindow() { glfwSetWindowShouldClose(glfwWindow, true); }

    private void updateTitle() {
        String name = (current == null) ? "none" : current.name;
        glfwSetWindowTitle(glfwWindow, "Screen: " + name);
    }

    private float clamp(float v, float a, float b) { return v < a ? a : (v > b ? b : v); }
    private float pxToNdc(float px, int w) { return (px / w) * 2f; }
}

/* ------------------------- Simple Model ------------------------- */

class SimpleModel implements Model {
    float t = 0f;
    @Override public void update(float dt) { t += dt; }
}

/* ------------------------- FontRendererEfficient (Java2D -> GL textures) ------------------------- */

class FontRendererEfficient {
    private final Font baseFont;
    private final AffineTransform extraTransform;
    private final int fontSizePx;
    private final int maxCacheEntries;
    private final Map<String, CachedTexture> cache;

    // fallback flag - if no system font found we'll use STB easy font fallback (no glyph textures)
    private final boolean hasSystemFont;

    FontRendererEfficient(String preferredFamily, int fontSizePx, int maxCacheEntries, AffineTransform extraTransform) {
        this.fontSizePx = Math.max(8, fontSizePx);
        this.extraTransform = extraTransform;
        this.maxCacheEntries = Math.max(16, maxCacheEntries);
        String chosen = findAvailableFamily(preferredFamily);
        if (chosen != null) {
            this.baseFont = new Font(chosen, Font.PLAIN, this.fontSizePx);
            this.hasSystemFont = true;
            System.out.println("FontRendererEfficient: using system font '" + chosen + "'");
        } else {
            this.baseFont = new Font("SansSerif", Font.PLAIN, this.fontSizePx);
            this.hasSystemFont = false;
            System.out.println("FontRendererEfficient: system font not found, will use STB easy fallback");
        }

        this.cache = new LinkedHashMap<String, CachedTexture>(128, 0.75f, true) {
            private static final long serialVersionUID = 1L;
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, CachedTexture> eldest) {
                if (size() > FontRendererEfficient.this.maxCacheEntries) {
                    CachedTexture ct = eldest.getValue();
                    if (ct != null && ct.texId > 0) glDeleteTextures(ct.texId);
                    return true;
                }
                return false;
            }
        };
    }

    private String findAvailableFamily(String preferredFamily) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] families = ge.getAvailableFontFamilyNames();
        if (preferredFamily != null) {
            for (String f : families) if (f.equalsIgnoreCase(preferredFamily.trim())) return f;
        }
        // no preferred, but return null if none present (to trigger STB fallback)
        for (String f : families) if ("SansSerif".equalsIgnoreCase(f) || "Arial".equalsIgnoreCase(f) || "DejaVu Sans".equalsIgnoreCase(f)) return f;
        return (families.length > 0) ? families[0] : null;
    }

    public synchronized void drawText(String text, int px, int py, int windowW, int windowH, float r, float g, float b, float a) {
        if (text == null || text.isEmpty()) return;

        if (hasSystemFont) {
            CachedTexture ct = cache.get(text);
            if (ct == null) {
                ct = createTextureForString(text);
                if (ct == null) return;
                cache.put(text, ct);
            }
            drawTexture(ct.texId, px, py, ct.w, ct.h, windowW, windowH, r, g, b, a);
            return;
        }

        // fallback: STB easy font (monochrome)
        ByteBuffer vb = BufferUtils.createByteBuffer(text.length() * 270);
        int quads = STBEasyFont.stb_easy_font_print(px, py, text, null, vb);
        if (quads <= 0) return;

        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        glOrtho(0, windowW, windowH, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();

        glDisable(GL_TEXTURE_2D);
        glColor4f(r, g, b, a);

        vb.rewind();
        glEnableClientState(GL_VERTEX_ARRAY);
        glVertexPointer(2, GL_FLOAT, 16, vb);
        glDrawArrays(GL_QUADS, 0, quads * 4);
        glDisableClientState(GL_VERTEX_ARRAY);

        glMatrixMode(GL_PROJECTION); glPopMatrix();
        glMatrixMode(GL_MODELVIEW); glPopMatrix();
        glPopAttrib();
    }

    private CachedTexture createTextureForString(String text) {
        Font f = baseFont;
        if (extraTransform != null) f = f.deriveFont(extraTransform);

        // measure
        BufferedImage tmp = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = tmp.createGraphics();
        g2.setFont(f);
        FontMetrics fm = g2.getFontMetrics();
        int textW = fm.stringWidth(text);
        int textH = fm.getHeight();
        g2.dispose();

        if (textW <= 0 || textH <= 0) return null;

        int padding = Math.max(2, fontSizePx / 6);
        int imgW = textW + padding * 2;
        int imgH = textH + padding * 2;

        BufferedImage img = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_ARGB);
        g2 = img.createGraphics();
        g2.setFont(f);
        g2.setColor(new Color(255,255,255,255)); // white glyphs
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics fm2 = g2.getFontMetrics();
        int ascent = fm2.getAscent();
        g2.drawString(text, padding, padding + ascent);
        g2.dispose();

        int tex = uploadBufferedImageAsTexture(img);
        return new CachedTexture(tex, imgW, imgH);
    }

    private int uploadBufferedImageAsTexture(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int[] pixels = new int[w * h];
        img.getRGB(0, 0, w, h, pixels, 0, w);
        ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * 4);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int p = pixels[y * w + x];
                byte a = (byte)((p >> 24) & 0xFF);
                byte r = (byte)((p >> 16) & 0xFF);
                byte g = (byte)((p >> 8) & 0xFF);
                byte b = (byte)(p & 0xFF);
                buffer.put(r).put(g).put(b).put(a);
            }
        }
        buffer.flip();

        int tex = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, tex);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glBindTexture(GL_TEXTURE_2D, 0);
        return tex;
    }

    private void drawTexture(int tex, int px, int py, int tw, int th, int windowW, int windowH, float r, float g, float b, float a) {
        if (tex <= 0) return;
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, tex);

        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION); glPushMatrix(); glLoadIdentity();
        glOrtho(0, windowW, windowH, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW); glPushMatrix(); glLoadIdentity();

        glColor4f(r, g, b, a);
        glBegin(GL_QUADS);
            glTexCoord2f(0f, 0f); glVertex2f(px, py);
            glTexCoord2f(1f, 0f); glVertex2f(px + tw, py);
            glTexCoord2f(1f, 1f); glVertex2f(px + tw, py + th);
            glTexCoord2f(0f, 1f); glVertex2f(px, py + th);
        glEnd();

        glMatrixMode(GL_PROJECTION); glPopMatrix(); glMatrixMode(GL_MODELVIEW); glPopMatrix();
        glPopAttrib();

        glBindTexture(GL_TEXTURE_2D, 0);
        glDisable(GL_TEXTURE_2D);

        glColor4f(1f,1f,1f,1f);
    }

    public synchronized void clearCache() {
        for (CachedTexture ct : cache.values()) {
            if (ct != null && ct.texId > 0) glDeleteTextures(ct.texId);
        }
        cache.clear();
    }

    public synchronized void dispose() {
        clearCache();
    }

    private static class CachedTexture {
        final int texId;
        final int w, h;
        CachedTexture(int texId, int w, int h) { this.texId = texId; this.w = w; this.h = h; }
    }
}

/* ------------------------- BaseView helpers ------------------------- */

abstract class BaseView implements View {
    final float bgR, bgG, bgB;
    BaseView(float r, float g, float b) { bgR=r; bgG=g; bgB=b; }

    @Override public void render(int w, int h, float alpha) {
        glPushMatrix();
        glColor4f(bgR, bgG, bgB, clamp(alpha));
        fillRectNdc(-1f, -1f, 2f, 2f);

        glColor4f(0.95f,0.95f,0.95f, 0.95f * alpha);
        fillRectNdc(-0.5f, -0.3f, 1f, 0.6f);

        glColor4f(0.18f,0.18f,0.18f, 0.95f * alpha);
        fillRectNdc(-0.5f, 0.22f, 1f, 0.12f);

        drawContents(w, h, alpha);
        glPopMatrix();
    }

    protected abstract void drawContents(int w, int h, float alpha);

    protected void fillRectNdc(float x, float y, float w, float h) {
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + w, y);
        glVertex2f(x + w, y + h);
        glVertex2f(x, y + h);
        glEnd();
    }

    protected void fillRectPixels(int px, int py, int pw, int ph, int windowW, int windowH) {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION); glPushMatrix(); glLoadIdentity();
        glOrtho(0, windowW, windowH, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW); glPushMatrix(); glLoadIdentity();

        glBegin(GL_QUADS);
        glVertex2f(px, py);
        glVertex2f(px + pw, py);
        glVertex2f(px + pw, py + ph);
        glVertex2f(px, py + ph);
        glEnd();

        glPopMatrix(); glMatrixMode(GL_PROJECTION); glPopMatrix(); glMatrixMode(GL_MODELVIEW);
        glPopAttrib();
    }

    protected void drawRectPixels(int px, int py, int pw, int ph, int windowW, int windowH) {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION); glPushMatrix(); glLoadIdentity();
        glOrtho(0, windowW, windowH, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW); glPushMatrix(); glLoadIdentity();

        glBegin(GL_LINE_LOOP);
        glVertex2f(px, py);
        glVertex2f(px + pw, py);
        glVertex2f(px + pw, py + ph);
        glVertex2f(px, py + ph);
        glEnd();

        glPopMatrix(); glMatrixMode(GL_PROJECTION); glPopMatrix(); glMatrixMode(GL_MODELVIEW);
        glPopAttrib();
    }

    protected float clamp(float v) { return v < 0f ? 0f : (v > 1f ? 1f : v); }
}

/* ------------------------- Concrete Views implementing required flow ------------------------- */

// Main View
class MainView extends BaseView {
    private final ScreenManager manager;
    MainView(ScreenManager m) { super(0.15f, 0.45f, 0.85f); manager = m; }

    @Override protected void drawContents(int w, int h, float a) {
        int bw = 240, bh = 48;
        int cx = w/2;
        int bx = cx - bw/2;
        int startBy = h/2 - 20;
        int quitBy = h/2 + 40;

        glColor4f(0.9f, 0.9f, 0.9f, a);
        fillRectPixels(bx, startBy, bw, bh, w, h);
        fillRectPixels(bx, quitBy, bw, bh, w, h);

        glColor4f(0.1f, 0.1f, 0.1f, a);
        drawRectPixels(bx, startBy, bw, bh, w, h);
        drawRectPixels(bx, quitBy, bw, bh, w, h);

        String title = "Pursuit of the Prism";
        manager.getFont().drawText(title, cx - (title.length()*10), h/2 - 140, w, h, 1f, 1f, 1f, 1f);

        manager.getFont().drawText("Start Game", bx + 24, startBy + 30, w, h, 0f,0f,0f,1f);
        manager.getFont().drawText("Quit", bx + 24, quitBy + 30, w, h, 0f,0f,0f,1f);
    }
}

// Difficulty view
class DifficultyView extends BaseView {
    private final ScreenManager manager;
    DifficultyView(ScreenManager m) { super(0.95f, 0.55f, 0.12f); manager = m; }

    @Override protected void drawContents(int w, int h, float a) {
        int bx = 20, by = 20, bw = 100, bh = 36;
        glColor4f(0.95f,0.95f,0.95f,a);
        fillRectPixels(bx, by, bw, bh, w, h);
        glColor4f(0.12f,0.12f,0.12f,a);
        drawRectPixels(bx, by, bw, bh, w, h);
        manager.getFont().drawText("Home", bx + 12, by + 24, w, h, 0f,0f,0f,1f);

        String[] labels = {"Easy","Normal","Hard","Impossible","Nightmare"};
        int startY = h/2 - 100;
        int pillW = 360, pillH = 50;
        int px = w/2 - pillW/2;
        for (int i=0;i<labels.length;i++) {
            int py = startY + i*(pillH+12);
            glColor4f(0.95f,0.95f,0.95f,a);
            fillRectPixels(px, py, pillW, pillH, w, h);
            glColor4f(0.12f,0.12f,0.12f,a);
            drawRectPixels(px, py, pillW, pillH, w, h);
            manager.getFont().drawText(labels[i], px + 24, py + 32, w, h, 0f,0f,0f,1f);
        }
        manager.getFont().drawText("Choose difficulty or click Home", 20, h - 36, w, h, 0f,0f,0f,1f);
    }
}

// Gameplay view
class GameplayView extends BaseView {
    private final ScreenManager manager;
    GameplayView(ScreenManager m) { super(0.06f, 0.65f, 0.25f); manager = m; }

    @Override protected void drawContents(int w, int h, float a) {
        float t = 0f;
        Screen sc = manager.getCurrentScreen();
        if (sc != null && sc.model instanceof SimpleModel) t = ((SimpleModel)sc.model).t;
        float x = (float)Math.sin(t * 1.2) * 120f;

        int pw = 48, ph = 48;
        int px = w/2 + Math.round(x);
        int py = h/2;
        glColor4f(0.95f,0.2f,0.2f,a);
        fillRectPixels(px - pw/2, py - ph/2, pw, ph, w, h);

        int bx = 20, by = 20, bw = 120, bh = 36;
        glColor4f(0.95f,0.95f,0.95f,a);
        fillRectPixels(bx, by, bw, bh, w, h);
        glColor4f(0.12f,0.12f,0.12f,a);
        drawRectPixels(bx, by, bw, bh, w, h);
        manager.getFont().drawText("Pause", bx + 12, by + 24, w, h, 0f,0f,0f,1f);

        manager.getFont().drawText("Gameplay - press P to pause", 12, h - 20, w, h, 1f,1f,1f,1f);
    }
}

// Pause view
class PauseView extends BaseView {
    private final ScreenManager manager;
    PauseView(ScreenManager m) { super(0.35f, 0.35f, 0.35f); manager = m; }

    @Override protected void drawContents(int w, int h, float a) {
        // translucent overlay
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION); glPushMatrix(); glLoadIdentity();
        glOrtho(0, w, h, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW); glPushMatrix(); glLoadIdentity();

        glColor4f(0f,0f,0f,0.45f);
        glBegin(GL_QUADS);
        glVertex2f(0,0); glVertex2f(w,0); glVertex2f(w,h); glVertex2f(0,h);
        glEnd();

        glPopMatrix(); glMatrixMode(GL_PROJECTION); glPopMatrix(); glMatrixMode(GL_MODELVIEW);
        glPopAttrib();

        int mw = 420, mh = 220;
        int mx = w/2 - mw/2, my = h/2 - mh/2;
        glColor4f(0.95f,0.95f,0.95f,0.95f);
        fillRectPixels(mx, my, mw, mh, w, h);
        glColor4f(0.12f,0.12f,0.12f,1f);
        drawRectPixels(mx, my, mw, mh, w, h);

        int bw = 160, bh = 48;
        int rx = mx + 40, ry = my + 60;
        int ex = mx + mw - bw - 40, ey = ry;
        glColor4f(0.92f,0.92f,0.92f,1f);
        fillRectPixels(rx, ry, bw, bh, w, h);
        fillRectPixels(ex, ey, bw, bh, w, h);
        glColor4f(0.12f,0.12f,0.12f,1f);
        drawRectPixels(rx, ry, bw, bh, w, h);
        drawRectPixels(ex, ey, bw, bh, w, h);

        manager.getFont().drawText("Resume", rx + 36, ry + 32, w, h, 0f,0f,0f,1f);
        manager.getFont().drawText("End Game", ex + 24, ey + 32, w, h, 0f,0f,0f,1f);
    }
}

// EndRun view
class EndRunView extends BaseView {
    private final ScreenManager manager;
    EndRunView(ScreenManager m) { super(0.85f, 0.12f, 0.15f); manager = m; }

    @Override protected void drawContents(int w, int h, float a) {
        manager.getFont().drawText("Run Ended - click or press G for GameOver", 12, 40, w, h, 1f,1f,1f,1f);
    }
}

// GameOver view
class GameOverView extends BaseView {
    private final ScreenManager manager;
    GameOverView(ScreenManager m) { super(0.95f,0.95f,0.25f); manager = m; }

    @Override protected void drawContents(int w, int h, float a) {
        int bw = 240, bh = 48;
        int cx = w/2;
        int rx = cx - bw/2;
        int ry = h/2 - 20;
        int hy = ry + 72;

        glColor4f(0.95f,0.95f,0.95f,a);
        fillRectPixels(rx, ry, bw, bh, w, h);
        fillRectPixels(rx, hy, bw, bh, w, h);
        glColor4f(0.12f,0.12f,0.12f,a);
        drawRectPixels(rx, ry, bw, bh, w, h);
        drawRectPixels(rx, hy, bw, bh, w, h);

        manager.getFont().drawText("Retry", rx + 80, ry + 32, w, h, 0f,0f,0f,1f);
        manager.getFont().drawText("Return Home", rx + 36, hy + 32, w, h, 0f,0f,0f,1f);
    }
}

/* ------------------------- Controllers implementing semantics & mouse hit testing ------------------------- */

class MainController implements Controller {
    private final ScreenManager manager;
    MainController(ScreenManager m) { manager = m; }

    @Override public void onKeyPressed(int key, int mods) {
        if (key == GLFW_KEY_1) manager.changeScreen(ScreenFactory.createDifficultyScreen(manager), TransitionType.SLIDE, 0.45f);
        if (key == GLFW_KEY_Q) manager.closeWindow();
    }

    @Override public void onMouseButton(int button, int action, double x, double y) {
        if (button != GLFW_MOUSE_BUTTON_LEFT || action != GLFW_PRESS) return;
        int w = manager.getWindowWidth(), h = manager.getWindowHeight();
        int bw = 240, bh = 48; int cx = w/2;
        int bx = cx - bw/2;
        int startBy = h/2 - 20;
        int quitBy = h/2 + 40;
        if (pointInRect(x, y, bx, startBy, bw, bh)) {
            manager.changeScreen(ScreenFactory.createDifficultyScreen(manager), TransitionType.SLIDE, 0.45f);
        } else if (pointInRect(x, y, bx, quitBy, bw, bh)) {
            manager.closeWindow();
        }
    }

    private boolean pointInRect(double mx, double my, int px, int py, int pw, int ph) {
        return mx >= px && mx <= px + pw && my >= py && my <= py + ph;
    }
}

class DifficultyController implements Controller {
    private final ScreenManager manager;
    DifficultyController(ScreenManager m) { manager = m; }

    @Override public void onKeyPressed(int key, int mods) {
        if (key == GLFW_KEY_3) manager.changeScreen(ScreenFactory.createGameplayScreen(manager), TransitionType.FADE, 0.4f);
        if (key == GLFW_KEY_H) manager.changeScreen(ScreenFactory.createMainScreen(manager), TransitionType.SLIDE, 0.35f);
    }

    @Override public void onMouseButton(int button, int action, double x, double y) {
        if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS) {
            if (x >= 20 && x <= 120 && y >= 20 && y <= 56) {
                manager.changeScreen(ScreenFactory.createMainScreen(manager), TransitionType.SLIDE, 0.35f);
                return;
            }
            manager.changeScreen(ScreenFactory.createGameplayScreen(manager), TransitionType.FADE, 0.4f);
        }
    }
}

class GameplayController implements Controller {
    private final ScreenManager manager;
    private final SimpleModel model;
    GameplayController(ScreenManager m, SimpleModel model) { manager = m; this.model = model; }

    @Override public void update(float dt) { model.update(dt); }

    @Override public void onKeyPressed(int key, int mods) {
        if (key == GLFW_KEY_P) manager.changeScreen(ScreenFactory.createPauseScreen(manager), TransitionType.FADE, 0.18f);
        if (key == GLFW_KEY_E) manager.changeScreen(ScreenFactory.createEndRunScreen(manager), TransitionType.SLIDE, 0.4f);
    }

    @Override public void onMouseButton(int button, int action, double x, double y) {
        if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS) {
            if (x >= 20 && x <= 140 && y >= 20 && y <= 56) {
                manager.changeScreen(ScreenFactory.createPauseScreen(manager), TransitionType.FADE, 0.18f);
            }
        }
    }
}

class PauseController implements Controller {
    private final ScreenManager manager;
    PauseController(ScreenManager m) { manager = m; }

    @Override public void onKeyPressed(int key, int mods) {
        if (key == GLFW_KEY_R) manager.changeScreen(ScreenFactory.createGameplayScreen(manager), TransitionType.FADE, 0.18f);
        if (key == GLFW_KEY_E) manager.changeScreen(ScreenFactory.createMainScreen(manager), TransitionType.SLIDE, 0.4f);
    }

    @Override public void onMouseButton(int button, int action, double x, double y) {
        if (button != GLFW_MOUSE_BUTTON_LEFT || action != GLFW_PRESS) return;
        int w = manager.getWindowWidth(), h = manager.getWindowHeight();
        int mw = 420, mh = 220, mx = w/2 - mw/2, my = h/2 - mh/2;
        int bw = 160, bh = 48;
        int rx = mx + 40, ry = my + 60;
        int ex = mx + mw - bw - 40, ey = ry;
        if (pointInRect(x, y, rx, ry, bw, bh)) {
            manager.changeScreen(ScreenFactory.createGameplayScreen(manager), TransitionType.FADE, 0.18f);
        } else if (pointInRect(x, y, ex, ey, bw, bh)) {
            manager.changeScreen(ScreenFactory.createMainScreen(manager), TransitionType.SLIDE, 0.4f);
        }
    }

    private boolean pointInRect(double mx, double my, int px, int py, int pw, int ph) {
        return mx >= px && mx <= px + pw && my >= py && my <= py + ph;
    }
}

class EndRunController implements Controller {
    private final ScreenManager manager;
    EndRunController(ScreenManager m) { manager = m; }

    @Override public void onKeyPressed(int key, int mods) {
        if (key == GLFW_KEY_G) manager.changeScreen(ScreenFactory.createGameOverScreen(manager), TransitionType.FADE, 0.35f);
    }

    @Override public void onMouseButton(int button, int action, double x, double y) {
        if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS) {
            manager.changeScreen(ScreenFactory.createGameOverScreen(manager), TransitionType.FADE, 0.35f);
        }
    }
}

class GameOverController implements Controller {
    private final ScreenManager manager;
    GameOverController(ScreenManager m) { manager = m; }

    @Override public void onKeyPressed(int key, int mods) {
        if (key == GLFW_KEY_R) manager.changeScreen(ScreenFactory.createGameplayScreen(manager), TransitionType.FADE, 0.25f);
        if (key == GLFW_KEY_H) manager.changeScreen(ScreenFactory.createMainScreen(manager), TransitionType.SLIDE, 0.45f);
    }

    @Override public void onMouseButton(int button, int action, double x, double y) {
        if (button != GLFW_MOUSE_BUTTON_LEFT || action != GLFW_PRESS) return;
        int bw = 240, bh = 48;
        int cx = manager.getWindowWidth()/2;
        int rx = cx - bw/2, ry = manager.getWindowHeight()/2 - 20;
        int hy = ry + 72;
        if (x >= rx && x <= rx + bw && y >= ry && y <= ry + bh) {
            manager.changeScreen(ScreenFactory.createGameplayScreen(manager), TransitionType.FADE, 0.25f);
        } else if (x >= rx && x <= rx + bw && y >= hy && y <= hy + bh) {
            manager.changeScreen(ScreenFactory.createMainScreen(manager), TransitionType.SLIDE, 0.45f);
        }
    }
}

/* ------------------------- Screen factory ------------------------- */

class ScreenFactory {
    static Screen createMainScreen(ScreenManager m) {
        return new Screen("Main", new SimpleModel(), new MainView(m), new MainController(m));
    }
    static Screen createDifficultyScreen(ScreenManager m) {
        return new Screen("Difficulty", new SimpleModel(), new DifficultyView(m), new DifficultyController(m));
    }
    static Screen createGameplayScreen(ScreenManager m) {
        SimpleModel model = new SimpleModel();
        return new Screen("Gameplay", model, new GameplayView(m), new GameplayController(m, model));
    }
    static Screen createPauseScreen(ScreenManager m) {
        return new Screen("Pause", new SimpleModel(), new PauseView(m), new PauseController(m));
    }
    static Screen createEndRunScreen(ScreenManager m) {
        return new Screen("EndRun", new SimpleModel(), new EndRunView(m), new EndRunController(m));
    }
    static Screen createGameOverScreen(ScreenManager m) {
        return new Screen("GameOver", new SimpleModel(), new GameOverView(m), new GameOverController(m));
    }
}
