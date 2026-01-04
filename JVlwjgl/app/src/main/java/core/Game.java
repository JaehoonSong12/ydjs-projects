// src/main/java/com/example/Game.java
package core;
import java.net.URL;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.stb.STBTruetype.*;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.system.MemoryStack;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Iterator;
import java.util.Deque;
import java.util.ArrayDeque;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.File;

import java.util.Scanner;
import static org.lwjgl.opengl.GL11.*;

// Add imports for Orbitron font rendering
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import static org.lwjgl.system.MemoryUtil.*;
import org.lwjgl.BufferUtils;

public class Game {
    // world & camera
    private final static float worldWidth = 2400f, worldHeight = 600f;

    public static int gameTime;
    public static float getWorldWidth() { return worldWidth; }
    private final float mapCenterX = worldWidth / 2.0f, mapCenterY = worldHeight / 2.0f;
    private final float pctWidth = worldWidth*0.8f, pctHeight = worldHeight*0.8f;

    private float[] starRectSpawnArea = {50,20,1,1}; //changeable
    private float[] mobsRectsSpanArea = {mapCenterX,mapCenterY,pctWidth,pctHeight};
    //[0]	Center X position of the rectangle,  [1]	Center Y position of the rectangle,
    //  [2]	Width of the rectangle, [3]	Height of the rectangle
    // normal starRectSpawnArea = {mapCenterX,mapCenterY,pctWidth,pctHeight};
    //or clumped {50,20,1,1};         {mapCenterX,mapCenterY,0,0};
    private Difficulty difficulty = Difficulty.HARD; // Change to EASY, NORMAL, HARD, IMPOSSIBLE, NIGHTMARE.
    private int totalMobsA = 0;
    enum Difficulty {
        EASY(10),
        NORMAL(5),
        HARD(1),
        IMPOSSIBLE(0.5f),
        NIGHTMARE(0.25f);
        public final float mobPerStar;
        Difficulty(float mobPerStar) { this.mobPerStar = mobPerStar;}
    }
    public static boolean freezeTime;

    private long window;
    private final int width = 800, height = 600;

    List<Character> keys = new ArrayList<>(Arrays.asList('W','A','D','S','Q','E'));
    private boolean keyShuffing;
    private int DWSMB = 0; //determines with to show the movement keys
    private void iFNIGHTMARESetUp() {
        if (difficulty == Difficulty.NIGHTMARE) {
            keyShuffing = true;
        } else {
            keyShuffing = false;
        }
        // If you want to do a more difficult EASY, NORMAL, HARD, or IMPOSSIBLE change the 2second keyshuffing to true;
        keyShuffing = keyShuffing;
    }


    private boolean addMobsA = false;
    private boolean MobsAlwayed = true; //changeable
    
    private int spawnMobsPerXStarsCollected = 1;

    private double mobsThatIsLeftover;

    private int pastScore = 0;

    // Texture cache for digits
    private int[] digitTextures = null;



    private Camera camera;
    private Player player;
    private List<Platform> platforms;
    private List<Star> stars;
    private List<MobA> mobs;

    //GAMBLING//
    private boolean dealarPayedThisRound = false;
    private boolean canGamble = true;
    //Dash//
    static public float dashLevel = 1.0f;
    private int dashLevelPrice = 0;
    static public boolean dashLevelWasBrought;
    //Shop//
    private boolean isDelay = true;
    private boolean shopOpened = false;
    private boolean submitKeyPreviouslyDown = false;
    private boolean key1PreviouslyDown = false;
    private boolean key2PreviouslyDown = false;
    public static boolean showDashCooldownMessage = false;
    // UI
    private int lives = 5;
    private int score = 0;
    private boolean shopOpenMovedisabldNoifcation;

    // Message system with Orbitron font
    private final Deque<Message> messages = new ArrayDeque<>();
    private static final int MAX_MESSAGES = 5;
    private static final double DISPLAY_DURATION = 5.5;

