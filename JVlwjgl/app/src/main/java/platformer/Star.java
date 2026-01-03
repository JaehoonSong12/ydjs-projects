package platformer;

import static org.lwjgl.opengl.GL11.glColor3f;

import dev.lwjgl.ShapeRenderer;

/**
 * Collectible star entity.
 * Uses framework ShapeRenderer for rendering.
 */
public class Star {
    public float x, y;
    
    public Star(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void render() {
        glColor3f(1, 1, 0);
        ShapeRenderer.renderTriangle(x, y, x + 10, y, x + 5, y + 15);
    }
}

