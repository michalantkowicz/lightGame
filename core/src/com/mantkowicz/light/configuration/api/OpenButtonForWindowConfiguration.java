package com.mantkowicz.light.configuration.api;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.resources.ResourcesService;

public interface OpenButtonForWindowConfiguration {
    Stage getUiStage();

    ResourcesService getResourcesService();

    GameEventService getGameEventService();
}