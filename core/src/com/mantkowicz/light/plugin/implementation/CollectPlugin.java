package com.mantkowicz.light.plugin.implementation;

import com.mantkowicz.light.configuration.api.CollectPluginConfiguration;
import com.mantkowicz.light.plugin.CollectResolver;
import com.mantkowicz.light.plugin.Collectible;
import com.mantkowicz.light.plugin.Plugin;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.CollectEvent;

import static com.mantkowicz.light.service.event.GameEventType.COLLECT;

public class CollectPlugin extends Plugin {
    private CollectResolver collectResolver;
    private final GameEventService gameEventService;

    public CollectPlugin(CollectResolver collectResolver, CollectPluginConfiguration configuration) {
        this.collectResolver = collectResolver;
        this.gameEventService = configuration.getGameEventService();
    }

    @Override
    public void run() {
        if (gameEventService.containsEvent(COLLECT)) {
            CollectEvent gameEvent = gameEventService.getEvent(COLLECT, CollectEvent.class, true);
            Collectible collectible = gameEvent.getEventObject();
            collectResolver.resolveCollect(collectible);
        }
    }
}