    // controls mappings
    private Map<String, Integer> keyMap;
    //private final String[] possibleKeys = {"Q","A","W","S","E","D"};

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!GLFW.glfwInit()) throw new IllegalStateException("Unable to init GLFW");
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        window = GLFW.glfwCreateWindow(width, height, "Platformer", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);
        GL.createCapabilities();
        glClearColor(0.5f, 0.8f, 1f, 0);

        // setup world
        camera = new Camera(width, height, worldWidth);
        randomizeControls();
        player = new Player(100, 50);
        generatePlatforms();
        spawnStars(5);
        mobs = new ArrayList<>();

        // Font system initialized in showMessage

    }

    private void randomizeControls() {
        if (keyShuffing) { Collections.shuffle(keys); }
        keyMap = new HashMap<>();
        keyMap.put("UP",    getKeyCode(keys.get(0)));
        keyMap.put("LEFT",  getKeyCode(keys.get(1)));
        keyMap.put("RIGHT", getKeyCode(keys.get(2)));
        keyMap.put("DOWN",  getKeyCode(keys.get(3)));
        keyMap.put("CHARATER SKILL",  getKeyCode(keys.get(4)));
        keyMap.put("SUBMIT", GLFW.GLFW_KEY_ENTER); // Enter key fixed binding

        if (keyShuffing) {
            showMessage("Key Mapping:");
            showMessage("UP    = " + keys.get(0));
            showMessage("LEFT  = " + keys.get(1));
            showMessage("RIGHT = " + keys.get(2));
            showMessage("DOWN  = " + keys.get(3));
            showMessage("CHARATER SKILL = " + keys.get(4));
            DWSMB = 0;
        }
        showMessage("Press Enter For Shop");
    }

    // Map character to GLFW key code
    private int getKeyCode(char c) {
        switch (c) {
            case 'Q': return GLFW.GLFW_KEY_Q;
            case 'A': return GLFW.GLFW_KEY_A;
            case 'W': return GLFW.GLFW_KEY_W;
            case 'S': return GLFW.GLFW_KEY_S;
            case 'E': return GLFW.GLFW_KEY_E;
            case 'D': return GLFW.GLFW_KEY_D;
            default:  return GLFW.GLFW_KEY_SPACE;
        }
    }

    private void generatePlatforms() {
        platforms = new ArrayList<>();
        // ground
        platforms.add(new Platform(0, 0, worldWidth, 20));
        // floating
        platforms.add(new Platform(400, 90, 200, 20));
        platforms.add(new Platform(800, 140, 150, 20));
        platforms.add(new Platform(1400,  290, 200, 20));
        platforms.add(new Platform(2000, 190, 200, 20));
    }

    private void spawnStars(int count) {
        stars = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            // Use starRectSpawnArea to define spawn boundaries
            //
            // [0] = lowest y, [1] = leftmost x, [2] = highest y, [3] = rightmost x
            // 20 50 20 50
            // private int[] starRectSpawnArea = {mapCenterX,mapCenterY,pctWidth,pctHeight}; // {centerX, centerY, }
            float x = starRectSpawnArea[0] - starRectSpawnArea[2] / 2.0f + rnd.nextFloat() * starRectSpawnArea[2];
            float y = starRectSpawnArea[1] - starRectSpawnArea[3] / 2.0f + rnd.nextFloat() * starRectSpawnArea[3];
            stars.add(new Star(x, y));
        }
    }


    private void spawnMobsA(int count) {
        if (mobs == null) return; // respect your null check system

        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            float x = mobsRectsSpanArea[0] - mobsRectsSpanArea[2] / 2.0f + rnd.nextFloat() * mobsRectsSpanArea[2];
            float y = mobsRectsSpanArea[1] - mobsRectsSpanArea[3] / 2.0f + rnd.nextFloat() * mobsRectsSpanArea[3];
            mobs.add(new MobA(x, y));
        }
        totalMobsA += count;
    }

    private void loop() {
        double last = GLFW.glfwGetTime();
        while (!GLFW.glfwWindowShouldClose(window)) {
            double now = GLFW.glfwGetTime();
            float delta = (float)(now - last);
            last = now;

            update(delta);
            render();
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }


    private void update(float dt) {
        gameTime++;
        boolean submitPressed = GLFW.glfwGetKey(window, keyMap.get("SUBMIT")) == GLFW.GLFW_PRESS;
        boolean key1Pressed = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_1) == GLFW.GLFW_PRESS;
        boolean key2Pressed = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_2) == GLFW.GLFW_PRESS;
        if (keyShuffing /*&& !freezeTime*/) {DWSMB += 1;}
        if (DWSMB >= 500 && keyShuffing /*&& !freezeTime*/){
            showMessage("Key Mapping:");
            showMessage("UP    = " + keys.get(0));
            showMessage("LEFT  = " + keys.get(1));
            showMessage("RIGHT = " + keys.get(2));
            showMessage("DOWN  = " + keys.get(3));
            DWSMB = 0;
        }

        if (GLFW.glfwGetKey(window, keyMap.get("LEFT")) == GLFW.GLFW_PRESS && !freezeTime) player.moveHoriz(-1, false);
        else if (GLFW.glfwGetKey(window, keyMap.get("LEFT")) == GLFW.GLFW_PRESS && !shopOpenMovedisabldNoifcation){
            showMessage("You cant move while shop is open");
            shopOpenMovedisabldNoifcation = true;
        }
        if (GLFW.glfwGetKey(window, keyMap.get("RIGHT")) == GLFW.GLFW_PRESS && !freezeTime) player.moveHoriz(1, true);
        else if (GLFW.glfwGetKey(window, keyMap.get("RIGHT")) == GLFW.GLFW_PRESS && !shopOpenMovedisabldNoifcation){
            showMessage("You cant move while shop is open");
            shopOpenMovedisabldNoifcation = true;
        }
        if (GLFW.glfwGetKey(window, keyMap.get("CHARATER SKILL")) == GLFW.GLFW_PRESS) {
            if (player.whichcharacter == 0) {
                player.dash();
            } else if (player.whichcharacter == 1 && canGamble && !player.skipGambling) {
                canGamble = false;
                gambling();
            } else if (player.whichcharacter == 2) {
                player.stab();
            }
        }
        if (GLFW.glfwGetKey(window, keyMap.get("CHARATER SKILL")) == GLFW.GLFW_RELEASE && player.whichcharacter == 1) {canGamble = true;}
        if (GLFW.glfwGetKey(window, keyMap.get("UP")) == GLFW.GLFW_PRESS) player.jCharge();
        if (GLFW.glfwGetKey(window, keyMap.get("UP")) == GLFW.GLFW_RELEASE) player.jump();
        if (GLFW.glfwGetKey(window, keyMap.get("DOWN")) == GLFW.GLFW_PRESS) {
            player.fall();
            //showMessage(String.format("%f", dashLevel));
        }

        // Toggle shop with Enter
        if (submitPressed && !submitKeyPreviouslyDown) {
            shopOpened = !shopOpened;
            freezeTime = shopOpened;
            if (shopOpened) {
                messages.clear();
                showMessage("You have entered the shop");
                showMessage("The things you can buy:");
                isDelay = true;
                if (player.whichcharacter == 0 && isDelay) {
                    showMessage("Press 1 for the next dash level for " + dashLevelPrice + " score");
                    if (!player.skipGambling) {showMessage("Press 2 to change your skill to gambling");}
                    else {showMessage("Press 2 to change your skill to stabbing");}
                    isDelay = false;
                } else if (player.whichcharacter == 1 && !dealarPayedThisRound && isDelay) {
                    showMessage("Press 1 to pay the dealer this round for 20 score");
                    showMessage("Press 2 to change your skill to dash");
                    isDelay = false;
                }
            } else {
                showMessage("You have left the shop");
                shopOpenMovedisabldNoifcation = false;
            }
        }
        submitKeyPreviouslyDown = submitPressed;

        // Handle in-shop purchases (does NOT close shop)
        if (shopOpened) {
            if (key1Pressed && !key1PreviouslyDown) {
                if (player.whichcharacter == 0 && score >= dashLevelPrice) {
                    score -= dashLevelPrice;
                    if (player.testing) {dashLevel += 10.0f;}
                    else dashLevel += 1.0f;
                    showMessage("Dash level purchased!");
                    dashLevelWasBrought = true;
                    if (!player.testing) {dashLevelPrice = (int)(dashLevel * 5);}
                } else if (player.whichcharacter == 1 && score >= 20 && !dealarPayedThisRound) {
                    score -= 20;
                    dealarPayedThisRound = true;
                    showMessage("Dealer paid, your score is now: " + score);
                } else {
                    showMessage("You're too poor to buy that");
                }
            }else if (key2Pressed && !key2PreviouslyDown){
                if (player.whichcharacter == 0) {
                    if (!player.skipGambling){
                        player.whichcharacter = 1;
                        showMessage("Your charator skil is now gambling");}
                    else {
                        player.whichcharacter = 2;
                        showMessage("Your charator skil is now stabbing");
                    }
                } else if (player.whichcharacter == 1) {
                    player.whichcharacter = 0;
                    showMessage("Your charator skil is now dash");
                }else if (player.whichcharacter == 2) {
                    player.whichcharacter = 0;
                    showMessage("Your charator skil is now dash");
                }
            }
        }
        key1PreviouslyDown = key1Pressed;
        key2PreviouslyDown = key2Pressed;

        player.applyGravity(dt);
        player.update(dt, platforms);
        camera.follow(player);

        // collect stars
        stars.removeIf(s -> {  
            if (player.playerCollidesWithStar(s) || player.isStarInDashTriangle(s)) {
                score++;
                return true;
            }
            return false;
        });
        if (stars.isEmpty()) {
            spawnStars(5);
            randomizeControls();
        }
        if (spawnMobsPerXStarsCollected == score - pastScore){
            addMobsA = true;
        }
        // Trigger spawn only when enough new stars were collected
        if ((score - pastScore) >= spawnMobsPerXStarsCollected) {
            addMobsA = true;
        }

        if (addMobsA && MobsAlwayed) {

            float growth = 1f + (score * 0.05f);
            float base = 1f / difficulty.mobPerStar;

            double mobCount = (score - pastScore) * (growth * base)
                    + mobsThatIsLeftover;

            int wholenumbersMobs = (int) mobCount;
            mobsThatIsLeftover = mobCount - wholenumbersMobs; // store leftover

            spawnMobsA(wholenumbersMobs);

            pastScore = score;
            addMobsA = false;
        }



        if (mobs != null) {
            for (MobA m : mobs) m.update(dt);
            mobs.removeIf(m -> {
                if (player.playerCollidesWithMob(m)) {
                    lives--;
                    totalMobsA--;
                    return true;
                } else if (player.mobCollidesWithStab(m)){
                    totalMobsA--;
                    return true;
                }
                return false;
            });
        }

        // Handle dash cooldown message
        if (showDashCooldownMessage) {
            showMessage("Dash is on cooldown! Wait for " + String.format("%.1f", player.getDashCooldown()) + " seconds.");
            showDashCooldownMessage = false;
        }
    }

    public void showMessage(String text) {
        double now = GLFW.glfwGetTime();
        if (messages.size() > MAX_MESSAGES) {
            Message oldest = messages.removeLast();
            glDeleteTextures(oldest.texId);
        }
        messages.addFirst(new Message(text, now));
    }

    private void renderMessages() {
        if (messages.isEmpty()) return;

        // Save current OpenGL state
        glPushAttrib(GL_ALL_ATTRIB_BITS);


        double now = GLFW.glfwGetTime();

        // Expire old messages
        Iterator<Message> it = messages.iterator();
        while (it.hasNext()) {
            Message m = it.next();
            double age = now - m.timestamp;
            if (age > DISPLAY_DURATION) {
                if (freezeTime) break; // If time is frozen, keep the message visible
                glDeleteTextures(m.texId);
                it.remove();
            }
        }

        // Draw queued messages
        int idx = 0;
        for (Message m : messages) {
            double age = now - m.timestamp;
            float alpha = (float) Math.max(0.0, 1.0 - (age / DISPLAY_DURATION));
            if (freezeTime) alpha = 1.0f; // Full opacity if time is frozen
            drawMessage(m.texId, m.textWidth, m.textHeight, idx, alpha);
            idx++;
        }

        // Restore OpenGL state
        glPopAttrib();
    }

    private void drawMessage(int texId, int textW, int textH, int index, float alpha) {
        int boxW = textW + 20;
        int boxH = textH + 20;
        int x = (width - boxW);
        int y = (int)(height - MAX_MESSAGES * (boxH + 10)) + index * (boxH + 10);


        // Save current OpenGL state
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        glMatrixMode(GL_TEXTURE);
        glPushMatrix();
        glLoadIdentity();
        glMatrixMode(GL_MODELVIEW);

        // Enable blending for alpha transparency
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Black background box with fade
        glDisable(GL_TEXTURE_2D);
        glColor4f(0f, 0f, 0f, 0.75f * alpha);
        glBegin(GL_QUADS);
        glVertex2f(x,     y);
        glVertex2f(x+boxW, y);
        glVertex2f(x+boxW, y+boxH);
        glVertex2f(x,     y+boxH);
        glEnd();

        // Text quad with fade - using correct texture coordinates for proper orientation
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, texId);
        glColor4f(1f, 1f, 1f, alpha);
        glBegin(GL_QUADS);
        glTexCoord2f(0,1); glVertex2f(x+10,     y+10);
        glTexCoord2f(1,1); glVertex2f(x+10+textW, y+10);
        glTexCoord2f(1,0); glVertex2f(x+10+textW, y+10+textH);
        glTexCoord2f(0,0); glVertex2f(x+10,     y+10+textH);
        glEnd();

        // Disable blending after drawing
        glDisable(GL_BLEND);

        // Restore OpenGL state
        glMatrixMode(GL_TEXTURE);
        glPopMatrix();
        glMatrixMode(GL_MODELVIEW);
        glPopAttrib();
    }

    public void gambling() {
        Random rnd = new Random();
        if (!dealarPayedThisRound && !player.testing){
            if (rnd.nextBoolean() && rnd.nextBoolean()) {
                canGamble = false;
                score += score;
                showMessage("You won!");
                showMessage("Your score has been doubled");
                showMessage("Your new score is " + score);
            } else {
                canGamble = false;
                score = 0;
                showMessage("You lost!");
                showMessage("The house always wins");
                showMessage("Better luck next time and also buy the game pass");
            }
        }else if (!player.testing){
            if (rnd.nextBoolean() && rnd.nextBoolean()) {
                canGamble = false;
                dealarPayedThisRound = false;
                score = 0;
                showMessage("You lost!");
                showMessage("The house always wins");
                showMessage("Better luck next time and also buy the game pass");
            } else {
                canGamble = false;
                dealarPayedThisRound = false;
                score += score;
                showMessage("You won!");
                showMessage("Your score has been doubled");
                showMessage("Your new score is " + score);
            }
        }else {
            score += score;
            showMessage("You won!");
            showMessage("Your score has been doubled");
            showMessage("Your new score is " + score);
        }
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        camera.begin();

        // draw platforms
        for (Platform p : platforms) p.render();
        // draw stars
        for (Star s : stars) s.render();
        // draw player
        player.render();
        // draw mobs
        if (mobs != null) { for (MobA m : mobs) m.render();}

        camera.end();

        // UI overlay
        renderUI();
    }

    private void renderUI() {
        // Simple UI: draw lives as red squares, score as quads count
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, width, 0, height, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        // Lives
        glColor3f(1,0,0);
        for (int i = 0; i < lives; i++) {
            glBegin(GL_QUADS);
            glVertex2f(10 + i*25, height - 10);
            glVertex2f(25 + i*25, height - 10);
            glVertex2f(25 + i*25, height - 25);
            glVertex2f(10 + i*25, height - 25);
            glEnd();
        }
        // Score indicator using digit textures
        if (digitTextures == null) {
            digitTextures = new int[10];
            for (int i = 0; i < 10; i++) {
                Path path = Paths.get("anderson", "number_" + i + ".png");
                digitTextures[i] = loadTexture(path);
            }
        }

        String scoreStr = Integer.toString(score);
        float sx = width / 2f - (scoreStr.length() * 20) / 2f;
        float sy = height - 30;
        float dx = 0;

        // Draw background for score
        glColor3f(0.5f, 0.8f, 1f);
        glBegin(GL_QUADS);
        glVertex2f(sx - 10, sy - 5);
        glVertex2f(sx + scoreStr.length() * 20 + 10, sy - 5);
        glVertex2f(sx + scoreStr.length() * 20 + 10, sy + 25);
        glVertex2f(sx - 10, sy - 5);
        glEnd();

        // Save current OpenGL state
        glPushAttrib(GL_ALL_ATTRIB_BITS);

        // Enable blending for transparency
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_TEXTURE_2D);

        for (char c : scoreStr.toCharArray()) {
            int digit = c - '0';
            if (digit < 0 || digit > 9) continue;
            glBindTexture(GL_TEXTURE_2D, digitTextures[digit]);
            glColor4f(1, 1, 1, 1); // Use alpha channel
            glBegin(GL_QUADS);
            glTexCoord2f(0, 0); glVertex2f(sx + dx, sy);
            glTexCoord2f(1, 0); glVertex2f(sx + dx + 20, sy);
            glTexCoord2f(1, 1); glVertex2f(sx + dx + 20, sy + 20);
            glTexCoord2f(0, 1); glVertex2f(sx + dx, sy + 20);
            glEnd();
            dx += 20;
        }
        glBindTexture(GL_TEXTURE_2D, 0);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);

        // Restore OpenGL state
        glPopAttrib();

        // Render messages
        renderMessages();

        // glColor3f(1,1,0);
        // for (int i = 0; i < score; i++) {
        //     glBegin(GL_QUADS);
        //     glVertex2f(width/2 - 10 + i*15, height - 10);
        //     glVertex2f(width/2 + 10 + i*15, height - 10);
        //     glVertex2f(width/2 + 10 + i*15, height - 25);
        //     glVertex2f(width/2 - 10 + i*15, height - 25);
        //     glEnd();
        // }
        // Controls under player
        // Could draw small quads below player, omitted for brevity
    }



    // Texture loading utility
    private int loadTexture(Path resourcePath) {
        int textureID;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            // Load image from resource - convert Path to proper resource path
            String resourcePathStr = "/" + resourcePath.toString().replace('\\', '/');
            InputStream in = getClass().getResourceAsStream(resourcePathStr);
            if (in == null) throw new RuntimeException("Image not found: " + resourcePathStr);

            // Copy resource to a temp file
            Path tempFile = Files.createTempFile("texture", ".png");
            Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);

            // Flip image vertically on load
            stbi_set_flip_vertically_on_load(true);

            // Load image data with alpha channel
            ByteBuffer image = stbi_load(tempFile.toString(), width, height, channels, 4);
            if (image == null) throw new RuntimeException("Failed to load texture: " + stbi_failure_reason());

            // Generate OpenGL texture
            textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureID);

            // Enable alpha channel
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);

            // Set texture parameters
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            stbi_image_free(image);

            // Clean up temp file
            Files.deleteIfExists(tempFile);
        } catch (Exception e) {
            throw new RuntimeException("Error loading texture: " + resourcePath, e);
        }

        return textureID;
    }

    private class Message {
        final int texId;
        final int textWidth, textHeight;
        final double timestamp;

        Message(String text, double timestamp) {
            this.timestamp = timestamp;
            int[] dims = createOrbitronTexture(text, 10);
            this.textWidth = dims[0];
            this.textHeight = dims[1];
            this.texId = currentTex;
        }
    }

    private int currentTex;

    private int[] createOrbitronTexture(String text, int padding) {
        // Use a more readable font size and fallback to system font if Orbitron not available
        Font font;
        try {
            font = new Font("Orbitron", Font.BOLD, 24);
            if (!font.getFamily().equals("Orbitron")) font = new Font("Arial", Font.BOLD, 24);
        } catch (Exception e) {
            font = new Font("Arial", Font.BOLD, 24);
        }
        AffineTransform dilation = AffineTransform.getScaleInstance(0.6, 1.2); // *dilation in math
        font = font.deriveFont(dilation);

        BufferedImage tmp = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
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

        int[] pixels = img.getRGB(0,0,imgW,imgH,null,0,imgW);
        ByteBuffer buffer = BufferUtils.createByteBuffer(imgW * imgH * 4);

        // Copy pixels to buffer in correct order
        for (int y = 0; y < imgH; y++) {
            for (int x = 0; x < imgW; x++) {
                int p = pixels[y * imgW + x];
                buffer.put((byte)((p >> 16) & 0xFF)); // Red
                buffer.put((byte)((p >> 8) & 0xFF));  // Green
                buffer.put((byte)(p & 0xFF));         // Blue
                buffer.put((byte)((p >> 24) & 0xFF)); // Alpha
            }
        }
        buffer.flip();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, imgW, imgH, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        currentTex = texId;
        return new int[]{textW, textH};
    }

    private void cleanup() {
        // Clean up message textures
        for (Message m : messages) {
            glDeleteTextures(m.texId);
        }
        messages.clear();

        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    public static void main(String[] args) {
        new Game().run();
    }
}
// Camera follows the player
class Camera {
    private final int screenW, screenH;
    private final float worldW;
    private float x;
    public Camera(int w, int h, float worldW) { screenW=w; screenH=h; this.worldW=worldW; }
    public void follow(Player p) {
        x = p.x - screenW/2f;
        if (x<0) x=0;
        if (x>worldW-screenW) x=worldW-screenW;
    }
    public void begin() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(x, x+screenW, 0, screenH, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }
    public void end() { /* nothing */ }
}

