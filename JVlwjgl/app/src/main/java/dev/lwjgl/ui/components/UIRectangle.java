package dev.lwjgl.ui.components;

import dev.lwjgl.ui.Colors;

import static org.lwjgl.opengl.GL11.*;

/**
 * Simple rectangle helper used by some UI widgets.
 */
public class UIRectangle extends UIComponent {

    private float[] color;

    public UIRectangle(double x, double y, double w, double h) {
        this(x, y, w, h, Colors.DARK_RED);
    }

    public UIRectangle(double x, double y, double w, double h, float[] color) {
        super(x, y, w, h);
        this.color = color;
    }

    @Override public void render() {
        Colors.setColor(this.color);
        renderRect(x,y,w,h);
    }
}
