package core;

import dev.lwjgl.ui.components.UILabel;
import dev.lwjgl.ui.components.UIRectangle;
import dev.lwjgl.ui.components.controls.UIButton;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Concrete State: MainMenuState
 * Handles the main menu screen.
 */
public class MainMenuState extends BaseAppState {

    @Override
    public void onEnter() {
        // This method is called every time we ENTER this state.
        // We set the title and build the UI from scratch.

        context.getWindow().setTitle("Snake Game - Click to Start");

        // Clear any old UI components
        this.view.clear();

        // Get window dimensions for centering
        double winW = context.getWindow().getWinW();
        double winH = context.getWindow().getWinH();

        // 1. Add Title Label
        UILabel titleLabel = new UILabel("abcdefghijklmnopqrstuvwxyz", 0, winH * 0.25, 4);
        titleLabel.centerHorizontal(0, winW); // Center it
        this.view.add(titleLabel);


        UIRectangle uiRectangle = new UIRectangle(10, 10, 10, 10);
        this.view.add(uiRectangle);


        UILabel titleLabel2 = new UILabel("Snake Game (Playable!)", 0, winH * 0.10, 2);
        titleLabel2.centerHorizontal(0, winW); // Center it
        this.view.add(titleLabel2);


        // 2. Add Start Button
        UIButton startButton = new UIButton(
                "START SNAKE GAME",
                winW / 2.0 - 100, // x
                winH / 2.0 - 24,  // y
                200,              // width
                48,               // height
                () -> {
                    // Action: When clicked, change state to PlayingState
                    System.out.println("Starting Snake Game!");
                    context.setState(new PlayingState());
                }
        );
        this.view.add(startButton);
    }

    @Override
    public void render() {
        // 1. Set background color
        glClearColor(0.08f, 0.08f, 0.08f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        // 2. Render the UI (title and button)
        super.render(); // This calls view.render()
    }

    @Override
    public void onKey(long window, int key, int scancode, int action, int mods) {
        // Override global ESCAPE to just exit the app
        if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
            glfwSetWindowShouldClose(window, true);
        }
    }
}
