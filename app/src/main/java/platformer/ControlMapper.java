package platformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.glfw.GLFW;

/**
 * Manages key mapping and control randomization.
 */
public class ControlMapper {
    private static final List<Character> keys = new ArrayList<>(Arrays.asList('W', 'A', 'D', 'S', 'Q', 'E'));
    public static char getkey(int whereInList){
        return keys.get(whereInList);
    }
    private Map<String, Integer> keyMap;
    private final boolean keyShuffling;
    private int displayKeyMappingCounter = 0;

    public ControlMapper(boolean keyShuffling) {
        this.keyShuffling = keyShuffling;
        this.keyMap = new HashMap<>();
        initializeControls();
    }
    
    private void initializeControls() {
        randomizeControls();
    }

    public void randomizeControls() {
        if (keyShuffling) {
            Collections.shuffle(keys);
        }
        keyMap = new HashMap<>();
        keyMap.put("UP", getKeyCode(keys.get(0)));
        keyMap.put("LEFT", getKeyCode(keys.get(1)));
        keyMap.put("RIGHT", getKeyCode(keys.get(2)));
        keyMap.put("DOWN", getKeyCode(keys.get(3)));
        keyMap.put("CHARATER SKILL", getKeyCode(keys.get(4)));
        keyMap.put("CHANGE CHARATER SKILL", getKeyCode(keys.get(5)));
        keyMap.put("SUBMIT", GLFW.GLFW_KEY_ENTER);
    }

    private int getKeyCode(char c) {
        return switch (c) {
            case 'Q' -> GLFW.GLFW_KEY_Q;
            case 'A' -> GLFW.GLFW_KEY_A;
            case 'W' -> GLFW.GLFW_KEY_W;
            case 'S' -> GLFW.GLFW_KEY_S;
            case 'E' -> GLFW.GLFW_KEY_E;
            case 'D' -> GLFW.GLFW_KEY_D;
            default -> GLFW.GLFW_KEY_SPACE;
        };
    }

    public Map<String, Integer> getKeyMap() {
        return keyMap;
    }

    public List<Character> getKeys() {
        return keys;
    }

    public boolean isKeyShuffling() {
        return keyShuffling;
    }

    public int getDisplayKeyMappingCounter() {
        return displayKeyMappingCounter;
    }

    public void incrementDisplayKeyMappingCounter() {
        displayKeyMappingCounter++;
    }

    public void resetDisplayKeyMappingCounter() {
        displayKeyMappingCounter = 0;
    }
}

