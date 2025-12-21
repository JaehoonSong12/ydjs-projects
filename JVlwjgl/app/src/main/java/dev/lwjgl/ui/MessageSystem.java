package dev.lwjgl.ui;

import java.awt.geom.AffineTransform;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.GL_ALL_ATTRIB_BITS;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 * Message system for displaying on-screen notifications with system fonts.
 * Messages fade out over time and can be frozen when time is paused.
 */
public class MessageSystem {
    private final Deque<Message> messages = new ArrayDeque<>();
    private static final int MAX_MESSAGES = 6;
    private static final double DISPLAY_DURATION = 5.5;
    private final int screenWidth;
    private final int screenHeight;
    private final String fontName;
    private final int fontSize;
    private final AffineTransform fontTransform;

    /**
     * Creates a message system with default Orbitron font.
     */
    public MessageSystem(int screenWidth, int screenHeight) {
        this(screenWidth, screenHeight, "Orbitron", 24, AffineTransform.getScaleInstance(0.6, 1.2));
    }

    /**
     * Creates a message system with custom font settings.
     * 
     * @param screenWidth Screen width for positioning
     * @param screenHeight Screen height for positioning
     * @param fontName Font name (e.g., "Orbitron", "Arial")
     * @param fontSize Font size
     * @param fontTransform Optional font transform (e.g., for scaling)
     */
    public MessageSystem(int screenWidth, int screenHeight, String fontName, int fontSize, AffineTransform fontTransform) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.fontTransform = fontTransform;
    }

    /**
     * Shows a message that will be displayed for DISPLAY_DURATION seconds.
     */
    public void showMessage(String text) {
        double now = GLFW.glfwGetTime();
        if (messages.size() > MAX_MESSAGES) {
            Message oldest = messages.removeLast();
            glDeleteTextures(oldest.texId);
        }
        messages.addFirst(new Message(text, now));
    }

    /**
     * Renders all active messages.
     * 
     * @param freezeTime If true, messages won't fade out or expire
     */
    public void render(boolean freezeTime) {
        if (messages.isEmpty()) return;

        glPushAttrib(GL_ALL_ATTRIB_BITS);
        double now = GLFW.glfwGetTime();

        Iterator<Message> it = messages.iterator();
        while (it.hasNext()) {
            Message m = it.next();
            double age = now - m.timestamp;
            if (age > DISPLAY_DURATION) {
                if (freezeTime) break;
                glDeleteTextures(m.texId);
                it.remove();
            }
        }

        int idx = 0;
        for (Message m : messages) {
            double age = now - m.timestamp;
            float alpha = (float) Math.max(0.0, 1.0 - (age / DISPLAY_DURATION));
            if (freezeTime) alpha = 1.0f;
            drawMessage(m.texId, m.textWidth, m.textHeight, idx, alpha);
            idx++;
        }

        glPopAttrib();
    }

    private void drawMessage(int texId, int textW, int textH, int index, float alpha) {
        int boxW = textW + 20;
        int boxH = textH + 20;
        int x = (screenWidth - boxW);
        int y = (int) (screenHeight - MAX_MESSAGES * (boxH + 10)) + index * (boxH + 10);

        glPushAttrib(GL_ALL_ATTRIB_BITS);
        glMatrixMode(GL_TEXTURE);
        glPushMatrix();
        glLoadIdentity();
        glMatrixMode(GL_MODELVIEW);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glDisable(GL_TEXTURE_2D);
        glColor4f(0f, 0f, 0f, 0.75f * alpha);
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + boxW, y);
        glVertex2f(x + boxW, y + boxH);
        glVertex2f(x, y + boxH);
        glEnd();

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, texId);
        glColor4f(1f, 1f, 1f, alpha);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 1);
        glVertex2f(x + 10, y + 10);
        glTexCoord2f(1, 1);
        glVertex2f(x + 10 + textW, y + 10);
        glTexCoord2f(1, 0);
        glVertex2f(x + 10 + textW, y + 10 + textH);
        glTexCoord2f(0, 0);
        glVertex2f(x + 10, y + 10 + textH);
        glEnd();

        glDisable(GL_BLEND);
        glMatrixMode(GL_TEXTURE);
        glPopMatrix();
        glMatrixMode(GL_MODELVIEW);
        glPopAttrib();
    }

    /**
     * Clears all messages and frees their textures.
     */
    public void clear() {
        for (Message m : messages) {
            glDeleteTextures(m.texId);
        }
        messages.clear();
    }

    /**
     * Cleanup method - same as clear().
     */
    public void cleanup() {
        clear();
    }

    private class Message {
        final int texId;
        final int textWidth, textHeight;
        final double timestamp;

        Message(String text, double timestamp) {
            this.timestamp = timestamp;
            int[] result = FontRenderer.createTextTexture(text, fontName, fontSize, 10, fontTransform);
            this.texId = result[0];
            this.textWidth = result[1];
            this.textHeight = result[2];
        }
    }
}

