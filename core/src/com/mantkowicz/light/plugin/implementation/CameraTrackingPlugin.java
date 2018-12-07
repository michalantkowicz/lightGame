package com.mantkowicz.light.plugin.implementation;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.actor.implementation.CameraActor;
import com.mantkowicz.light.configuration.api.CameraTrackingPluginConfiguration;
import com.mantkowicz.light.plugin.Plugin;

public class CameraTrackingPlugin implements Plugin {
    private static final float DISTANCE_LEFT_RIGHT = 300;
    private static final float DISTANCE_TOP_BOTTOM = 800;
    private static final float MOVEMENT_TIME = .75f;

    private GameActor actor;
    private CameraActor cameraActor;
    private boolean centeredAtStart = false;

    public CameraTrackingPlugin(GameActor actor, CameraTrackingPluginConfiguration configuration) {
        this.actor = actor;

        this.cameraActor = new CameraActor(configuration.getCamera());
//        cameraActor.setDebug(DISTANCE_LEFT_RIGHT, DISTANCE_TOP_BOTTOM);
        configuration.getStage().addActor(cameraActor);
    }

    @Override
    public void run(float delta) {
        if (!centeredAtStart) {
            cameraActor.setPosition(actor.getX(), actor.getY());
            centeredAtStart = true;
        }

        if (shouldCameraMove()) {
            Vector2 actorCenterPosition = actor.getCenter();
            MoveToAction action = Actions.moveTo(actorCenterPosition.x, actorCenterPosition.y, MOVEMENT_TIME, Interpolation.pow2);
            cameraActor.addAction(action);
        }
    }

    private boolean shouldCameraMove() {
        Vector2 actorCenter = actor.getCenter();
        Vector2 cameraCenter = cameraActor.getCenter();
        float horizontalInterval = DISTANCE_LEFT_RIGHT / 2f;
        float verticalInterval = DISTANCE_TOP_BOTTOM / 2f;

        return (cameraActor.getActions().size == 0) &&
                (actorCenter.x < cameraCenter.x - horizontalInterval ||
                        actorCenter.x > cameraCenter.x + horizontalInterval ||
                        actorCenter.y < cameraCenter.y - verticalInterval ||
                        actorCenter.y > cameraCenter.y + verticalInterval);
    }
}
