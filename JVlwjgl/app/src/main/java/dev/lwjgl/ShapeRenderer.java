package dev.lwjgl;

import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

/**
 * Utility class for rendering basic shapes.
 * Can be used by both UI components and game entities.
 */
public class ShapeRenderer {
    
    /**
     * Renders a rectangle (quad) using float coordinates.
     */
    public static void renderRect(float x, float y, float w, float h) {
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + w, y);
        glVertex2f(x + w, y + h);
        glVertex2f(x, y + h);
        glEnd();
    }
    
    /**
     * Renders a rectangle (quad) using double coordinates.
     */
    public static void renderRect(double x, double y, double w, double h) {
        glBegin(GL_QUADS);
        glVertex2d(x, y);
        glVertex2d(x + w, y);
        glVertex2d(x + w, y + h);
        glVertex2d(x, y + h);
        glEnd();
    }
    
    /**
     * Renders a rectangle (quad) using int coordinates.
     */
    public static void renderRect(int x, int y, int w, int h) {
        glBegin(GL_QUADS);
        glVertex2i(x, y);
        glVertex2i(x + w, y);
        glVertex2i(x + w, y + h);
        glVertex2i(x, y + h);
        glEnd();
    }
    
    /**
     * Renders a triangle using float coordinates.
     * @param x1, y1 First vertex
     * @param x2, y2 Second vertex
     * @param x3, y3 Third vertex
     */
    public static void renderTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        glBegin(GL_TRIANGLES);
        glVertex2f(x1, y1);
        glVertex2f(x2, y2);
        glVertex2f(x3, y3);
        glEnd();
    }
    
    /**
     * Renders a triangle using double coordinates.
     */
    public static void renderTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        glBegin(GL_TRIANGLES);
        glVertex2d(x1, y1);
        glVertex2d(x2, y2);
        glVertex2d(x3, y3);
        glEnd();
    }
    
    /**
     * Renders a polygon with n sides.
     */
    public static void renderPolygon(int n, double x, double y, double radius, double rotationalAngle) {
        glBegin(GL_POLYGON);
        double rot = Math.toRadians(rotationalAngle);
        for (int i = 0; i < n; i++) {
            double angle = rot + (2 * Math.PI * i / n);
            double vx = x + radius * Math.cos(angle);
            double vy = y + radius * Math.sin(angle);
            glVertex2d(vx, vy);
        }
        glEnd();
    }
    
    /**
     * Renders a star shape with n points.
     */
    public static void renderStar(int n, double x, double y, double radius, double rotationalAngle) {
        double rot = Math.toRadians(rotationalAngle);
        double innerRadius = radius * 0.4;
        
        glBegin(GL_TRIANGLES);
        
        for (int i = 0; i < n; i++) {
            double tipAngle = rot + (2 * Math.PI * i / n);
            double tipX = x + radius * Math.cos(tipAngle);
            double tipY = y + radius * Math.sin(tipAngle);
            
            double valleyAngle1 = rot + (2 * Math.PI * (i - 0.5) / n);
            double valleyX1 = x + innerRadius * Math.cos(valleyAngle1);
            double valleyY1 = y + innerRadius * Math.sin(valleyAngle1);
            
            double valleyAngle2 = rot + (2 * Math.PI * (i + 0.5) / n);
            double valleyX2 = x + innerRadius * Math.cos(valleyAngle2);
            double valleyY2 = y + innerRadius * Math.sin(valleyAngle2);
            
            glVertex2d(x, y);
            glVertex2d(valleyX1, valleyY1);
            glVertex2d(tipX, tipY);
            
            glVertex2d(x, y);
            glVertex2d(tipX, tipY);
            glVertex2d(valleyX2, valleyY2);
        }
        
        glEnd();
    }
}

