package platformer;

import java.util.List;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 * Player entity with movement, jumping, dashing, and stabbing abilities.
 */
public class Player {

    private final int width = 800, height = 600;
    private final float worldWidth = width * 3f, worldHeight = height;
    private final float speed = 200;
    public static boolean testing = true;
    public int whichcharacter = 2;
    public boolean skipGambling = true;
    
    // Position and velocity
    public static float x, y;
    private float vx, vy;
    private boolean onGround;
    private boolean isfaceingRight = false;
    
    // Dash system
    public static float dashCooldownTime = 0.6f;
    private float dashCurrentTime = dashCooldownTime;
    private boolean showDashEffect = false;
    private float dashEffectTimer = 0f;
    private final float dashEffectDuration = 0.1f;
    private float dashEffectAlpha = 0f;
    private int stopdashTellSpam;
    private boolean uhhIThinkIsAboutTimeWhereDashShouldTell;
    public static boolean showDashCooldownMessage = false;
    
    // jump system
    private float jumpSpeed = 400;
    private final float higherJumpAdditionalSpeed = 300;
    private int jumpChargeCounter;
    private final int maxJumpCount = 300;
    private final int jumpChargeCounterIncremants = 10;
    private boolean enableAdditionalJump;
    private int numberOfJump;
    private final int MAX_ADDITIONAL_JUMP = 3;
    private final float additionalJumpSpeed = 400;
    private boolean showAdditionalJumpOval = false;//currently
    private float jumpOvalTimer = 0f;
    private final float jumpOvalDuration = 0.3f;
    private float jumpOvalX = 0f, jumpOvalY = 0f;
    private float jumpOvalAlpha = 0f;


    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void moveHoriz(int dir, boolean leftOrRight) {
        if (!PlatformerModel.freezeTime) {
            vx = dir * speed;
            isfaceingRight = leftOrRight;
        }
    }

    public void dash() {
        if (dashCurrentTime <= 0.0f && !PlatformerModel.freezeTime) {
            if (dashCooldownTime > 0 && PlatformerModel.dashLevelWasBrought) {
                dashCooldownTime -= ((PlatformerModel.dashLevel / 2) + 1.0f) * 0.01f;
                PlatformerModel.dashLevelWasBrought = false;
            }
            if (dashCooldownTime <= 0) dashCooldownTime = -0.01f;
            
            if (isfaceingRight) {
                x += 200;
            } else {
                x += -200;
            }
            dashCurrentTime = dashCooldownTime;
            dashEffectTimer = dashEffectDuration;
            dashEffectAlpha = dashCooldownTime;
            showDashEffect = true;
            uhhIThinkIsAboutTimeWhereDashShouldTell = false;
        } else if (!PlatformerModel.freezeTime && uhhIThinkIsAboutTimeWhereDashShouldTell) {
            PlatformerModel.showDashCooldownMessage = true;
            uhhIThinkIsAboutTimeWhereDashShouldTell = false;
        }
    }


    public void jCharge() {
        if (!PlatformerModel.freezeTime) {
            enableAdditionalJump = (!onGround && numberOfJump < MAX_ADDITIONAL_JUMP);
            if (onGround && jumpChargeCounter < maxJumpCount) {
                jumpChargeCounter += jumpChargeCounterIncremants;
            }
            if (jumpChargeCounter >= higherJumpAdditionalSpeed) {
                jumpChargeCounter = maxJumpCount;
            }
        }
    }

    public void jump() {
        if (!PlatformerModel.freezeTime) {
            if (jumpChargeCounter > jumpChargeCounterIncremants && onGround) {
                vy = jumpSpeed + jumpChargeCounter;
                jumpChargeCounter = 0;
            }
            if (enableAdditionalJump && !onGround) {
                vy = additionalJumpSpeed;
                this.enableAdditionalJump = false;
                numberOfJump++;
                showAdditionalJumpOval = true;
                jumpOvalTimer = jumpOvalDuration;
                jumpOvalX = x + 10;
                jumpOvalY = y - 5;
                jumpOvalAlpha = 0.5f;
            }
        }
    }

    public void fall() {
        if (!PlatformerModel.freezeTime) {
            y += -20;
        }
    }

    public void applyGravity(float dt) {
        if (!PlatformerModel.freezeTime) {
            vy -= 980 * dt;
        }
    }

    // Stab system
    public boolean isStabbing;
    private final float stabCooldownCap = 1.0f;
    public float stabCurrentCooldown;
    public final float stabDurationCap = 0.1f;
    private float stabCurrentDuration;
    private final float stabRange = 50.0f;


