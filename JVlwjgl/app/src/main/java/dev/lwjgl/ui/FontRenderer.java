package dev.lwjgl.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

/**
 * Utility class for rendering text using system fonts (Java2D) to OpenGL textures.
 * Supports custom fonts with fallback to Arial.
 */
public class FontRenderer {
    
    /**
     * Creates a texture from text using the specified font.
     * 
     * @param text The text to render
     * @param fontName The font name (e.g., "Orbitron", "Arial")
     * @param fontSize The font size
     * @param padding Padding around the text
     * @return An array containing [textureId, textWidth, textHeight]
     */
    public static int[] createTextTexture(String text, String fontName, int fontSize, int padding) {
        return createTextTexture(text, fontName, fontSize, padding, null);
    }
    
    /**
     * Creates a texture from text using the specified font with optional transform.
     * 
     * @param text The text to render
     * @param fontName The font name (e.g., "Orbitron", "Arial")
     * @param fontSize The font size
     * @param padding Padding around the text
     * @param transform Optional affine transform (e.g., for scaling/dilation)
     * @return An array containing [textureId, textWidth, textHeight]
     */
    public static int[] createTextTexture(String text, String fontName, int fontSize, int padding, AffineTransform transform) {
        Font font;
        try {
            font = new Font(fontName, Font.BOLD, fontSize);
            if (!font.getFamily().equals(fontName)) {
                font = new Font("Arial", Font.BOLD, fontSize);
            }
        } catch (Exception e) {
            font = new Font("Arial", Font.BOLD, fontSize);
        }
        
        if (transform != null) {
            font = font.deriveFont(transform);
        }

        BufferedImage tmp = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = tmp.createGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int textW = fm.stringWidth(text);
        int textH = fm.getHeight();
        g2d.dispose();

        int imgW = textW + padding * 2;
        int imgH = textH + padding * 2;
        BufferedImage img = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawString(text, padding, fm.getAscent() + padding);
        g2d.dispose();

        int texId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texId);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        int[] pixels = img.getRGB(0, 0, imgW, imgH, null, 0, imgW);
        java.nio.ByteBuffer buffer = BufferUtils.createByteBuffer(imgW * imgH * 4);

        for (int y = 0; y < imgH; y++) {
            for (int x = 0; x < imgW; x++) {
                int p = pixels[y * imgW + x];
                buffer.put((byte) ((p >> 16) & 0xFF)); // Red
                buffer.put((byte) ((p >> 8) & 0xFF));  // Green
                buffer.put((byte) (p & 0xFF));         // Blue
                buffer.put((byte) ((p >> 24) & 0xFF)); // Alpha
            }
        }
        buffer.flip();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, imgW, imgH, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        
        return new int[]{texId, textW, textH};
    }
}

