package platformer;

import static org.lwjgl.opengl.GL11.glColor3f;
import dev.lwjgl.ShapeRenderer;

public class MobA {

    public float x, y;
    public float size = 20f;

    // tuning values
    private static final float MIN_SPEED = 1f;
    private static final float MAX_SPEED = 3f;
    private static final float SPEED_RAMP_DISTANCE = 400f;
    private static final float WIND_STRENGTH = 1f;

    public MobA(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float dt) {
        if (PlatformerModel.freezeTime) return;

        // centers
        float px = Player.x + 10;
        float py = Player.y + 10;
        float mx = x + size / 2f;
        float my = y + size / 2f;

        float dx = px - mx;
        float dy = py - my;

        float distance = (float)Math.sqrt(dx * dx + dy * dy);

        if (distance != 0f) {
            dx /= distance;
            dy /= distance;
        }

        // speed scales with distance (ALWAYS)
        float t = Math.min(distance / SPEED_RAMP_DISTANCE, 1f);
        float speed = MIN_SPEED + (MAX_SPEED - MIN_SPEED) * t;

        // NORMAL CHASE (always active unless invisible)
        if (!Player.isInvisibile) {
            x += dx * speed;
            y += dy * speed;
        }

        // WIND BURST (only knockback inside radius)
        if (Player.windBurst && distance < Player.windBurstSize && distance > 0f) {
            float pushAmount = (Player.windBurstSize + size) - distance;
//            if (pushAmount > Player.windBurstSize) pushAmount = Player.windBurstSize + size;
            x -= dx * pushAmount * WIND_STRENGTH;
            y -= dy * pushAmount * WIND_STRENGTH;
        }

        // world bounds (final authority)
        x = Math.max(0, Math.min(x, PlatformerModel.WORLD_WIDTH - size));
        y = Math.max(20, Math.min(y, PlatformerModel.WORLD_HEIGHT - size));
    }

    public void render() {
        glColor3f(0.4f, 0.0f, 0.4f);
        ShapeRenderer.renderRect(x, y, size, size);
    }
}
