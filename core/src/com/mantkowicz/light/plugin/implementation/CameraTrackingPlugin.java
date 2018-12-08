package com.mantkowicz.light.plugin.implementation;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.actor.implementation.CameraActor;
import com.mantkowicz.light.configuration.api.CameraTrackingPluginConfiguration;
import com.mantkowicz.light.plugin.Plugin;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.LookAtMeEvent;

import static com.mantkowicz.light.service.event.GameEventType.LOOK_AT_ME;

public class CameraTrackingPlugin implements Plugin {
    private static final float DISTANCE_LEFT_RIGHT = 450;
    private static final float DISTANCE_TOP_BOTTOM = 450;

    private GameActor actor;
    private CameraActor cameraActor;
    private boolean centeredAtStart = false;
    private Vector2 interval = new Vector2();
    private GameEventService gameEventService;

    public CameraTrackingPlugin(GameActor actor, CameraTrackingPluginConfiguration configuration) {
        this.actor = actor;
        this.gameEventService = configuration.getGameEventService();

        this.cameraActor = new CameraActor(configuration.getCamera());
        cameraActor.setDebug(DISTANCE_LEFT_RIGHT, DISTANCE_TOP_BOTTOM);
        configuration.getStage().addActor(cameraActor);
    }

    @Override
    public void run(float delta) {
        if (!centeredAtStart) { // TODO: move to another plugin that will remove itself after once run
            cameraActor.setPosition(actor.getX(), actor.getY());
            centeredAtStart = true;
        }

        if (gameEventService.containsEvent(LOOK_AT_ME) || cameraActor.getActions().size > 0) {
            if (cameraActor.getActions().size == 0) {
                LookAtMeEvent event = gameEventService.getEvent(LOOK_AT_ME, LookAtMeEvent.class, true);
                Vector2 targetCenter = event.getEventObject().getCenter();
                MoveToAction moveToTargetAction = Actions.moveTo(targetCenter.x, targetCenter.y, 1f, Interpolation.pow2Out);
                cameraActor.addAction(moveToTargetAction);
            }
        } else {
            Vector2 actorCenter = actor.getCenter();

            if (shouldCameraMove(actorCenter)) {
                interval = actorCenter.sub(cameraActor.getCenter()).scl(0.02f);
                cameraActor.moveBy(interval.x, interval.y);
            } else {
                if (interval.len2() > 1f) {
                    cameraActor.moveBy(interval.x, interval.y);
                    interval = interval.scl(0.96f);
                }
            }
        }
    }

    private boolean shouldCameraMove(Vector2 actorCenter) {
        Vector2 cameraCenter = cameraActor.getCenter();
        float horizontalInterval = DISTANCE_LEFT_RIGHT / 2f;
        float verticalInterval = DISTANCE_TOP_BOTTOM / 2f;

        return (actorCenter.x < cameraCenter.x - horizontalInterval ||
                actorCenter.x > cameraCenter.x + horizontalInterval ||
                actorCenter.y < cameraCenter.y - verticalInterval ||
                actorCenter.y > cameraCenter.y + verticalInterval);
    }
}
