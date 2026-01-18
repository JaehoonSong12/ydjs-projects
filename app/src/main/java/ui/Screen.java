
// File: Screen.java
package ui;

public interface Screen {
    void init();
    void show();
    void hide();
    void update(double dt);
    void render();
    void dispose();
}