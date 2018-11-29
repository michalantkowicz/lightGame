package com.mantkowicz.light.configuration.api;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.service.resources.ResourcesService;

public interface PlayerCollectResolverConfiguration {
    Stage getUiStage();

    ResourcesService getResourcesService();
}
