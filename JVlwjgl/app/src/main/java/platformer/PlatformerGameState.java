package platformer;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.glClearColor;

import dev.lwjgl.UIWindow;

/**
 * Game state manager for the platformer game.
 * Handles input, shop interactions, and coordinates rendering.
 */
public class PlatformerGameState {
    private final UIWindow window;
    private final PlatformerModel model;
    private final double[] cursor = new double[2];
    
    // Key state tracking for continuous input
    private final boolean[] keyStates = new boolean[512];

    private boolean[] prevKeyStates = new boolean[512];

    public PlatformerGameState(UIWindow window) {
        this.window = window;
        this.model = new PlatformerModel(window.getWinW(), window.getWinH());
    }

    public void update(double delta) {
        window.getCursorPos(cursor);
        handleContinuousInput();
        model.update((float) delta);
    }

    private void handleContinuousInput() {
        var keyMap = model.getControlMapper().getKeyMap();
        var player = model.getPlayer();
        var shop = model.getShopSystem();

        // ---------- MOVE LEFT ----------
        if (keyStates[keyMap.get("LEFT")] && !PlatformerModel.freezeTime) {
            player.moveHoriz(-1, false);
        } else if (keyStates[keyMap.get("LEFT")] && !shop.isShopOpenMovedisabldNoifcation()) {
            model.showMessage("You cant move while shop is open");
            shop.setShopOpenMovedisabldNoifcation(true);
        }

        // ---------- MOVE RIGHT ----------
        if (keyStates[keyMap.get("RIGHT")] && !PlatformerModel.freezeTime) {
            player.moveHoriz(1, true);
        } else if (keyStates[keyMap.get("RIGHT")] && !shop.isShopOpenMovedisabldNoifcation()) {
            model.showMessage("You cant move while shop is open");
            shop.setShopOpenMovedisabldNoifcation(true);
        }

        // ---------- CHARACTER SKILL ----------
        int skillKey = keyMap.get("CHARATER SKILL");

        boolean skillPressed  =  keyStates[skillKey] && !prevKeyStates[skillKey];
        boolean skillHeld     =  keyStates[skillKey];
        boolean skillReleased = !keyStates[skillKey] &&  prevKeyStates[skillKey];

        if (skillHeld) {
            if (player.whichcharacter == 0) {
                player.dash();
            }
            else if (player.whichcharacter == 1 && shop.canGamble() && !player.skipGambling) {
                shop.setCanGamble(false);
                model.performGambling();
            }
            else if (player.whichcharacter == 2) {
                player.stab();
            }
            else if (player.whichcharacter == 3) {
                player.prayHold();   // HOLD behavior
            }
        }

        if (skillReleased) {
            if (player.whichcharacter == 3) {
                player.prayRelease(); // RELEASE behavior (heal here)
            }
        }

        // ---------- JUMP CHARGE ----------
        if (keyStates[keyMap.get("UP")]) {
            player.jCharge();
        }
        
        // Handle fall (continuous)
        if (keyStates[keyMap.get("DOWN")]) {
            player.fall();
        }
            // PlatformerModel.gainLife(); // move this to prayRelease or elsewhere

        // ---------- SAVE PREVIOUS STATES ----------
        System.arraycopy(keyStates, 0, prevKeyStates, 0, keyStates.length);
    }

    public void render() {
        glClearColor(0.5f, 0.8f, 1f, 0);
        model.render();
        model.renderUI();
    }

    public void onKey(int key, int action) {
        // Update key state
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            keyStates[key] = true;
        } else if (action == GLFW.GLFW_RELEASE) {
            keyStates[key] = false;
            
            // Handle key release events
            var keyMap = model.getControlMapper().getKeyMap();
            if (key == keyMap.get("UP")) {
                model.getPlayer().jump();
            }
            if (key == keyMap.get("CHARATER SKILL") && model.getPlayer().whichcharacter == 1) {
                model.getShopSystem().setCanGamble(true);
            }
        }
        
