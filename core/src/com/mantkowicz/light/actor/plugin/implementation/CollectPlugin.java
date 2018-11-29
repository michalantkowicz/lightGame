package com.mantkowicz.light.actor.plugin.implementation;

import com.mantkowicz.light.actor.Collecting;
import com.mantkowicz.light.actor.plugin.Plugin;
import com.mantkowicz.light.configuration.CollectPluginConfiguration;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.CollectEvent;

import static com.mantkowicz.light.service.event.GameEventType.COLLECT;

public class CollectPlugin extends Plugin {
    private final Collecting collectingObject;
    private final GameEventService gameEventService;

    public CollectPlugin(Collecting collectingObject, CollectPluginConfiguration configuration) {
        this.collectingObject = collectingObject;
        this.gameEventService = configuration.getGameEventService();
    }

    @Override
    public void run() {
        if (gameEventService.containsEvent(COLLECT)) {
            CollectEvent gameEvent = (CollectEvent) gameEventService.getEvent(COLLECT, true);
            collectingObject.collect(gameEvent.getEventObject());
            gameEvent.getEventObject().afterCollect();
        }
    }
}
