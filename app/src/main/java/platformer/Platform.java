package platformer;

import static org.lwjgl.opengl.GL11.glColor3f;

import dev.lwjgl.ShapeRenderer;

/**
 * Static platform entity.
 * Uses framework ShapeRenderer for rendering.
 */
public class Platform {
    public float x, y, w, h;
    
    public Platform(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    public void render() {
        glColor3f(0.3f, 0.3f, 0.3f);
        ShapeRenderer.renderRect(x, y, w, h);
    }
}

