package dev.lwjgl.ui.components;

import dev.lwjgl.ui.*;

/**
 * A UI element for displaying text. (Leaf)
 * This is a "Leaf" in the Composite Design Pattern.
 * It renders text by delegating to the BitmapFont utility.
 */
public class UILabel extends UIComponent {

    

    private String text;
    /**
     * Updates the text of the label and recalculates its
     * width and height.
     */
    public void setText(String text) {
        this.text = text;
        // Update component bounds
        this.w = BitmapFont.getInstance().getTextWidth(this.text, this.scale);          // DEP
        this.h = BitmapFont.getInstance().getTextHeight(this.scale);                    // DEP
    }




    private double scale;
    public UILabel(String text, double x, double y, double scale) {
        // Initial width and height are calculated from the text
        super(x, y, 0, 0, Colors.LIGHT_GRAY);
        this.scale = scale;
        this.setText(text); // Call setText to set text and update bounds
    }

    

    /**
     * Renders the text at its stored x, y position.
     */
    @Override
    public void render() {
        if (!visible || this.text == null || this.text.isEmpty()) return;
        Colors.setColor(this.color);
        // Core logic for rendering
        double[] pointOfDrawing = { this.x, this.y };
        // Loop over each character in the string
        for (char ch : this.text.toUpperCase().toCharArray()) {
            int[] pattern = BitmapFont.getInstance().getPattern(ch);
            // Get the "native" size of the glyph bitmap
            int num_rows = BitmapFont.getInstance().getMaxNumRow(pattern);
            int num_cols = BitmapFont.getInstance().getMaxNumCol(pattern);
            // Calculate scale to normalize glyphs to a 5x7 scaled box
            // (This matches the original's complex logic)
            double yPartitionUnit = (this.scale * 7.0) / num_rows;
            double xPartitionUnit = (this.scale * 5.0) / num_cols;
            
            // Render the glyph "pixel by pixel"
            for (int row = 0; row < num_rows; row++) {
                int bits = pattern[row];
                for (int col = 0; col < num_cols; col++) {
                    if (((bits >> (num_cols - col - 1)) & 1) == 1) {
                        // This "pixel" is on, draw a quad
                        double px = pointOfDrawing[0] + col * xPartitionUnit;
                        double py = pointOfDrawing[1] + row * yPartitionUnit;
                        this.renderRect(px, py, xPartitionUnit, yPartitionUnit);
                    }
                }
            }
            // Advance cursor for the next character
            // (The original used a fixed 6-pixel advance)
            pointOfDrawing[0] += (this.scale * 5.0) * 1.2; 
        }

        
    }


    /**
     * Helper to center this label horizontally within a parent's bounds.
     * @param parentX The parent's starting X.
     * @param parentW The parent's total width.
     */
    public void centerHorizontal(double parentX, double parentW) {
        this.x = parentX + (parentW - this.w) / 2.0;
    }

    /**
     * Helper to center this label vertically within a parent's bounds.
     * @param parentY The parent's starting Y.
     * @param parentH The parent's total height.
     */
    public void centerVertical(double parentY, double parentH) {
        this.y = parentY + (parentH - this.h) / 2.0;
    }
}