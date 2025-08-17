





// File: GameApp.java
package ui;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GameApp {
    private long window;
    private final int width = 1024, height = 768;
    private final ViewRouter router = new ViewRouter();
    private final GameState state = new GameState();
    private final GameController gameController = new GameController(state);
    private InputController inputController;

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to init GLFW");
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        window = glfwCreateWindow(width, height, "Pursuit of the Prism", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create window");
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glViewport(0,0,width,height);
        glOrtho(0,width,height,0,-1,1);
        glClearColor(0.5f,0.8f,1.0f,1.0f);

        inputController = new InputController(state, router);
        glfwSetKeyCallback(window, inputController);

        // create screens
        MainMenuScreen main = new MainMenuScreen(state, router, width, height);
        GameScreen game = new GameScreen(state, router, width, height);

        router.push(main);
    }

    private void loop() {
        long last = System.nanoTime();
        while (!glfwWindowShouldClose(window)) {
            long now = System.nanoTime();
            double dt = (now - last) / 1e9;
            last = now;

            gameController.update(dt, inputController);
            router.update(dt);

            // Render
            Screen active = router.getActive();
            if (active != null) active.render();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void cleanup() {
        glfwTerminate();
    }

    public static void main(String[] args) { 
        new GameApp().run(); 
    }
}
