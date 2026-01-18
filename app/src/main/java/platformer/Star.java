package platformer;

import dev.lwjgl.ui.components.UIStar;
import dev.lwjgl.ui.Colors;

/**
 * Collectible star entity.
 * Uses UIStar for rendering with glow effects.
 */
public class Star {
    public float x, y;
    private UIStar star;
    public static int starSize = 20;
    public Star(float x, float y) {
        this.x = x;
        this.y = y;
        // Create a 5-pointed star with yellow color and glow effect
        this.star = new UIStar(5, x, y, starSize/2, 90, Colors.YELLOW);
        this.star.setGlowing(true);
        this.star.setRotating(true);
    }
    
    public void render() {
        star.render();
    }
    
    public void updatePosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
        this.star.xCenter = newX;
        this.star.yCenter = newY;
    }
}

