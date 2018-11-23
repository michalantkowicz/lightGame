package com.mantkowicz.light.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class PlayerNotification extends Label {
    public PlayerNotification(String text, NotificationType notificationType, NotificationAnimation notificationAnimation) {
        super(text, LabelStyleBuilder.build(notificationType));
        addAction(notificationAnimation.getAction(this));
        setAlignment(Align.center);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getActions().size <= 0) {
            remove();
        }
    }

    private static class LabelStyleBuilder {
        private static LabelStyle build(NotificationType notificationType) {
            LabelStyle labelStyle = new LabelStyle();
            labelStyle.font = new BitmapFont();
            switch (notificationType) {
                case WARN:
                    labelStyle.fontColor = Color.RED;
                    break;
                default:
                    labelStyle.fontColor = Color.WHITE;
            }
            return labelStyle;
        }
    }

    public Vector2 getCenter() {
        return new Vector2(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }
}
