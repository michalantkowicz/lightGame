package com.mantkowicz.light.interaction;

import com.mantkowicz.light.actor.GameBoardActor;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.AddToStageEvent;
import com.mantkowicz.light.service.resources.ThumbnailType;

public class DescriptionInteraction implements Interaction {
    private final GameEventService eventService;
    private final DescriptionDefinition descriptionDefinition;
private final GameBoardActor actor;


    public DescriptionInteraction(GameEventService eventService, GameBoardActor actor, String description) {
        this.eventService = eventService;
        this.actor = actor;

        descriptionDefinition = new DescriptionDefinition().setDescription(description).setPosition(actor.getCenter().add(0, actor.getHeight() / 2f));
    }

    @Override
    public void interact() {
        descriptionDefinition.setPosition(actor.getCenter().add(0, actor.getHeight() / 2f));
        eventService.addEvent(new AddToStageEvent(descriptionDefinition));
    }

    @Override
    public ThumbnailType getThumbnailType() {
        return ThumbnailType.DEFAULT;
    }
}
