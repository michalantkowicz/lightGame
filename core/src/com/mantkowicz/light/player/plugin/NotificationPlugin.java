package com.mantkowicz.light.player.plugin;

import box2dLight.RayHandler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mantkowicz.light.player.*;
import com.mantkowicz.light.stage.NotificationStage;

import static com.mantkowicz.light.player.NotificationType.WARN;

public class NotificationPlugin extends Plugin {
    private static final int MILLIS_TO_EXCLAIM_AT_SHADOW = 2000;

    private Player player;
    private Vector2 notificationOffset;
    private RayHandler rayHandler;
    private NotificationStage notificationStage;
    private Array<String> darknessNotifications;

    public NotificationPlugin(Player player, Vector2 notificationOffset, RayHandler rayHandler, NotificationStage notificationStage) {
        this.player = player;
        this.notificationOffset = notificationOffset;
        this.rayHandler = rayHandler;
        this.notificationStage = notificationStage;

        initExclamationAtShadowNotifications();
    }

    @Override
    public void run() {
        if (shouldPlayerExclaimeAtShadow()) {
            PlayerNotification exclamationAtShadow = getExclamationAtShadow();
            notificationStage.addActor(exclamationAtShadow);
            exclamationAtShadow.toFront();
        } else if (player.getIdleLength() < MILLIS_TO_EXCLAIM_AT_SHADOW) {
            notificationStage.clear();
        }
    }

    private boolean shouldPlayerExclaimeAtShadow() {
        return isPlayerAtShadow()
                && player.getIdleLength() >= MILLIS_TO_EXCLAIM_AT_SHADOW
                && !notificationStage.doesContainNotification(NotificationType.WARN);
    }

    /**
     * Check whether the player is at shadow. Method check four corners of actor bounding box
     *
     * @return whether at least one corner is at shadow
     */
    private boolean isPlayerAtShadow() {
        return rayHandler.pointAtShadow(player.getX(), player.getY())
                || rayHandler.pointAtShadow(player.getX() + player.getWidth(), player.getY())
                || rayHandler.pointAtShadow(player.getX() + player.getWidth(), player.getY() + player.getHeight())
                || rayHandler.pointAtShadow(player.getX(), player.getY() + player.getHeight());
    }

    private PlayerNotification getExclamationAtShadow() {
        NotificationAnimation notificationAnimation = new EnlargeAndFadeOutAnimation();
        PlayerNotification playerNotification = new PlayerNotification(randomExclamationAtShadowNotification(), WARN, notificationAnimation);
        Vector2 notificationPosition = player.getCenter().add(notificationOffset).sub(playerNotification.getWidth() / 2f, 0);
        playerNotification.setPosition(notificationPosition.x, notificationPosition.y);
        return playerNotification;
    }

    private String randomExclamationAtShadowNotification() {
        darknessNotifications.shuffle();
        return darknessNotifications.first();
    }

    public void initExclamationAtShadowNotifications() {
        darknessNotifications = new Array<>();
        darknessNotifications.add("dude I'm scared of darkness!");
        darknessNotifications.add("please take me away from here...");
        darknessNotifications.add("I can't stand this anymore!!!");
        darknessNotifications.add("did you hear that...?");
        darknessNotifications.add("I feel like I'm going crazy");
    }
}
