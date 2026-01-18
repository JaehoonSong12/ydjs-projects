package dev.lwjgl.ui.components;

import dev.lwjgl.ui.Colors;

import static org.lwjgl.opengl.GL11.*;

public class UIStar extends UIComponent {

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


    // ---------- STAR DATA ----------
    int n;
    public double xCenter;
    public double yCenter;
    double radius;
    double rotationalAngle;

    public UIStar(int n, double xCenter, double yCenter, double radius, double rotationalAngle) {
        this(n, xCenter, yCenter, radius, rotationalAngle, Colors.DARK_RED);
    }

    public UIStar(
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
        this.rotationalAngle = rotationalAngle;
        this.color = color;
    }

    @Override
    public void render() {
        double angleInDegree = this.rotationalAngle;

        if (isRotating) {
            angleInDegree += rotationCounter;
            rotationCounter++;
        }

        if (isGrowing && radius < 3000) {
            radius++;
        }

        if (isGlowing) { // glow variables live in parent
            int layers = 8;
            double glowPercent = 0.4;

            for (int i = layers; i >= 1; i--) {
                float rawPulse = calculateGlowPulse();      // from parent
                float pulse = shapePulse(rawPulse);         // from parent
                float alpha = glowMin + (glowMax - glowMin) * pulse; // from parent

                glColor4f(color[0], color[1], color[2], alpha);
                renderStar(n, xCenter, yCenter,
                        radius + radius * glowPercent * i / layers,
                        angleInDegree);
            }

            float brightness = calculateCoreBrightness(); // from parent
            glColor4f(color[0] * brightness, color[1] * brightness, color[2] * brightness, 1f);
            renderStar(n, xCenter, yCenter, radius, angleInDegree);

            float pulse = calculateGlowPulse();
            updateGlowState(pulse); // from parent
        } else {
            float brightness = calculateCoreBrightness(); // from parent
            glColor4f(color[0] * brightness, color[1] * brightness, color[2] * brightness, 1f);
            renderStar(n, xCenter, yCenter, radius, angleInDegree);
        }
    }
}
