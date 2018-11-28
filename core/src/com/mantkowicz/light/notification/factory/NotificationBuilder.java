package com.mantkowicz.light.notification.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mantkowicz.light.notification.Notification;
import com.mantkowicz.light.notification.NotificationStyle;
import com.mantkowicz.light.notification.NotificationType;
import com.mantkowicz.light.notification.animation.FadeOutAnimation;
import com.mantkowicz.light.notification.animation.NotificationAnimation;

public class NotificationBuilder {
    private NotificationType notificationType;
    private NotificationStyle notificationStyle;
    private NotificationAnimation notificationAnimation;
    private Vector2 notificationCenter;
    private final String text;

    public NotificationBuilder(String text) {
        this.text = text;

        notificationStyle = NotificationStyle.DEFAULT;
        notificationType = NotificationType.DEFAULT;
        notificationAnimation = new FadeOutAnimation();
    }

    public NotificationBuilder notificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public NotificationBuilder notificationStyle(NotificationStyle notificationStyle) {
        this.notificationStyle = notificationStyle;
        return this;
    }

    public NotificationBuilder notificationAnimation(NotificationAnimation notificationAnimation) {
        this.notificationAnimation = notificationAnimation;
        return this;
    }

    public NotificationBuilder centerAt(Vector2 notificationCenter) {
        this.notificationCenter = notificationCenter;
        return this;
    }

    public Notification build() {
        Label label = LabelFactory.createLabel(notificationStyle, text);

        Notification notification = new Notification(notificationType, label);
        notification.addAction(notificationAnimation.getAction(notification));

        notificationCenter.sub(notification.getWidth() / 2f, notification.getHeight() / 2f);
        notification.setPosition(notificationCenter.x, notificationCenter.y);

        return notification;
    }
}
