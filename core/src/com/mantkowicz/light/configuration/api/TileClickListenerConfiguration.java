package com.mantkowicz.light.configuration.api;

import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.stage.NotificationStage;

public interface TileClickListenerConfiguration {
    GameEventService getGameEventService();

    NotificationStage getNotificationStage();

    PhraseService getPhraseService();
}
