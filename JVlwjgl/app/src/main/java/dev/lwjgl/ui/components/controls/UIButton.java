package dev.lwjgl.ui.components.controls;

import dev.lwjgl.ui.Colors;
import dev.lwjgl.ui.components.UIComponent;
import dev.lwjgl.ui.components.UILabel;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.opengl.GL11.*;

/**
 * Basic button that displays a text label and executes a callback.
 */
public class UIButton extends UIComponent {

    private final UILabel label;
    private final Runnable onClick;
    private boolean hover = false;
    private final float[] bgColor = {0.12f, 0.30f, 0.12f};
    private final float[] hoverColor = {0.18f, 0.45f, 0.18f};
    private final float[] borderColor = {0.95f, 0.95f, 0.95f};

    public UIButton(String text, double x, double y, double w, double h, Runnable onClick) {
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
