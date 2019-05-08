package com.mantkowicz.light.interaction;

import com.mantkowicz.light.actor.GameBoardActor;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.AddToStageEvent;
import com.mantkowicz.light.service.resources.ResourcesService;
import com.mantkowicz.light.service.resources.ThumbnailType;

public class DescriptionInteraction implements Interaction {
    private final GameEventService eventService;
    private final DescriptionDefinition descriptionDefinition;
    private final GameBoardActor actor;

    public DescriptionInteraction(ResourcesService resourcesService, GameEventService eventService, GameBoardActor actor, String description) {
        this.eventService = eventService;
        this.actor = actor;

        descriptionDefinition = DescriptionDefinition.builder()
                .background(resourcesService.getNinePatch("tooltip"))
                .description(description)
                .parent(actor)
                .build();
    }

    @Override
    public void interact() {
        //TODO to remove the actor from the stage after some time
        eventService.addEvent(new AddToStageEvent(descriptionDefinition));
    }

    @Override
    public ThumbnailType getThumbnailType() {
        return ThumbnailType.DEFAULT;
    }
}
