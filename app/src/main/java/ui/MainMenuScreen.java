// File: MainMenuScreen.java
package ui;

import static org.lwjgl.opengl.GL11.*;

public class MainMenuScreen implements Screen {
    private final GameState state; private final ViewRouter router; private final MessageRenderer messages;
    public MainMenuScreen(GameState s, ViewRouter r, int w, int h) { state = s; router = r; messages = new MessageRenderer(w,h); }
    @Override public void init() { messages.init(); }
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void dispose() {}
    @Override public void update(double dt) {}
    @Override public void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        messages.renderMessages();
    }
}