// Simple player with gravity and collision
class Player {
    private final int width = 800, height = 600;
    private final float worldWidth = width * 3f, worldHeight = height;
    private float speed = 200, jumpSpeed = 400, higherJumpAdditionalSpeed = 300, additionalJumpSpeed = 400;
    private final int MAX_ADDITIONAL_JUMP = 3; //changed  /2 //changeable
    private final int MAX_COUNTER = 300; //changeable
    private final int _COUNTER_INCREMENT = 10; //changeable
    static public boolean testing = true; //changeable
    public int whichcharacter = 0; //changeable
    public boolean skipGambling = true; //changeable
    //Dash//
    public static float x, y;
    private float vx, vy;
    private boolean onGround;
    private int counter;
    private boolean enableAdditionalJump;
    private boolean isfaceingRight = false;
    static public float dashCooldownTime = 0.6f; // changed  //1.0f //changeable
    private float dashCooldown = dashCooldownTime;
    private int numberOfJump;

    // Dash effect variables
    private boolean showDashEffect = false;
    private float dashEffectTimer = 0f;
    private final float dashEffectDuration = 0.1f; // Much faster fade out // 0.2f
    private float dashEffectAlpha = 0f;
    private float dashStartX = 0f; // Track where dash started
    private int stopdashTellSpam;
    private boolean uhhIThinkIsAboutTimeWhereDashShouldTell;

