// File: InputController.java
package ui;

import org.lwjgl.glfw.GLFWKeyCallbackI;
import static org.lwjgl.glfw.GLFW.*;

public class InputController implements GLFWKeyCallbackI {
    private final GameState state;
    private final ViewRouter router;

    public boolean left, right, jump, pause;

    public InputController(GameState s, ViewRouter r) { state = s; router = r; }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            switch (key) {
                case GLFW_KEY_A: left = true; break;
                case GLFW_KEY_D: right = true; break;
                case GLFW_KEY_W: case GLFW_KEY_SPACE: if (state.player.onGround) { state.player.vy = -420; state.player.onGround = false; } break;
                case GLFW_KEY_ESCAPE: router.push(new PauseScreen(state, router)); break;
                case GLFW_KEY_Q: router.push(new MessageScreen("You pressed Q")); break;
                default: break;
            }
        } else if (action == GLFW_RELEASE) {
            switch (key) {
                case GLFW_KEY_A: left = false; break;
                case GLFW_KEY_D: right = false; break;
                default: break;
            }
        }
    }
}