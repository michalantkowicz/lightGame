package com.mantkowicz.light.configuration;

import com.mantkowicz.light.service.event.GameEventService;

public interface CollectPluginConfiguration {
    GameEventService getGameEventService();
}