    // For additional jump oval rendering
    private boolean showAdditionalJumpOval = false;
    private float jumpOvalTimer = 0f;
    private final float jumpOvalDuration = 0.3f;
    private float jumpOvalX = 0f, jumpOvalY = 0f;
    private float jumpOvalAlpha = 0f;

    //Stab//
    public boolean didstab;
    private float stabCooldownTime = 0.0f;
    public float stabCooldown = stabCooldownTime; //changeable
    private float stabRange = 50.0f; //changeable



    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void moveHoriz(int dir,boolean leftOrRight ) {
        if(!Game.freezeTime){
            vx = dir * speed;
            isfaceingRight = leftOrRight;
        }
    }

    public void dash(){
        if (dashCooldown <= 0.0f && !Game.freezeTime){
            dashStartX = x; // Record starting position
            if (dashCooldownTime > 0 && Game.dashLevelWasBrought){
                dashCooldownTime -= ((Game.dashLevel/2) + 1.0f)*0.01f;
                Game.dashLevelWasBrought = false;
            }
            if (dashCooldownTime <= 0){dashCooldownTime =- 0.01f;}
            if (isfaceingRight == true){
                x += 200;
                dashCooldown = dashCooldownTime;
                dashEffectTimer = dashEffectDuration;
                dashEffectAlpha = dashCooldownTime;
                showDashEffect = true;
                uhhIThinkIsAboutTimeWhereDashShouldTell = false;
            }
            if (isfaceingRight == false){
                x += -200;
                dashCooldown = dashCooldownTime;
                dashEffectTimer = dashEffectDuration;
                dashEffectAlpha = dashCooldownTime;
                showDashEffect = true;
                uhhIThinkIsAboutTimeWhereDashShouldTell = false;
            }
        } else if(!Game.freezeTime && uhhIThinkIsAboutTimeWhereDashShouldTell){
            // Signal to Game that we want to show a dash cooldown message
            Game.showDashCooldownMessage = true;
            uhhIThinkIsAboutTimeWhereDashShouldTell = false;
        }
    }

