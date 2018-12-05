package com.mantkowicz.light.configuration.api;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.resources.ResourcesService;

public interface PlayerCollectResolverConfiguration extends CollectWindowConfiguration, OpenButtonForWindowConfiguration, CameraTrackingPluginConfiguration {
    @Override
    Stage getUiStage();

    @Override
    ResourcesService getResourcesService();

    @Override
    GameEventService getGameEventService();

    @Override
    Camera getCamera();

    @Override
    Stage getStage();
}
