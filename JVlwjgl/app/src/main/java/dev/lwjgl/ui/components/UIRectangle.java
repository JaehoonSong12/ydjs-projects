package dev.lwjgl.ui.components;

import dev.lwjgl.ui.Colors;
import static org.lwjgl.opengl.GL11.*;

public class UIRectangle extends UIComponent {

    private float[] color;

    // ---------- ORIGINAL SIZE ----------
    private double originalW;
    private double originalH;

    // ---------- SHRINKING ----------
    private boolean isShrinking = false;
    private double shrinkScale = 1.0;

    public void setShrinking(boolean shrinking) {
        this.isShrinking = shrinking;
        if (shrinking) shrinkScale = 1.0;
    }

    // ---------- GLOW ----------
    private boolean isGlowing = false;
    private double glowSpeed = 0.000000002;
    private float glowMin = 0.08f;
    private float glowMax = 0.12f;

    private Integer maxGlowPulses = null; // null = unlimited
    private int glowCounter = 0;

    // Core brightness pulse
    private boolean coreBrightnessPulse = false;
    private double pulseAmount = 0.3;     // how much brighter (0.3 = 30%)
    private double pulseSpeed = 0.000000005;

    public void setGlowing(boolean glowing) {
        this.isGlowing = glowing;
        if (glowing) glowCounter = 0;
    }

    public void setMaxGlowPulses(Integer maxPulses) {
        this.maxGlowPulses = maxPulses;
        glowCounter = 0;
    }

    public void setCoreBrightnessPulse(boolean pulse) {
        this.coreBrightnessPulse = pulse;
    }

    // ---------- CONSTRUCTORS ----------
    public UIRectangle(double x, double y, double w, double h) {
        this(x, y, w, h, Colors.DARK_RED);
    }

    public UIRectangle(double x, double y, double w, double h, float[] color) {
        super(x, y, w, h);
        this.color = color;

        this.originalW = w;
        this.originalH = h;
    }

    @Override
    public void render() {
        double baseW = originalW;
        double baseH = originalH;

        double scale = 1.0;
        if (isShrinking) {
            scale = shrinkScale;
            shrinkScale -= 0.05;
            if (shrinkScale <= 0) return;
        }

        double drawW = baseW * scale;
        double drawH = baseH * scale;

        // Glow rendering
        if (isGlowing) {
            int layers = 8;
            double glowPercent = 0.4;

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE);

            float time = (float) (System.nanoTime() * glowSpeed);
            float pulse = (float) (Math.sin(time) * 0.5 + 0.5);

            double centerX = x + baseW / 2.0;
            double centerY = y + baseH / 2.0;

            for (int i = layers; i >= 1; i--) {
                float t = i / (float) layers;
                float alpha = (glowMin + (glowMax - glowMin) * pulse) * t * t;

                double glowW = baseW * glowPercent * t * scale;
                double glowH = baseH * glowPercent * t * scale;

                glColor4f(color[0], color[1], color[2], alpha);

                renderRect(
                        centerX - (drawW / 2 + glowW),
                        centerY - (drawH / 2 + glowH),
                        drawW + glowW * 2,
                        drawH + glowH * 2
                );
            }

            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            if (pulse > 0.95f) glowCounter++;
            if (maxGlowPulses != null && glowCounter >= maxGlowPulses) isGlowing = false;
        }

        // ---------- CORE RECTANGLE ----------
        double finalW = drawW;
        double finalH = drawH;

        float brightness = 1.0f;
        if (coreBrightnessPulse) {
            float coreTime = (float) (System.nanoTime() * pulseSpeed);
            float pulse = (float) (Math.sin(coreTime) * 0.5 + 0.5); // 0 → 1 → 0
            brightness = 1.0f + (float)(pulseAmount * pulse);
        }

        glColor3f(color[0]* brightness, color[1]* brightness, color[2]* brightness);


        renderRect(
                x - (finalW - drawW) / 2,
                y - (finalH - drawH) / 2,
                finalW,
                finalH
        );
    }
}