    public void stab() {
        if (stabCooldown <= 0.0f && !Game.freezeTime){
            stabCooldown = stabCooldownTime - 2.0f;
            didstab = true;
        }
    }

    public void jCharge() {
        if (!Game.freezeTime) {
            enableAdditionalJump = (!onGround && numberOfJump < MAX_ADDITIONAL_JUMP);

            if (onGround && counter < MAX_COUNTER) {
                counter += _COUNTER_INCREMENT;
            }
            if (counter >= higherJumpAdditionalSpeed) {

                counter = MAX_COUNTER;
            }
        }
    }

    public void jump() {
        if (!Game.freezeTime) {
            if (counter > _COUNTER_INCREMENT && onGround) {
                vy = jumpSpeed + counter;
                counter = 0;
            }
            if (enableAdditionalJump && !onGround) {
                vy = additionalJumpSpeed;
                this.enableAdditionalJump = false;
                numberOfJump++;

                // Show oval only when additional jump occurs
                showAdditionalJumpOval = true;
                jumpOvalTimer = jumpOvalDuration;
                jumpOvalX = x + 10;
                jumpOvalY = y - 5;
                jumpOvalAlpha = 0.5f;
            }
        }
    }

    public void fall() {
        if(!Game.freezeTime){
            y += - 20;
        }
    }

