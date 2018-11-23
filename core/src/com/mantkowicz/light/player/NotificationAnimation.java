package com.mantkowicz.light.player;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public interface NotificationAnimation {
    Action getAction(PlayerNotification label);
}
