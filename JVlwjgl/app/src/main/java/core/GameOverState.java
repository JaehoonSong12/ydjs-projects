package core;

import dev.lwjgl.ui.components.UILabel;
import dev.lwjgl.ui.components.controls.UIButton;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.opengl.GL11.*;

/**
 * Concrete State: GameOverState
 * Handles the game over screen.
 */
public class GameOverState extends BaseAppState {

    @Override
    public void onEnter() {
        int score = (context.getSnakeModel() != null) ? context.getSnakeModel().getScore() : 0;
        context.getWindow().setTitle("Game Over! Score: " + score);

        this.view.clear();

        double winW = context.getWindow().getWinW();
        double winH = context.getWindow().getWinH();

        // 1. "GAME OVER" Label
        UILabel gameOverLabel = new UILabel("GAME OVER", 0, winH * 0.25, 4);
        gameOverLabel.centerHorizontal(0, winW);
        this.view.add(gameOverLabel);

        // 2. "FINAL SCORE" Label
        UILabel scoreLabel = new UILabel("FINAL SCORE: " + score, 0, winH * 0.25 + 50, 3);
        scoreLabel.centerHorizontal(0, winW);
        this.view.add(scoreLabel);

        // 3. "Click button" Label
        UILabel restartLabel = new UILabel("Click button to play again", 0, winH * 0.25 + 100, 2);
        restartLabel.centerHorizontal(0, winW);
        this.view.add(restartLabel);

        // 4. "RESTART" Button (looks identical to the main menu button)
        UIButton restartButton = new UIButton(
                "RESTART",
                winW / 2.0 - 100, // x
                winH / 2.0 + 48,  // y (lower down)
                200,              // width
                48,               // height
                () -> {
                    // Action: Return to the Main Menu
                    context.setState(new MainMenuState());
                }
        );
        this.view.add(restartButton);
    }

    @Override
    public void render() {
        // 1. Set background color
        glClearColor(0.08f, 0.08f, 0.08f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        // 2. Render the UI
        super.render();
    }

    @Override
    public void onKey(long window, int key, int scancode, int action, int mods) {
        // Allow ESCAPE to return to menu
        if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
            context.setState(new MainMenuState());
        }
    }
}
