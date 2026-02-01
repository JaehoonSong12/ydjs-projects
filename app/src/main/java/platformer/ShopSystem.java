package platformer;

/**
 * Manages shop state and purchases.
 */
public class ShopSystem {
    private boolean shopOpened = false;
    private boolean submitKeyPreviouslyDown = false;
    private boolean key1PreviouslyDown = false;
    private boolean key2PreviouslyDown = false;
    private boolean isDelay = true;
    private boolean shopOpenMovedisabldNoifcation = false;
    public static boolean reopenShop;
    private int dashLevelPrice = 0;
    private boolean dealarPayedThisRound = false;
    private boolean canGamble = true;

    public boolean isShopOpened() {
        return shopOpened;
    }

    public void toggleShop() {
        shopOpened = !shopOpened;
        if (shopOpened) {
            isDelay = true;
        } else {
            shopOpenMovedisabldNoifcation = false;
        }
    }

    public boolean isSubmitKeyPreviouslyDown() {
        return submitKeyPreviouslyDown;
    }

    public void setSubmitKeyPreviouslyDown(boolean value) {
        submitKeyPreviouslyDown = value;
    }

    public boolean isKey1PreviouslyDown() {
        return key1PreviouslyDown;
    }

    public void setKey1PreviouslyDown(boolean value) {
        key1PreviouslyDown = value;
    }

    public boolean isKey2PreviouslyDown() {
        return key2PreviouslyDown;
    }

    public void setKey2PreviouslyDown(boolean value) {
        key2PreviouslyDown = value;
    }

    public boolean isDelay() {
        return isDelay;
    }

    public void setDelay(boolean value) {
        isDelay = value;
    }

    public boolean isShopOpenMovedisabldNoifcation() {
        return shopOpenMovedisabldNoifcation;
    }

    public void setShopOpenMovedisabldNoifcation(boolean value) {
        shopOpenMovedisabldNoifcation = value;
    }

    public int getDashLevelPrice() {
        return dashLevelPrice;
    }

    public void setDashLevelPrice(int price) {
        dashLevelPrice = price;
    }

    public boolean isDealarPayedThisRound() {
        return dealarPayedThisRound;
    }

    public void setDealarPayedThisRound(boolean value) {
        dealarPayedThisRound = value;
    }

    public boolean canGamble() {
        return canGamble;
    }

    public void setCanGamble(boolean value) {
        canGamble = value;
    }
}

