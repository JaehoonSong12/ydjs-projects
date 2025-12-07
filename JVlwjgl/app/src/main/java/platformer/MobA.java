package platformer;

import static org.lwjgl.opengl.GL11.glColor3f;

import dev.lwjgl.ShapeRenderer;

/**
 * Enemy mob entity that chases the player.
 * Uses framework ShapeRenderer for rendering.
 */
public class MobA {
    public float x, y;
    public float size = 20;
    public float movementdistants = 0.01f;
    
    public MobA(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void update(float dt) {
        if (PlatformerModel.freezeTime) return;
        
        float distanceToPlayer = (float) Math.sqrt(
            (Player.x - x) * (Player.x - x) + (Player.y - y) * (Player.y - y)
        );
        
        int maxSpeed = 3;
        int minSpeed = 1;
        movementdistants = (float) ((maxSpeed - minSpeed) / PlatformerModel.getWorldWidth() * distanceToPlayer + minSpeed);
        
        if (Player.x - x > 0) x += movementdistants;
        if (Player.x - x < 0) x += -movementdistants;
        if (Player.y - y > 0) y += movementdistants;
        if (Player.y - y < 0) y += -movementdistants;
    }
    
    public void render() {
        glColor3f(0.4f, 0.0f, 0.4f);
        ShapeRenderer.renderRect(x, y, size, size);
    }
}

