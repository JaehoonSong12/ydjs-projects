package dev.lwjgl.ui.components;

import dev.lwjgl.ui.Colors;

import static org.lwjgl.opengl.GL11.*;

/**
 * Simple rectangle helper used by some UI widgets.
 */
public class UIRectangle {

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