    public void applyGravity(float dt) {
        if(!Game.freezeTime){
            vy -= 980 * dt;
        }
    }

    public void update(float dt, List<Platform> plats) {
        if(!Game.freezeTime){
            if(Game.dashLevelWasBrought){
                jumpSpeed = (jumpSpeed + (Game.dashLevel/2)*0.1f);
                Game.dashLevelWasBrought = false;
            }
            x += vx * dt;
            y += vy * dt;
            stopdashTellSpam += 1;
            onGround = false;
            if (stopdashTellSpam >= 10){
                stopdashTellSpam = 0;
                uhhIThinkIsAboutTimeWhereDashShouldTell = true;
            }
            if (x > worldWidth - 20) x = worldWidth - 20;
            if (x < 0) x = 0;
            if (y > worldHeight - 20) y = worldHeight - 20;
            if (y < 20) y = 20;
            for (Platform p : plats) {
                if (p.x - 20 < x && x < p.x + p.w
                        &&
                        p.y + p.h - 5 < y && y < p.y + p.h + 5) { // on the platform
                    if(vy <= -1) { // only when falling (comming from top)
                        y = p.y + p.h;
                        onGround = true;
                        numberOfJump = 0;
                        vy = 0;
                    }
                }
            }
            // In Player class or wherever you manage dashCooldown
            if (dashCooldown > 0.0f) {
                dashCooldown -= 1.0f * dt;
                if (dashCooldown < 0.0f){
                    dashCooldown = 0.0f;
                }
            }
            if (stabCooldown > 0.0f) {
                stabCooldown -= 1.0f * dt;
                if (stabCooldown < 0.0f){
                    stabCooldown = 0.0f;
                }
            }
            if (stabCooldown <= stabCooldownTime / (9/10)) {didstab = false;}

            // Countdown timer for additional jump oval
            if (showAdditionalJumpOval) {
                jumpOvalTimer += dt;

                // Fade in for first 0.2s, then fade out for next 0.8s
                if (jumpOvalTimer <= 0.2f) {
                    jumpOvalAlpha = jumpOvalTimer / 0.2f; // 0 to 1
                } else if (jumpOvalTimer <= 1.0f) {
                    jumpOvalAlpha = 1f - (jumpOvalTimer - 0.2f) / 0.8f; // 1 to 0
                } else {
                    showAdditionalJumpOval = false;
                    jumpOvalAlpha = 0f;
                    jumpOvalTimer = 0f;
                }
            }

            // Update dash effect
            if (showDashEffect) {
                dashEffectTimer -= dt;
                if (dashEffectTimer <= 0) {
                    showDashEffect = false;
                    dashEffectTimer = 0;
                    dashEffectAlpha = 0;
                } else {
                    // Fade out effect
                    dashEffectAlpha = dashEffectTimer / dashEffectDuration;
                }
            }

            if (y <= 20) {
                y = 20;
                onGround = true;
                vy = 0;
            }

            vx = 0;
        }
    }

