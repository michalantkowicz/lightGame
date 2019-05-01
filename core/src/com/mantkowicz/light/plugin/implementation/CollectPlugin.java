package com.mantkowicz.light.plugin.implementation;

import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.configuration.api.CollectPluginConfiguration;
import com.mantkowicz.light.plugin.CollectResolver;
import com.mantkowicz.light.plugin.Plugin;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.CollectEvent;

import static com.mantkowicz.light.service.event.GameEventType.COLLECT;

public class CollectPlugin implements Plugin {
    private final CollectResolver collectResolver;
    private final GameEventService gameEventService;

    public CollectPlugin(CollectResolver collectResolver, CollectPluginConfiguration configuration) {
        this.collectResolver = collectResolver;
        this.gameEventService = configuration.getGameEventService();
    }

    @Override
    public void run(float delta) {
        if (gameEventService.containsEvent(COLLECT)) {
            CollectEvent gameEvent = gameEventService.removeEventFromQueue(COLLECT, CollectEvent.class);
            Collectible collectible = gameEvent.getEventObject();
            collectResolver.resolveCollect(collectible);
        }
    }
}
