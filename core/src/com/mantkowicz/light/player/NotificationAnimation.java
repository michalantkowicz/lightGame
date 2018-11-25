package com.mantkowicz.light.player;

import com.badlogic.gdx.scenes.scene2d.Action;

public interface NotificationAnimation {
    Action getAction(PlayerNotification label);
}
