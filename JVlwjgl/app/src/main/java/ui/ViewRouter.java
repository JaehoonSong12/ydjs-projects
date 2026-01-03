// File: ViewRouter.java
package ui;

import java.util.ArrayDeque;
import java.util.Deque;

public class ViewRouter {
    private final Deque<Screen> stack = new ArrayDeque<>();

    public void replace(Screen s) {
        while (!stack.isEmpty()) {
            Screen old = stack.pop();
            old.hide();
            old.dispose();
        }
        push(s);
    }

    public void push(Screen s) {
        if (!stack.isEmpty()) stack.peek().hide();
        stack.push(s);
        s.init();
        s.show();
    }

    public void pop() {
        if (stack.isEmpty()) return;
        Screen top = stack.pop();
        top.hide();
        top.dispose();
        if (!stack.isEmpty()) stack.peek().show();
    }

    public Screen getActive() { return stack.peek(); }

    public void update(double dt) { if (!stack.isEmpty()) stack.peek().update(dt); }
    public void render() { if (!stack.isEmpty()) stack.peek().render(); }
}