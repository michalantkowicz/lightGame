package com.mantkowicz.light.configuration.api;

import box2dLight.RayHandler;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.stage.NotificationStage;

public interface NotificationPluginConfiguration {
    RayHandler getRayHandler();

    NotificationStage getNotificationStage();

    PhraseService getPhraseService();
}
