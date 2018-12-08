package com.mantkowicz.light.configuration.api;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.service.event.GameEventService;

public interface CameraTrackingPluginConfiguration {
    Camera getCamera();

    Stage getStage();

    GameEventService getGameEventService();
}
