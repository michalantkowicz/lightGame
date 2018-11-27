package com.mantkowicz.light.actor.plugin;

import box2dLight.RayHandler;
import com.badlogic.gdx.math.Vector2;
import com.mantkowicz.light.notification.Notification;
import com.mantkowicz.light.notification.animation.EnlargeAndFadeOutAnimation;
import com.mantkowicz.light.notification.factory.NotificationBuilder;
import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.stage.NotificationStage;

import static com.mantkowicz.light.notification.NotificationType.PLAYER_NOTIFICATION;
import static com.mantkowicz.light.service.phrase.PhraseGroup.DARKNESS_EXCLAMATION;

public class NotificationPlugin extends Plugin {
    private static final int MILLIS_TO_EXCLAIM_AT_SHADOW = 2000;

    private Player player;
    private Vector2 notificationOffset;
    private RayHandler rayHandler;
    private NotificationStage notificationStage;
    private PhraseService phraseService;

    public NotificationPlugin(Player player, Vector2 notificationOffset, RayHandler rayHandler, NotificationStage notificationStage, PhraseService phraseService) {
        this.player = player;
        this.notificationOffset = notificationOffset;
        this.rayHandler = rayHandler;
        this.notificationStage = notificationStage;
        this.phraseService = phraseService;
    }

    @Override
    public void run() {
        if (shouldPlayerExclaimeAtShadow()) {
            Notification shadowNotification = getExclamationAtShadow();
            notificationStage.addActor(shadowNotification);
            shadowNotification.toFront();
        } else if (player.getIdleLength() < MILLIS_TO_EXCLAIM_AT_SHADOW) {
            notificationStage.removeAllNotificationsOfType(PLAYER_NOTIFICATION);
        }
    }

    private boolean shouldPlayerExclaimeAtShadow() {
        return isPlayerAtShadow()
                && player.getIdleLength() >= MILLIS_TO_EXCLAIM_AT_SHADOW
                && !notificationStage.doesContainNotification(PLAYER_NOTIFICATION);
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

    private Notification getExclamationAtShadow() {
        String exclamationText = phraseService.getRandomPhrase(DARKNESS_EXCLAMATION);
        Vector2 notificationCenter = player.getCenter().add(notificationOffset);
        return new NotificationBuilder(exclamationText)
                .notificationAnimation(new EnlargeAndFadeOutAnimation())
                .notificationType(PLAYER_NOTIFICATION)
                .centerAt(notificationCenter)
                .build();
    }
}
