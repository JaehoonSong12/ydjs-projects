// File: GameController.java
package ui;

public class GameController {
    private final GameState state;

    public GameController(GameState state) { this.state = state; }

    public void update(double dt, InputController input) {
        // Apply horizontal movement
        float speed = 300f;
        state.player.vx = 0;
        if (input.left) state.player.vx = -speed;
        if (input.right) state.player.vx = speed;

        // Update state
        state.update(dt);

        // TODO: collision detection, triangle collection, enemy hit logic
    }
}