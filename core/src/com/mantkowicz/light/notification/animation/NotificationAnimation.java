package com.mantkowicz.light.notification.animation;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.mantkowicz.light.notification.Notification;

public interface NotificationAnimation {
    Action getAction(Notification notification);
}
