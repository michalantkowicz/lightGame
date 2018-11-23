package com.mantkowicz.light.player;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class FadeOutAnimation implements NotificationAnimation {
    private static final float DURATION = 2f;

    @Override
    public Action getAction(PlayerNotification label) {
        return Actions.fadeOut(DURATION);
    }
}
