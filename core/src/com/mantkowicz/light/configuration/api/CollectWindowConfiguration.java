package com.mantkowicz.light.configuration.api;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.service.resources.ResourcesService;

public interface CollectWindowConfiguration {
    ResourcesService getResourcesService();

    Stage getUiStage();

    OrthographicCamera getCamera();
}
