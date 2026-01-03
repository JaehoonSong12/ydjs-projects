// File: GameScreen.java
package ui;

import static org.lwjgl.opengl.GL11.*;

public class GameScreen implements Screen {
    private final GameState state;
    private final ViewRouter router;
    private final MessageRenderer messages;

    public GameScreen(GameState state, ViewRouter router, int screenW, int screenH) {
        this.state = state; this.router = router; this.messages = new MessageRenderer(screenW, screenH);
    }

    @Override public void init() { messages.init(); }
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    @Override
    public void update(double dt) {
        // Game logic updates occur through GameController in main loop
    }

    @Override
    public void render() {
        // Draw background
        glClear(GL_COLOR_BUFFER_BIT);

        // Simple player rendering as a rectangle
        glDisable(GL_TEXTURE_2D);
        glColor3f(0f, 0.6f, 0.2f);
        glBegin(GL_QUADS);
            glVertex2f(state.player.x - 20, state.player.y - 40);
            glVertex2f(state.player.x + 20, state.player.y - 40);
            glVertex2f(state.player.x + 20, state.player.y + 40);
            glVertex2f(state.player.x - 20, state.player.y + 40);
        glEnd();

        // HUD/messages
        messages.renderMessages();
    }
}