package com.mantkowicz.light.service.event.implementation;

import com.badlogic.gdx.math.Vector2;
import com.mantkowicz.light.service.event.GameEvent;

import static com.mantkowicz.light.service.event.GameEventType.MENU_CLOSE;

public class MenuCloseEvent extends GameEvent<Vector2> {
    public MenuCloseEvent(Vector2 pointerPosition) {
        super(MENU_CLOSE, pointerPosition);
    }
}
