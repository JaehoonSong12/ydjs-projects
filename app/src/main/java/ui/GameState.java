// File: GameState.java
package ui;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    public Player player = new Player();
    public final List<Enemy> enemies = new ArrayList<>();
    public final List<Triangle> triangles = new ArrayList<>();
    public int score = 0;
    public boolean running = true;

    public void reset() {
        player = new Player();
        enemies.clear();
        triangles.clear();
        score = 0;
        running = true;
    }

    public void update(double dt) {
        // Simple placeholder logic: update player only
        player.update(dt);
        // TODO: spawn enemies/triangles; move enemies; collision checks
    }
}