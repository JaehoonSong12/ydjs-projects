// File: LevelSelectScreen.java
package ui;

import static org.lwjgl.opengl.GL11.*;

public class LevelSelectScreen implements Screen {
    private final GameState state; private final ViewRouter router; public LevelSelectScreen(GameState s, ViewRouter r){ state=s; router=r; }
    @Override public void init() {}
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void dispose() {}
    @Override public void update(double dt) {}
    @Override public void render() { glClear(GL_COLOR_BUFFER_BIT); }
}