package dev.lwjgl.ui;



import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;



/**
 * A utility class for storing color constants.
 * (Unchanged from original)
 */
public class Colors {
    // Basic colors (RGB values 0.0f to 1.0f)
    public static final float[] RED = {1.0f, 0.0f, 0.0f};
    public static final float[] GREEN = {0.0f, 1.0f, 0.0f};
    public static final float[] BLUE = {0.0f, 0.0f, 1.0f};
    public static final float[] WHITE = {1.0f, 1.0f, 1.0f};
    public static final float[] BLACK = {0.0f, 0.0f, 0.0f};
    public static final float[] GRAY = {0.5f, 0.5f, 0.5f};
    public static final float[] LIGHT_GRAY = {0.96f, 0.96f, 0.96f};

    public static final float[] YELLOW = {1.0f, 1.0f, 0.0f};
    public static final float[] CYAN = {0.0f, 1.0f, 1.0f};
    public static final float[] MAGENTA = {1.0f, 0.0f, 1.0f};
    
    // Custom colors
    public static final float[] DARK_RED = {0.8f, 0.2f, 0.2f};
    public static final float[] LIGHT_BLUE = {0.7f, 0.9f, 1.0f};
    public static final float[] ORANGE = {1.0f, 0.5f, 0.0f};
    public static final float[] PURPLE = {0.5f, 0.0f, 0.5f};
    
    /**
     * Helper method to set the current OpenGL color from an array.
     */
    public static void setColor(float[] color) {
        if (color != null && color.length >= 3) {
            glColor3f(color[0], color[1], color[2]);
        }
    }
}