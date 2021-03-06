package com.mantkowicz.light.configuration.api;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.resources.ResourcesService;

public interface UIConfiguration {
    Stage getUiStage();

    ResourcesService getResourcesService();

    OrthographicCamera getCamera();

    GameEventService getGameEventService();
}
