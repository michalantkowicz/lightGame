package com.mantkowicz.light.plugin.implementation;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.actor.implementation.CameraActor;
import com.mantkowicz.light.configuration.api.CameraTrackingPluginConfiguration;
import com.mantkowicz.light.plugin.Plugin;

public class CameraTrackingPlugin implements Plugin {
    private static final float DISTANCE = 200;
    private static final float DISTANCE2 = DISTANCE * DISTANCE;
    private static final float MOVEMENT_TIME = 1.5f;

    private GameActor actor;
    private Camera camera;
    private CameraActor cameraActor;

    public CameraTrackingPlugin(GameActor actor, CameraTrackingPluginConfiguration configuration) {
        this.actor = actor;
        this.camera = configuration.getCamera();

        this.cameraActor = new CameraActor(configuration.getCamera());
        configuration.getStage().addActor(cameraActor);
    }

    @Override
    public void run(float delta) {
        if (shouldCameraMove()) {
            cameraActor.clearActions();
            Vector2 actorCenterPosition = actor.getCenter();
            MoveToAction action = Actions.moveTo(actorCenterPosition.x, actorCenterPosition.y, MOVEMENT_TIME, Interpolation.sineOut);
            cameraActor.addAction(action);
        }
    }

    private boolean shouldCameraMove() {
        return actor.getCenter().dst2(camera.position.x, camera.position.y) > DISTANCE2;
    }
}
