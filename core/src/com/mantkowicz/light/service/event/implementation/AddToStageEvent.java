package com.mantkowicz.light.service.event.implementation;

import com.mantkowicz.light.actor.GameActorDefinition;
import com.mantkowicz.light.service.event.GameEvent;

import static com.mantkowicz.light.service.event.GameEventType.ADD_TO_STAGE;

public class AddToStageEvent extends GameEvent<GameActorDefinition> {
    public AddToStageEvent(GameActorDefinition definition) {
        super(ADD_TO_STAGE, definition);
    }
}