    public static boolean showStabCooldownMessage;
    public void stab() {
        if (this.stabCurrentCooldown <= 0.0f && !PlatformerModel.freezeTime) {
            stabCurrentCooldown = stabCooldownCap;
            stabCurrentDuration = stabDurationCap;
            isStabbing = true;
        }else {
            showStabCooldownMessage = true;
        }
    }
    public void update(float dt, List<Platform> plats) {
        if (!PlatformerModel.freezeTime) {
            if (PlatformerModel.dashLevelWasBrought) {
                jumpSpeed = (jumpSpeed + (PlatformerModel.dashLevel / 2) * 0.1f);
                PlatformerModel.dashLevelWasBrought = false;
            }
            x += vx * dt;
            y += vy * dt;
            stopdashTellSpam += 1;
            onGround = false;
            
            if (stopdashTellSpam >= 10) {
                stopdashTellSpam = 0;
                uhhIThinkIsAboutTimeWhereDashShouldTell = true;
            }
            
            if (x > worldWidth - 20) x = worldWidth - 20;
            if (x < 0) x = 0;
            if (y > worldHeight - 20) y = worldHeight - 20;
            if (y < 20) y = 20;
            
            for (Platform p : plats) {
                if (p.x - 20 < x && x < p.x + p.w && p.y + p.h - 5 < y && y < p.y + p.h + 5) {
                    if (vy <= -1) {
                        y = p.y + p.h;
                        onGround = true;
                        numberOfJump = 0;
                        vy = 0;
                    }
                }
            }
            
            if (dashCurrentTime > 0.0f) {
                dashCurrentTime -= 1.0f * dt;
                if (dashCurrentTime < 0.0f) dashCurrentTime = 0.0f;
            }

            if (stabCurrentDuration > 0.0f) {
                stabCurrentDuration -= 1.0f * dt;
                if (stabCurrentDuration <= 0.0f) {
                    stabCurrentDuration = 0.0f;
                    isStabbing = false;
                }
            }
            if (stabCurrentCooldown > 0.0f) { // countdown
                stabCurrentCooldown -= 1.0f * dt;
                if (stabCurrentCooldown <= 0.0f) {
                    stabCurrentCooldown = 0.0f;
                }
            }
           // if (stabCurrentTime <= 0) isStabbing = false;


            if (showAdditionalJumpOval) {
                jumpOvalTimer += dt;
                if (jumpOvalTimer <= 0.2f) {
                    jumpOvalAlpha = jumpOvalTimer / 0.2f;
                } else if (jumpOvalTimer <= 1.0f) {
                    jumpOvalAlpha = 1f - (jumpOvalTimer - 0.2f) / 0.8f;
                } else {
                    showAdditionalJumpOval = false;
                    jumpOvalAlpha = 0f;
                    jumpOvalTimer = 0f;
                }
            }
            
            if (showDashEffect) {
                dashEffectTimer -= dt;
                if (dashEffectTimer <= 0) {
                    showDashEffect = false;
                    dashEffectTimer = 0;
                    dashEffectAlpha = 0;
                } else {
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
        if (isStabbing) {
            double mx = m.x;
            double my = m.y;
            double ms = m.size;
            double stabX, stabWidth;

            if (isfaceingRight) {
                stabX = x + 20;
                stabWidth = stabRange;
            } else {
                stabX = x - stabRange;
                stabWidth = stabRange;
            }

            double stabY = y;
            double stabHeight = 20;

            return stabX < mx + ms && stabX + stabWidth > mx && stabY < my + ms && stabY + stabHeight > my;
        }
        return false;
    }

    public boolean isStarInDashTriangle(Star s) {
        if (!showDashEffect) return false;
        return Math.abs((isfaceingRight ? x - 200 : x + 200) - s.x) <= 201 && Math.abs((y + 10) - s.y) < 15;
    }
    
    public float getDashCooldown() {
        return dashCurrentTime;
    }

    public void renderJumpOval() {
        if (!showAdditionalJumpOval || jumpOvalAlpha <= 0f) return;
        glPushMatrix();
        glTranslatef(jumpOvalX, jumpOvalY, 0);
        glColor4f(1f, 1f, 1f, jumpOvalAlpha);
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
        glTranslatef(x, y, 0);
        
        if (jumpChargeCounter == 0) {
            glColor4f(0, 0, 1, dashEffectAlpha);
        } else {
            float red = Math.min(1.0f, jumpChargeCounter / 300f);
            glColor4f(red, 0, 1 - red, dashEffectAlpha);
        }
        
        glBegin(GL_TRIANGLES);
        if (isfaceingRight) {
            glVertex2f(0, 0);
            glVertex2f(0, 20);
            glVertex2f(-200, 10);
        } else {
            glVertex2f(0, 0);
            glVertex2f(0, 20);
            glVertex2f(200, 10);
        }
        glEnd();
        glPopMatrix();
    }

    public void renderStabEffect() {
        if (isStabbing) {
            glPushMatrix();
            glTranslatef(x, y, 0);

            glBegin(GL_QUADS);
            glColor4f(0.5f, 0.5f, 0.5f, 0.5f);
            if (isfaceingRight) {
                // Rectangle extending to the right
                glVertex2f(0, 0);
                glVertex2f(0, 20);
                glVertex2f(stabRange + 20, 20);
                glVertex2f(stabRange + 20, 0);
            } else {
                // Rectangle extending to the left
                glVertex2f(0, 0);
                glVertex2f(0, 20);
                glVertex2f(-stabRange, 20);
                glVertex2f(-stabRange, 0);
            }

            glEnd();
            glPopMatrix();
        }
    }
    public void render() {
        renderJumpOval();
        renderDashEffect();
        renderStabEffect();
        if (jumpChargeCounter == 0) {
            glColor3f(0, 0, 1);
        } else {
            float red = Math.min(1.0f, jumpChargeCounter / 300f);
            glColor3f(red, 0, 1 - red);
        }
        
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + 20, y);
        glVertex2f(x + 20, y + 20);
        glVertex2f(x, y + 20);
        glEnd();
    }
}

