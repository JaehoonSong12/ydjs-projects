package dev.lwjgl.ui;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;



import java.util.Map;
import java.util.HashMap;


/**
 * A Singleton utility class for rendering the bitmap font.
 * This class encapsulates all the glyph data and drawing logic
 * that was previously inside the UILabel class.
 */
public class BitmapFont {
    
    // --- Singleton Pattern ---
    private static BitmapFont instance;
    public static BitmapFont getInstance() {
        if (instance == null) {
            instance = new BitmapFont();
        }
        return instance;
    }
    
    /**
     * The map of characters to their bitmap (integer array) representations.
     */
    private final Map<Character, int[]> glyphs;

    /**
     * Private constructor for the Singleton.
     * Initializes the glyph map.
     */
    private BitmapFont() {
        glyphs = new HashMap<>();
        glyphs.put(' ', new int[]{0b000,0b000,0b000,0b000,0b000});
        // (Copied from the original UILabel class)
        glyphs.put('A', new int[]{0b010,0b101,0b111,0b101,0b101});
        glyphs.put('B', new int[]{0b110,0b101,0b110,0b101,0b110});
        glyphs.put('C', new int[]{0b01110,0b10001,0b10000,0b10000,0b10000,0b10001,0b01110});
        glyphs.put('D', new int[]{0b110,0b101,0b101,0b101,0b110});
        glyphs.put('E', new int[]{0b111,0b100,0b111,0b100,0b111});
        glyphs.put('F', new int[]{0b111,0b100,0b111,0b100,0b100});
        glyphs.put('G', new int[]{0b1111,0b1000,0b1011,0b1001,0b1111});
        glyphs.put('H', new int[]{0b1001,0b1001,0b1111,0b1001,0b1001});
        glyphs.put('I', new int[]{0b111,0b010,0b010,0b010,0b111});
        glyphs.put('J', new int[]{0b111,0b001,0b001,0b101,0b111});
        glyphs.put('K', new int[]{0b101,0b101,0b110,0b101,0b101});
        glyphs.put('L', new int[]{0b100,0b100,0b100,0b100,0b111});
        glyphs.put('M', new int[]{0b10001,0b11011,0b10101,0b10001,0b10001});
        glyphs.put('N', new int[]{0b10001,0b11001,0b10101,0b10011,0b10001});
        glyphs.put('O', new int[]{0b01110,0b10001,0b10001,0b10001,0b01110});
        glyphs.put('P', new int[]{0b1111,0b1001,0b1111,0b1000,0b1000});
        glyphs.put('Q', new int[]{0b111,0b101,0b111,0b10,0b011});
        glyphs.put('R', new int[]{0b111,0b101,0b111,0b110,0b101});
        glyphs.put('S', new int[]{0b011,0b100,0b010,0b001,0b110});
        glyphs.put('T', new int[]{0b111,0b010,0b010,0b010,0b010});
        glyphs.put('U', new int[]{0b1001,0b1001,0b1001,0b1001,0b0110});
        glyphs.put('V', new int[]{0b10001,0b10001,0b01010,0b01010,0b00100});
        glyphs.put('W', new int[]{0b10001,0b10001,0b10101,0b10101,0b01010});
        glyphs.put('X', new int[]{0b10001,0b01010,0b00100,0b01010,0b10001});
        glyphs.put('Y', new int[]{0b10001,0b01010,0b00100,0b00100,0b00100});
        glyphs.put('Z', new int[]{0b11111,0b00010,0b00100,0b01000,0b11111});
        // digits
        glyphs.put('0', new int[]{0b111,0b101,0b101,0b101,0b111});
        glyphs.put('1', new int[]{0b010,0b110,0b010,0b010,0b111});
        glyphs.put('2', new int[]{0b111,0b001,0b111,0b100,0b111});
        glyphs.put('3', new int[]{0b111,0b001,0b011,0b001,0b111});
        glyphs.put('4', new int[]{0b1001,0b1001,0b1111,0b0001,0b0001});
        glyphs.put('5', new int[]{0b1111,0b1000,0b1111,0b0001,0b1111});
        glyphs.put('6', new int[]{0b11111,0b10000,0b11111,0b10001,0b11111});
        glyphs.put('7', new int[]{0b1111,0b0010,0b0010,0b0100,0b0100});
        glyphs.put('8', new int[]{0b11111,0b10001,0b10001,0b11111,0b10001,0b10001,0b11111});
        glyphs.put('9', new int[]{0b1111,0b1001,0b1111,0b0001,0b1111});
        // punctuations
        glyphs.put(',', new int[]{0b00000,0b00000,0b00000,0b00000,0b00000,0b01000,0b01000,0b10000});
        glyphs.put('=', new int[]{0b00000,0b01110,0b00000,0b01110});
        glyphs.put(':', new int[]{0b000,0b000,0b010,0b000,0b010,0b000,});
        glyphs.put(';', new int[]{0b000,0b000,0b010,0b000,0b010,0b100,});
        glyphs.put('[', new int[]{0b01110,0b01000,0b01000,0b01000,0b01000,0b01110,});
        glyphs.put(']', new int[]{0b01110,0b00010,0b00010,0b00010,0b00010,0b01110,});
        glyphs.put('.', new int[]{0b00000,0b00000,0b00000,0b00000,0b00000,0b00000,0b00110,0b0110});
        glyphs.put('!', new int[]{0b0010,0b0010,0b0010,0b0010,0b00000,0b00000,0b0010,0b00110});
        glyphs.put('?', new int[]{0b01110,0b10001,0b0010,0b0010,0b00000,0b00000,0b00110,0b00110});
    }

    public int[] getPattern(char ch) {
        return glyphs.getOrDefault(ch, glyphs.get(' '));
    }


    /**
     * Calculates the pixel width of a string.
     * (Matches original UILabel.getPixelWidth)
     */
    public double getTextWidth(String text, double scale) {
        return text.length() * 6 * scale;
    }

    /**
     * Calculates the pixel height of the font.
     * (Corrected from original: font is scaled to 7 pixels high)
     */
    public double getTextHeight(double scale) {
        return 7 * scale;
    }

    /**
     * Helper to find the number of columns (bits) in a glyph.
     * (Copied from original UILabel)
     */
    public int getMaxNumCol(int[] sample) {
        int max_num_cols = -1;
        for (int index = 0; index < sample.length; index++) {
            int answer = 0;
            int exponent_of_two = 1;
            while (true) {
                int digitBound = (1 << exponent_of_two) - 1;
                if (sample[index] <= digitBound) {
                    answer = exponent_of_two;
                    break;
                }
                exponent_of_two++;
                if (exponent_of_two > 16) break; // Safety break
            }
            if (max_num_cols < answer) max_num_cols = answer;
        }
        return max_num_cols; // Default to at least 5
    }

    /**
     * Helper to find the number of rows (array length) in a glyph.
     * (Copied from original UILabel)
     */
    public int getMaxNumRow(int[] sample) {
        return sample.length; // Default to at least 7
    }
}