    public boolean playerCollidesWithStar(Star s) {
        return Math.hypot((x + 10) - s.x, (y + 10) - s.y) < 15;
    }

    public boolean playerCollidesWithMob(MobA m) {
        return x < m.x + m.size && x + 20 > m.x && y < m.y + m.size && y + 20 > m.y;
    }
    
    public boolean mobCollidesWithStab(MobA m) {
        if(!didstab) {return false;}
        // mob rectangle
        double mx = m.x;
        double my = m.y;
        double ms = m.size;

        double stabX, stabWidth;
        if (isfaceingRight) {
            // Stab to the RIGHT
            stabX = x + 20;            // start at player's right side
            stabWidth = stabRange;     // length of stab
        } else {
            // Stab to the LEFT
            stabX = x - stabRange;     // start left of player
            stabWidth = stabRange;     // length of stab
        }


        // Stab rectangle vertical range matches player height
        double stabY = y;
        double stabHeight = 20;

        // Rectangle collision
        return stabX < mx + ms &&
            stabX + stabWidth > mx &&
            stabY < my + ms &&
            stabY + stabHeight > my;
    }

    public boolean isStarInDashTriangle(Star s) {// s.x = 10 && x = 50 --> x = -150 && s.x = 10 --> -10 (-150 - 200) = 340 == not within 50
        if (!showDashEffect) return false;

        return Math.abs((isfaceingRight ? x - 200 : x + 200) - s.x) <= 201 && Math.abs((y + 10) - s.y) < 15;
    }
    public void renderJumpOval() {
        if (!showAdditionalJumpOval || jumpOvalAlpha <= 0f) return;

        glPushMatrix();
        glTranslatef(jumpOvalX, jumpOvalY, 0);

        glColor4f(1f, 1f, 1f, jumpOvalAlpha); // fading opacity

        int segments = 40;
        float radiusX = 15f;
        float radiusY = 7f;

        glBegin(GL_TRIANGLE_FAN);
        glVertex2f(0, 0);
        for (int i = 0; i <= segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            float dx = (float) Math.cos(angle) * radiusX;
            float dy = (float) Math.sin(angle) * radiusY;
            glVertex2f(dx, dy);
        }
        glEnd();

        glPopMatrix();
    }


