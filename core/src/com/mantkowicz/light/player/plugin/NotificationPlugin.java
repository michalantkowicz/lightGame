package com.mantkowicz.light.player.plugin;

import box2dLight.RayHandler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mantkowicz.light.player.*;

import static com.mantkowicz.light.player.NotificationType.WARN;

public class NotificationPlugin extends Plugin {
    private Player player;
    private Vector2 notificationOffset;
    private RayHandler rayHandler;
    private Stage notificationStage;
    private Array<String> darknessNotifications;

    public NotificationPlugin(Player player, Vector2 notificationOffset, RayHandler rayHandler, Stage notificationStage) {
        this.player = player;
        this.notificationOffset = notificationOffset;
        this.rayHandler = rayHandler;
        this.notificationStage = notificationStage;

        initDarknessNotifications();
    }

    @Override
    public void run() {
        Vector2 playerCenter = player.getCenter();
        if (rayHandler.pointAtShadow(playerCenter.x, playerCenter.y) && notificationStage.getActors().size <= 0) {
            notificationStage.addActor(getDarknessShout());
        }
    }

    private PlayerNotification getDarknessShout() {
        NotificationAnimation notificationAnimation = new EnlargeAndFadeOutAnimation();
        PlayerNotification playerNotification = new PlayerNotification(randomDarknessNotification(), WARN, notificationAnimation);
        Vector2 notificationPosition = player.getCenter().add(notificationOffset).sub(playerNotification.getWidth() / 2f, 0);
        playerNotification.setPosition(notificationPosition.x, notificationPosition.y);
        return playerNotification;
    }

    private String randomDarknessNotification() {
        darknessNotifications.shuffle();
        return darknessNotifications.first();
    }

    public void initDarknessNotifications() {
        darknessNotifications = new Array<>();
        darknessNotifications.add("dude I'm scared of darkness!");
        darknessNotifications.add("please take me away from here...");
        darknessNotifications.add("I can't stand this anymore!!!");
        darknessNotifications.add("did you hear that...?");
        darknessNotifications.add("I feel like I'm going crazy");
    }
}
