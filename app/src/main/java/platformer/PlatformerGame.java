package platformer;

import dev.lwjgl.UIWindow;

/**
 * Entry point for the refactored LWJGL Platformer game.
 * Uses the UIWindow framework for window management.
 */
public class PlatformerGame {
    private UIWindow window;
    private PlatformerGameState state;

    public static void main(String[] args) {
        new PlatformerGame().run();
    }

    private void run() {
        try {
            init();
            loop();
        } finally {
            if (window != null) {
                window.destroy();
            }
            if (state != null) {
                state.cleanup();
            }
        }
    }

    private void init() {
        this.window = new UIWindow("Platformer", 800, 600, 20);
        this.state = new PlatformerGameState(window);

        window.setKeyCallback((win, key, scancode, action, mods) -> state.onKey(key, action));
        window.setMouseButtonCallback((win, button, action, mods) -> {
            double[] cursor = new double[2];
            window.getCursorPos(cursor);
            state.onMouse(button, action, cursor[0], cursor[1]);
        });
    }

    private void loop() {
        double lastTime = window.getTime();

        while (!window.shouldClose()) {
            double now = window.getTime();
            double delta = now - lastTime;
            lastTime = now;

            state.update(delta);
            state.render();

            window.swapBuffers();
            window.pollEvents();
        }
    }
}

