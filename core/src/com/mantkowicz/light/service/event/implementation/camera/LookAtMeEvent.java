package com.mantkowicz.light.service.event.implementation.camera;

import com.mantkowicz.light.actor.GameBoardActor;
import com.mantkowicz.light.service.event.GameEvent;

import static com.mantkowicz.light.service.event.GameEventType.LOOK_AT_ME;

public class LookAtMeEvent extends GameEvent<GameBoardActor> {
    public LookAtMeEvent(GameBoardActor boardGameActor) {
        super(LOOK_AT_ME, boardGameActor);
    }
}
