package com.mantkowicz.light.configuration.api;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface CameraTrackingPluginConfiguration {
    Camera getCamera();

    Stage getStage();
}
