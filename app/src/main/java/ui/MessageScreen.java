// File: MessageScreen.java
package ui;

import static org.lwjgl.opengl.GL11.*;

/** Simple ephemeral screen to show a message via MessageRenderer (used for testing input routing) */
public class MessageScreen implements Screen {
    private final MessageRenderer mr;
    private final String text;
    public MessageScreen(String t) { text = t; mr = new MessageRenderer(1024,768); }
    @Override public void init() { mr.init(); mr.addMessage(text); }
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void dispose() {}
    @Override public void update(double dt) {}
    @Override public void render() { mr.renderMessages(); }
}