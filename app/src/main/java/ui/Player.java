// File: Player.java
package ui;

public class Player {
    public float x = 100, y = 300;
    public float vx = 0, vy = 0;
    public boolean onGround = false;

    public void update(double dt) {
        // very simple gravity
        vy += 800f * dt; // gravity
        x += vx * dt;
        y += vy * dt;
        if (y > 500) { y = 500; vy = 0; onGround = true; }
    }
}