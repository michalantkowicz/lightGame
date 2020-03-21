package com.mantkowicz.light.configuration.api;

import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.service.resources.ResourcesService;
import com.mantkowicz.light.stage.NotificationStage;

public interface BoardConfiguration {
    GameEventService getGameEventService();

    NotificationStage getNotificationStage();

    PhraseService getPhraseService();

    ResourcesService getResourcesService();
}