        if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_PRESS) {
            window.requestClose();
            return;
        }

        // Handle shop toggle
        if (key == GLFW.GLFW_KEY_ENTER && action == GLFW.GLFW_PRESS) {
            handleShopToggle();
        }

        // Handle shop purchases
        if (model.getShopSystem().isShopOpened()) {
            if (key == GLFW.GLFW_KEY_1 && action == GLFW.GLFW_PRESS) {
                handleShopPurchase1();
            } else if (key == GLFW.GLFW_KEY_2 && action == GLFW.GLFW_PRESS) {
                handleShopPurchase2();
            }
        }
    }

    public void onMouse(int button, int action, double x, double y) {
        // Mouse handling if needed
    }

    private void handleShopToggle() {
        var shop = model.getShopSystem();
        boolean submitKeyPreviouslyDown = shop.isSubmitKeyPreviouslyDown();
        
        if (!submitKeyPreviouslyDown) {
            shop.toggleShop();
            PlatformerModel.freezeTime = shop.isShopOpened();
            
            if (shop.isShopOpened()) {
                model.getMessageSystem().clear();
                model.showMessage("You have entered the shop");
                model.showMessage("The things you can buy:");
                shop.setDelay(true);
                
                if (model.getPlayer().whichcharacter == 0 && shop.isDelay()) {
                    model.showMessage("Press 1 for the next dash level for " + shop.getDashLevelPrice() + " score");
                    if (!model.getPlayer().skipGambling) {
                        model.showMessage("Press 2 to change your skill to gambling");
                    } else {
                        model.showMessage("Press 2 to change your skill to stabbing");
                    }
                    shop.setDelay(false);
                } else if (model.getPlayer().whichcharacter == 1 && !shop.isDealarPayedThisRound() && shop.isDelay()) {
                    model.showMessage("Press 1 to pay the dealer this round for 20 score");
                    model.showMessage("Press 2 to change your skill to dash");
                    shop.setDelay(false);
                }
            } else {
                model.showMessage("You have left the shop");
                shop.setShopOpenMovedisabldNoifcation(false);
            }
        }
        
        shop.setSubmitKeyPreviouslyDown(true);
    }


    private void handleShopPurchase1() {
        var shop = model.getShopSystem();
        var player = model.getPlayer();
        
        // Prevent immediate purchases when shop just opened
        
        if (player.whichcharacter == 0 && model.getScore() >= shop.getDashLevelPrice()) {
            // Purchase dash level
            model.setScore(model.getScore() - shop.getDashLevelPrice());
            if (Player.testing) {
                PlatformerModel.dashLevel += 10.0f;
            } else {
                PlatformerModel.dashLevel += 1.0f;
            }
            model.showMessage("Dash level purchased!");
            PlatformerModel.dashLevelWasBrought = true;
            if (!Player.testing) {
                shop.setDashLevelPrice((int) (PlatformerModel.dashLevel * 5));
            }
        } else if (player.whichcharacter == 1 && model.getScore() >= 20 && !shop.isDealarPayedThisRound()) {
            model.setScore(model.getScore() - 20);
            shop.setDealarPayedThisRound(true);
            model.showMessage("Dealer paid, your score is now: " + model.getScore());
        } else {
            model.showMessage("You're too poor to buy that");
        }
    }

    private void handleShopPurchase2() {
        var player = model.getPlayer();
        
        // Prevent immediate purchases when shop just opened
        
        if (player.whichcharacter == 0) {
            if (!player.skipGambling) {
                player.whichcharacter = 1;
                model.showMessage("Your charator skil is now gambling");
            } else {
                player.whichcharacter = 2;
                model.showMessage("Your charator skil is now stabbing");
            }
        } else if (player.whichcharacter == 1) {
            player.whichcharacter = 0;
            model.showMessage("Your charator skil is now dash");
        } else if (player.whichcharacter == 2) {
            player.whichcharacter = 3;
            model.showMessage("Your charator skil is now praying");
        }else if (player.whichcharacter == 3) {
            player.whichcharacter = 0;
            model.showMessage("Your charator skil is now dash");
        }
    }

    public PlatformerModel getModel() {
        return model;
    }

    public void cleanup() {
        model.cleanup();
    }
}

