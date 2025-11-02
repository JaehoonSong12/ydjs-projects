package dev.lwjgl.ui.components;

import dev.lwjgl.ui.Colors;

import static org.lwjgl.opengl.GL11.*;

/**
 * A simple rectangle drawing class.
 * (Unchanged from original, but with Javadoc added)
 */
public class UIRectangle extends UIComponent {
    /**
     * Constructs a rectangle with a default color (Dark Red).
     */
    public UIRectangle(int x, int y, int w, int h, float[] color) {
        super(x, y, w, h, color);
    }
    public UIRectangle(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    /**
     * Renders the rectangle as a filled quad.
     */
    public void render() {
        renderRect(x,y,w,h);
    }
}
