package com.mantkowicz.light.service.event.implementation;

import com.mantkowicz.light.actor.BoardGameActor;
import com.mantkowicz.light.service.event.GameEvent;

import static com.mantkowicz.light.service.event.GameEventType.LOOK_AT_ME;

public class LookAtMeEvent extends GameEvent<BoardGameActor> {
    public LookAtMeEvent(BoardGameActor boardGameActor) {
        super(LOOK_AT_ME, boardGameActor);
    }
}
