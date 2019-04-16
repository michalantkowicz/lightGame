package com.mantkowicz.light.service.event.implementation;

import com.mantkowicz.light.actor.GameBoardActor;
import com.mantkowicz.light.service.event.GameEvent;

import static com.mantkowicz.light.service.event.GameEventType.MENU_OPEN;

public class MenuOpenEvent extends GameEvent<GameBoardActor> {
    public MenuOpenEvent(GameBoardActor boardGameActor) {
        super(MENU_OPEN, boardGameActor);
    }
}
