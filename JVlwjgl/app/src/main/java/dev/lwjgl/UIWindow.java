package dev.lwjgl;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * A wrapper class for the GLFW window.
 * This class encapsulates all window creation and management logic.
 * It is no longer a static utility, but a proper object.
 */
public class UIWindow {

    /**
     * The handle to the native GLFW window.
     */
    private long windowHandle;

    public long getWindowHandle() {
        return windowHandle;
    }

    private int winW;

    public int getWinW() {
        return winW;
    }

    private int winH;

    public int getWinH() {
        return winH;
    }

    private String title;

    /**
     * Creates and initializes a new UIWindow.
     *
     * @param title The initial window title.
     * @param winW  The initial window width.
     * @param winH  The initial window height.
     */
    public UIWindow(String title, int winW, int winH) {
        this.winW = winW;
        this.winH = winH;
        this.title = title;
        initLWJGL();
    }

    /**
     * Handles the low-level GLFW and OpenGL setup.
     */
    private void initLWJGL() {
        // GLFW is assumed to be initialized by the main app

        // create window
        glfwDefaultWindowHints();
        long handle = glfwCreateWindow(this.winW, this.winH, this.title, NULL, NULL);
        if (handle == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }
        this.windowHandle = handle;

        // make context current
        glfwMakeContextCurrent(windowHandle);
        // Enable v-sync
        glfwSwapInterval(1);

        // Init GL
        GL.createCapabilities();

        // Set a simple orthographic projection where y increases downward
        // This matches GLFW cursor coordinates.
        setOrthoProjection();
    }

    /**
     * Sets the OpenGL projection matrix for 2D rendering.
     */
    private void setOrthoProjection() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        // left, right, bottom, top -> (top=0, bottom=winH) makes Y go down
        glOrtho(0, this.winW, this.winH, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    /**
     * Updates the window title.
     *
     * @param title The new title.
     */
    public void setTitle(String title) {
        this.title = title;
        glfwSetWindowTitle(windowHandle, title);
    }

    /**
     * Resizes the window and updates the OpenGL viewport/projection.
     * Note: This is not hooked up to a callback, but could be.
     */
    public void resize(int newW, int newH) {
        this.winW = newW;
        this.winH = newH;

        glfwSetWindowSize(windowHandle, this.winW, this.winH);
        glViewport(0, 0, this.winW, this.winH);

        // Update projection to match new size
        setOrthoProjection();
    }

    /**
     * Destroys the native window resource.
     */
    public void destroy() {
        if (windowHandle != NULL) {
            glfwDestroyWindow(windowHandle);
        }
    }
}
