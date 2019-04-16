package com.mantkowicz.light.configuration.api;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.service.event.GameEventService;

public interface CameraTrackingFeatureConfiguration {
    OrthographicCamera getCamera();

    Stage getStage();

    GameEventService getGameEventService();
}
