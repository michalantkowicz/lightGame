package com.mantkowicz.light.board.tile.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.notification.Notification;
import com.mantkowicz.light.notification.animation.MoveUpperAndFadeOutAnimation;
import com.mantkowicz.light.notification.factory.NotificationBuilder;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.stage.NotificationStage;

import static com.mantkowicz.light.notification.NotificationStyle.WARNING;
import static com.mantkowicz.light.notification.NotificationType.NO_PATH_NOTIFICATION;
import static com.mantkowicz.light.service.phrase.PhraseGroup.NO_PATH;

public class NoPathTileClickListener extends ClickListener {
    private final Tile tile;
    private final NotificationStage notificationStage;
    private final PhraseService phraseService;

    public NoPathTileClickListener(Tile tile, NotificationStage notificationStage, PhraseService phraseService) {
        this.tile = tile;
        this.notificationStage = notificationStage;
        this.phraseService = phraseService;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        String noPathText = phraseService.getRandomPhrase(NO_PATH);
        Vector2 notificationCenter = tile.getCenter();
        Notification noPathNotification = new NotificationBuilder(noPathText)
                .notificationAnimation(new MoveUpperAndFadeOutAnimation())
                .notificationType(NO_PATH_NOTIFICATION)
                .notificationStyle(WARNING)
                .centerAt(notificationCenter)
                .build();
        notificationStage.addActor(noPathNotification);
    }
}