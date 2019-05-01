package com.mantkowicz.light.configuration.api;

import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.stage.AbstractStage;

import java.util.List;

public interface StagesConfiguration {
    List<AbstractStage> getStages();

    GameEventService getGameEventService();
}
