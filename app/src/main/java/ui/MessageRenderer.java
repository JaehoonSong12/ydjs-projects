// File: MessageRenderer.java
package ui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class MessageRenderer {
    private final int screenWidth, screenHeight;
    private final Deque<Message> messages = new ArrayDeque<>();
    private static final double DISPLAY_DURATION = 5.0;
    private static final int MAX_MESSAGES = 3;
    private int currentTex;

    public MessageRenderer(int width, int height) { screenWidth = width; screenHeight = height; }

    public void init() { glEnable(GL_BLEND); glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); }

    public void addMessage(String text) {
        double now = glfwGetTime();
        if (messages.size() >= MAX_MESSAGES) { Message old = messages.removeFirst(); glDeleteTextures(old.texId); }
        messages.addLast(new Message(text, now));
    }

    public void renderMessages() {
        if (messages.isEmpty()) return;
        double now = glfwGetTime();
        Iterator<Message> it = messages.iterator();
        while (it.hasNext()) {
            Message m = it.next();
            if (now - m.timestamp > DISPLAY_DURATION) { glDeleteTextures(m.texId); it.remove(); }
        }
        int idx = 0;
        for (Message m : messages) {
            double age = glfwGetTime() - m.timestamp;
            float alpha = (float) Math.max(0.0, 1.0 - (age / DISPLAY_DURATION));
            drawMessage(m.texId, m.textWidth, m.textHeight, idx, alpha);
            idx++;
        }
    }

    private void drawMessage(int texId, int textW, int textH, int index, float alpha) {
        int padding = 12; int extraHeight = 10;
        int boxW = textW + padding * 2; int boxH = textH + padding * 2 + extraHeight;
        int x = (screenWidth - boxW) / 2; int y = (int)(screenHeight * 0.12f) + index * (boxH + 12);
        glDisable(GL_TEXTURE_2D);
        glColor4f(0f,0f,0f,0.75f * alpha);
        glBegin(GL_QUADS);
            glVertex2f(x, y);
            glVertex2f(x+boxW, y);
            glVertex2f(x+boxW, y+boxH);
            glVertex2f(x, y+boxH);
        glEnd();
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, texId);
        glColor4f(1f,1f,1f,alpha);
        glBegin(GL_QUADS);
            glTexCoord2f(0,1); glVertex2f(x+padding, y+padding);
            glTexCoord2f(1,1); glVertex2f(x+padding+textW, y+padding);
            glTexCoord2f(1,0); glVertex2f(x+padding+textW, y+padding+textH);
            glTexCoord2f(0,0); glVertex2f(x+padding, y+padding+textH);
        glEnd();
        glColor4f(1f,1f,1f,1f);
    }

    private class Message {
        final int texId; final int textWidth, textHeight; final double timestamp;
        Message(String text, double timestamp) { this.timestamp = timestamp; int[] dims = createTexture(text, 36, 12); this.textWidth = dims[0]; this.textHeight = dims[1]; this.texId = currentTex; }
    }

    private int[] createTexture(String text, int fontSize, int padding) {
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, fontSize);
        BufferedImage tmp = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = tmp.createGraphics(); g.setFont(font); FontMetrics fm = g.getFontMetrics(); int w = fm.stringWidth(text); int h = fm.getHeight(); g.dispose();
        int imgW = w + padding*2; int imgH = h + padding*2; BufferedImage img = new BufferedImage(imgW,imgH,BufferedImage.TYPE_INT_ARGB);
        g = img.createGraphics(); g.setFont(font); g.setColor(Color.WHITE); g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); g.drawString(text, padding, fm.getAscent()+padding); g.dispose();
        int id = glGenTextures(); glBindTexture(GL_TEXTURE_2D,id); glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR); glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
        int[] pixels = img.getRGB(0,0,imgW,imgH,null,0,imgW);
        ByteBuffer buf = ByteBuffer.allocateDirect(imgW*imgH*4);
        for (int yy=0; yy<imgH; yy++) for (int xx=0; xx<imgW; xx++){ int p = pixels[yy*imgW+xx]; buf.put((byte)((p>>16)&0xFF)); buf.put((byte)((p>>8)&0xFF)); buf.put((byte)(p&0xFF)); buf.put((byte)((p>>24)&0xFF)); }
        buf.flip(); glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA8,imgW,imgH,0,GL_RGBA,GL_UNSIGNED_BYTE,buf); currentTex = id; return new int[]{w,h};
    }
}