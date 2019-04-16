package com.mantkowicz.light.configuration.api;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.resources.ResourcesService;

public interface PlayerCollectResolverConfiguration extends UIConfiguration, CollectWindowConfiguration, OpenButtonForWindowConfiguration, CameraTrackingFeatureConfiguration, MenuFeatureConfiguration {
    @Override
    Stage getUiStage();

    @Override
    ResourcesService getResourcesService();

    @Override
    GameEventService getGameEventService();

    @Override
    OrthographicCamera getCamera();

    @Override
    Stage getStage();
}
