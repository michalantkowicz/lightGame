package com.mantkowicz.light.configuration.api;

import com.mantkowicz.light.service.event.GameEventService;

public interface CollectPluginConfiguration {
    GameEventService getGameEventService();
}
