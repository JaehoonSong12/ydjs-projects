package dev.lwjgl.ui.components;



import java.util.ArrayList;
import java.util.List;

/**
 * Composite container node for UI components.
 */
public class UIContainer extends UIComponent {

    private final List<UIComponent> children = new ArrayList<>();

    public UIContainer(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public void add(UIComponent component) {
        if (component != null) {
            children.add(component);
        }
    }

    public void clear() {
        children.clear();
    }

    @Override
    public void render() {
        if (!visible) return;
        for (UIComponent child : children) {
            child.render();
        }
    }

    @Override
    public void update(double mouseX, double mouseY) {
        if (!visible || !enabled) return;
        for (UIComponent child : children) {
            child.update(mouseX, mouseY);
        }
    }

    @Override
    public boolean handleMouse(double mouseX, double mouseY, int button, int action) {
        if (!visible || !enabled) return false;
        for (int i = children.size() - 1; i >= 0; i--) {
            UIComponent child = children.get(i);
            if (child.handleMouse(mouseX, mouseY, button, action)) {
                return true;
            }
        }
        return false;
    }
}
