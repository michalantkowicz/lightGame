package com.mantkowicz.light.interaction;

import com.mantkowicz.light.actor.GameBoardActor;
import com.mantkowicz.light.notification.Notification;
import com.mantkowicz.light.notification.NotificationType;
import com.mantkowicz.light.notification.animation.MoveUpperAndFadeOutAnimation;
import com.mantkowicz.light.notification.factory.NotificationBuilder;
import com.mantkowicz.light.service.resources.ThumbnailType;
import com.mantkowicz.light.stage.NotificationStage;

public class DescriptionInteraction implements Interaction {
    private final NotificationStage stage;
    private final GameBoardActor actor;
    private final Notification description;

    public DescriptionInteraction(NotificationStage stage, GameBoardActor actor, String description) {
        this.stage = stage;
        this.actor = actor;

        this.description = new NotificationBuilder(description)
                .notificationAnimation(new MoveUpperAndFadeOutAnimation())
                .notificationType(NotificationType.DESCRIPTION)
                .build();
    }

    @Override
    public void interact() {
        description.setCenterAt(actor.getCenter());
//        stage.addActor(description);
        System.out.println(description);
    }

    @Override
    public ThumbnailType getThumbnailType() {
        return ThumbnailType.DEFAULT;
    }
}
