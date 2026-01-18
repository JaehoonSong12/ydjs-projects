// File: PauseScreen.java
package ui;

import static org.lwjgl.opengl.GL11.*;

public class PauseScreen implements Screen {
    private final GameState state; private final ViewRouter router;
    public PauseScreen(GameState s, ViewRouter r) { state = s; router = r; }
    @Override public void init() {}
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    @Override public void update(double dt) {}

    @Override
    public void render() {
        // Draw translucent overlay
        glEnable(GL_BLEND);
        glColor4f(0f, 0f, 0f, 0.5f);
        glBegin(GL_QUADS);
            glVertex2f(0,0); glVertex2f(1024,0); glVertex2f(1024,768); glVertex2f(0,768);
        glEnd();
        glColor3f(1f,1f,1f);
        // Simple pause text
        // In a real UI you'd draw proper text/buttons
    }
}