package dev.lwjgl.ui.components;

import dev.lwjgl.ui.Colors;

public class UIStar  extends UIComponent {

    private float[] color;



    int rotationCounter;
    boolean isRotating;
    public void setRotating(boolean isRotating) {
        this.isRotating = isRotating;
        this.rotationCounter = 0;
    }


    boolean isGrowing;

    public void setGrowing(boolean isGrowing) {
        this.isGrowing = isGrowing;
    }

    int n;
    double xCenter;
    double yCenter;
    double radius;
    double rotationalAngle;


    public UIStar(int n, double xCenter, double yCenter, double radius, double rotationalAngle) { // x,y are centers
//        double side = Math.sqrt(2) * radius;
        this(n, xCenter, yCenter, radius, rotationalAngle, Colors.DARK_RED);
    }

    public UIStar(int n, double xCenter, double yCenter, double radius, double rotationalAngle, float[] color) {
        super(
                xCenter - radius/Math.sqrt(2), yCenter- radius/Math.sqrt(2),
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

    @Override public void render() {
        Colors.setColor(this.color);
        double angleInDegree = this.rotationalAngle;
        if (this.isRotating) {
            angleInDegree += (1.0) * this.rotationCounter;
            this.rotationCounter++;
        }
        if (this.isGrowing) {
            if (this.radius < 3000) this.radius++;
        }
        renderStar(
                this.n,
                this.xCenter,
                this.yCenter,
                this.radius,
                angleInDegree
        );
    }
}
