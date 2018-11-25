package com.mantkowicz.light.stage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mantkowicz.light.player.NotificationType;
import com.mantkowicz.light.player.PlayerNotification;

public class NotificationStage extends Stage {
    public NotificationStage(Viewport viewport) {
        super(viewport);
    }

    public boolean doesContainNotification(NotificationType notificationType) {
        for (Actor actor : getActors()) {
            if (actor instanceof PlayerNotification) {
                if (notificationType.equals(((PlayerNotification) actor).getNotificationType())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNotificationCount(NotificationType notificationType) {
        int count = 0;
        for (Actor actor : getActors()) {
            if (actor instanceof PlayerNotification) {
                if (notificationType.equals((PlayerNotification) actor)) {
                    count++;
                }
            }
        }
        return count;
    }
}
