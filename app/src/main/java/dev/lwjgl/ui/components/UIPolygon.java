package dev.lwjgl.ui.components;

import dev.lwjgl.ui.Colors;

import static org.lwjgl.opengl.GL11.*;

public class UIPolygon extends UIComponent {

    private float[] color;

    // ---------- ROTATION ----------
    int rotationCounter;
    boolean isRotating;

    public void setRotating(boolean isRotating) {
        this.isRotating = isRotating;
        this.rotationCounter = 0;
    }

    // ---------- GROW ----------
    boolean isGrowing;

    public void setGrowing(boolean isGrowing) {
        this.isGrowing = isGrowing;
    }


    // ---------- POLYGON DATA ----------
    int n;
    public double xCenter;
    public double yCenter;
    double radius;
    double rotationalAngle;

    public UIPolygon(int n, double xCenter, double yCenter, double radius, double rotationalAngle) {
        this(n, xCenter, yCenter, radius, rotationalAngle, Colors.DARK_RED);
    }

    public UIPolygon(
            int n,
            double xCenter,
            double yCenter,
            double radius,
            double rotationalAngle,
            float[] color
    ) {
        super(
                xCenter - radius / Math.sqrt(2),
                yCenter - radius / Math.sqrt(2),
                Math.sqrt(2) * radius,
                Math.sqrt(2) * radius
        );

        this.n = n;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
        this.rotationalAngle = rotationalAngle + 90;
        this.color = color;
    }

    @Override
    public void render() {

        double angleInDegree = this.rotationalAngle;

        if (this.isRotating) {
            angleInDegree += this.rotationCounter;
            this.rotationCounter++;
        }

        if (this.isGrowing) {
            if (this.radius < 3000) this.radius++;
        }

        // ---------- GLOW PASS ----------
        if (isGlowing) {
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE); // additive glow

            int layers = 8;
            double glowPercent = 0.4; // 40% of radius

            float pulse = calculateGlowPulse();

            for (int i = layers; i >= 1; i--) {
                float t = i / (float) layers;
                float alpha = (glowMin + (glowMax - glowMin) * pulse) * t * t;

                glColor4f(
                        color[0],
                        color[1],
                        color[2],
                        alpha
                );

                // Glow radius = original radius + percentage * radius * t
                renderPolygon(
                        this.n,
                        this.xCenter,
                        this.yCenter,
                        this.radius + this.radius * glowPercent * t,
                        angleInDegree
                );
            }

            // ---------- CORE POLYGON ----------
            float brightness = calculateCoreBrightness();

            glColor4f(
                    color[0] * brightness,
                    color[1] * brightness,
                    color[2] * brightness,
                    1.0f
            );

            renderPolygon(
                    this.n,
                    this.xCenter,
                    this.yCenter,
                    this.radius,
                    angleInDegree
            );

            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            updateGlowState(pulse);
        } else {
            //  NO GLOW
            float brightness = calculateCoreBrightness();
            glColor4f(color[0] * brightness, color[1] * brightness, color[2] * brightness, 1.0f);
            renderPolygon(
                    this.n,
                    this.xCenter,
                    this.yCenter,
                    this.radius,
                    angleInDegree
            );
        }
    }
}