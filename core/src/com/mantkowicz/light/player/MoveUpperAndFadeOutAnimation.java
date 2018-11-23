package com.mantkowicz.light.player;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MoveUpperAndFadeOutAnimation implements NotificationAnimation {
    private static final float DURATION = 2f;

    @Override
    public Action getAction(PlayerNotification label) {
        return Actions.parallel(Actions.moveBy(0, 20, DURATION), Actions.fadeOut(DURATION));
    }
}
