package platformer;

import dev.lwjgl.ui.components.UIPolygon;

import java.nio.channels.ClosedSelectorException;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static platformer.PlatformerModel.*;

/**
 * Player entity with movement, jumping, dashing, and stabbing abilities.
 */
public class Player {
    private final int width = 800, height = 600;
    private final float worldWidth = width * 3f, worldHeight = height;
    private final float speed = 200;
    private float jumpSpeed = 400;
    private final float higherJumpAdditionalSpeed = 300;
    private final float additionalJumpSpeed = 400;
    private final int MAX_ADDITIONAL_JUMP = 3;
    private final int MAX_COUNTER = 300;
    private final int _COUNTER_INCREMENT = 10;
    
    public static boolean testing = true;
    public int whichcharacter = 0;
    public boolean skipGambling = true;
    private float invisibileFramDurration = 0.1f;
    private float invisibileFramCurrentTime;
    public static boolean isInvisibile;

    
    // Position and velocity
    public static float x, y;
    private float vx, vy;
    private boolean onGround;
    private int counter;
    private boolean enableAdditionalJump;
    private boolean isfaceingRight = false;
    private boolean fall;
    private float shouldFall;
    
    // Dash system
    public static float dashCooldownTime = 0.6f;
    private float dashCooldown = dashCooldownTime;
    private boolean showDashEffect = false;
    private float dashEffectTimer = 0f;
    private final float dashEffectDuration = 0.1f;
    private float dashEffectAlpha = 0f;
    public static boolean showDashCooldownMessage = false;
    
    // Additional jump oval rendering
    private boolean showAdditionalJumpOval = false;
    private float jumpOvalTimer = 0f;
    private final float jumpOvalDuration = 0.3f;
    private float jumpOvalX = 0f, jumpOvalY = 0f;
    private float jumpOvalAlpha = 0f;
    private int numberOfJump;
    
    // Stab system
    public boolean didstab;
    private final float stabCooldownTime = 0.0f;
    public float stabCooldown = stabCooldownTime;
    private final float stabRange = 50.0f;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void moveHoriz(int dir, boolean leftOrRight) {
        if (!PlatformerModel.freezeTime && !shouldntMove) {
            vx = dir * speed;
            isfaceingRight = leftOrRight;
        }
    }
    public void fall() {
        if (!PlatformerModel.freezeTime) {
            shouldFall = 0.1f;
        }
    }

    public void dash() {
        if (dashCooldown <= 0.0f && !PlatformerModel.freezeTime) {
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
            dashCooldown = dashCooldownTime;
            dashEffectTimer = dashEffectDuration;
            dashEffectAlpha = dashCooldownTime;
            showDashEffect = true;
            invisibileFramCurrentTime = invisibileFramDurration;
        } else if (!PlatformerModel.freezeTime) {
            showDashCooldownMessage = true;
        }
    }

    public void stab() {
        if (stabCooldown <= 0.0f && !PlatformerModel.freezeTime) {
            stabCooldown = stabCooldownTime - 2.0f;
            didstab = true;
        }
    }

    public void jCharge() {
        if (!PlatformerModel.freezeTime) {
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
        if (!PlatformerModel.freezeTime) {
            if (counter > _COUNTER_INCREMENT && onGround) {
                vy = jumpSpeed + counter;
                counter = 0;
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



    public void applyGravity(float dt) {
        if (!PlatformerModel.freezeTime && !shouldntMove) {
            vy -= 980 * dt;
        }
    }

    public void update(float dt, List<Platform> plats) {
        if (!PlatformerModel.freezeTime) {
            if (PlatformerModel.dashLevelWasBrought) {
                jumpSpeed = (jumpSpeed + (PlatformerModel.dashLevel / 2) * 0.1f);
                PlatformerModel.dashLevelWasBrought = false;
            }
            if (!shouldntMove) {
                x += vx * dt;
                y += vy * dt;
            }
            onGround = false;
            fall = false;
            if (y <= 20) {
                y = 20;
                onGround = true;
                vy = 0;
            }
            vx = 0;
            if (x > worldWidth - 20) x = worldWidth - 20;
            if (x < 0) x = 0;
            if (y > worldHeight - 20) y = worldHeight - 20;
            if (y < 20) y = 20;
            if(forefield && !initializeStabVarables){
                stabYRange *= 2;
                stabXRange *= 2;
                initializeStabVarables = true;
            }
                //Clocks/Timers
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
            
            if (dashCooldown > 0.0f) {
                dashCooldown -= 1.0f * dt;
                if (dashCooldown < 0.0f) dashCooldown = 0.0f;
            }
            
            if (stabCooldown > 0.0f) {
                stabCooldown -= 1.0f * dt;
                if (stabCooldown < 0.0f) stabCooldown = 0.0f;
            }
            
            if (stabCooldown <= stabCooldownTime / (9.0f / 10.0f)) {
                didstab = false;
            }
            
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
            if (invisibileFramCurrentTime > 0.0f) { // countdown
                invisibileFramCurrentTime -= 1.0f * dt;
                isInvisibile = true;
                if (invisibileFramCurrentTime <= 0.0f) {
                    invisibileFramCurrentTime = 0.0f;
                    isInvisibile = false;
                }
            }
            if (startPrayTimer) { // countUp
                prayTimer += 1.0f * dt;
            }
            for (Platform p : plats) {
                if (p.x - 20 < x && x < p.x + p.w && p.y + p.h - 5 < y && y < p.y + p.h + 5 && !fall) {
                    if (vy <= -1) {
                        y = p.y + p.h;
                        onGround = true;
                        numberOfJump = 0;
                        vy = 0;
                    }
                }
            }
        }
    }

    public boolean playerCollidesWithStar(Star s) {
        return Math.hypot((x + 10) - s.x, (y + 10) - s.y) < 15;
    }

    public boolean playerCollidesWithMob(MobA m) {
        return x < m.x + m.size && x + 20 > m.x && y < m.y + m.size && y + 20 > m.y;
    }
    
    public boolean mobCollidesWithStab(MobA m) {
        if (!didstab) return false;
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

    public boolean isStarInDashTriangle(Star s) {
        if (!showDashEffect) return false;
        return Math.abs((isfaceingRight ? x - 200 : x + 200) - s.x) <= 201 && Math.abs((y + 10) - s.y) < 15;
    }
    
    public float getDashCooldown() {
        return dashCooldown;
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
        
        if (counter == 0) {
            glColor4f(0, 0, 1, dashEffectAlpha);
        } else {
            float red = Math.min(1.0f, counter / 300f);
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

    public void render() {
        renderJumpOval();
        renderDashEffect();
        
        if (counter == 0) {
            glColor3f(0, 0, 1);
        } else {
            float red = Math.min(1.0f, counter / 300f);
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