    public void renderDashEffect() {
        if (!showDashEffect || dashEffectAlpha <= 0f) return;

        glPushMatrix();
        glTranslatef(x, y, 0); // Position at current player position

        // Use same color as player
        if (counter == 0) {
            glColor4f(0, 0, 1, dashEffectAlpha); // Blue when on ground
        } else {
            float red = Math.min(1.0f, counter / 300f); // Scale red based on charge
            glColor4f(red, 0, 1 - red, dashEffectAlpha); // Transition from blue to red
        }

        glBegin(GL_TRIANGLES);
        if (isfaceingRight) {
            // Triangle pointing left (base on right side of player)
            glVertex2f(0, 0); // Base point
            glVertex2f(0, 20); // Base point
            glVertex2f(-200, 10); // Point back to start
        } else {
            // Triangle pointing right (base on left side of player)
            glVertex2f(0, 0); // Base point
            glVertex2f(0, 20); // Base point
            glVertex2f(200, 10); // Point back to start
        }
        glEnd();

        glPopMatrix();
    }

    public float getDashCooldown() {
        return dashCooldown;
    }

    public void render() {
        renderJumpOval(); // draw oval if needed
        renderDashEffect(); // draw dash effect if needed

        if (counter == 0) {
            glColor3f(0, 0, 1); // Blue when on ground
        } else {
            float red = Math.min(1.0f, counter / 300f); // Scale red based on charge
            glColor3f(red, 0, 1 - red); // Transition from blue to red
        }

        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + 20, y);
        glVertex2f(x + 20, y + 20);
        glVertex2f(x, y + 20);
        glEnd();
    }
}



// Static platforms
class Platform {
    public float x, y, w, h;
    public Platform(float x, float y, float w, float h) { this.x=x; this.y=y; this.w=w; this.h=h; }
    public void render() {
        glColor3f(0.3f,0.3f,0.3f);
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x+w, y);
        glVertex2f(x+w, y+h);
        glVertex2f(x, y+h);
        glEnd();
    }
}

// Collectible stars
class Star {
    public float x, y;
    public Star(float x, float y) { this.x=x; this.y=y; }
    public void render() {
        glColor3f(1,1,0);
        glBegin(GL_TRIANGLES);
        glVertex2f(x, y);
        glVertex2f(x+10, y);
        glVertex2f(x+5, y+15);
        glEnd();
    }
}

// Simple mob stub
class MobA {
    public float x, y;
    public float size=20;
    public float movementdistants = 0.01f;
    public MobA(float x, float y) { this.x=x; this.y=y; }
    public void update(float dt) {
        render();
        if (Game.freezeTime) return;
        float distanceToPlayer = (float) Math.sqrt((Player.x - x)*(Player.x - x) + (Player.y - y)*(Player.y - y));
        movementdistants = (float) (3 * Math.pow(0.998, distanceToPlayer) + 1);
        int maxSpeed = 3;
        int minSpeed = 1;
        movementdistants = (float) ((maxSpeed - minSpeed) / Game.getWorldWidth() * distanceToPlayer + minSpeed);
        if (Player.x - x > 0) {x += movementdistants;}
        if (Player.x - x < 0) {x += -movementdistants;}
        if (Player.y - y > 0) {y += movementdistants;}
        if (Player.y - y < 0) {y += -movementdistants;}
    }

    public void render() {
        glColor3f(0.4f, 0.0f, 0.4f); // dark purple
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x+size, y);
        glVertex2f(x+size, y+size);
        glVertex2f(x, y+size);
        glEnd();
    }
}