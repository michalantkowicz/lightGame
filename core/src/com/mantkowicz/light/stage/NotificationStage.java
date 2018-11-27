package com.mantkowicz.light.stage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mantkowicz.light.notification.Notification;
import com.mantkowicz.light.notification.NotificationType;

public class NotificationStage extends Stage {
    public NotificationStage(Viewport viewport) {
        super(viewport);
    }

    public boolean doesContainNotification(NotificationType notificationType) {
        for (Actor actor : getActors()) {
            if (actor instanceof Notification) {
                if (notificationType.equals(((Notification) actor).getNotificationType())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNotificationCount(NotificationType notificationType) {
        int count = 0;
        for (Actor actor : getActors()) {
            if (actor instanceof Notification) {
                if (notificationType.equals(((Notification) actor).getNotificationType())) {
                    count++;
                }
            }
        }
        return count;
    }

    public void removeAllNotificationsOfType(NotificationType notificationType) {
        for (Actor actor : getActors()) {
            if (actor instanceof Notification) {
                if (notificationType.equals(((Notification) actor).getNotificationType())) {
                    actor.remove();
                }
            }
        }
    }
}
