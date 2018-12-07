package com.mantkowicz.light.actor.implementation;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mantkowicz.light.actor.GameActor;

import static com.mantkowicz.light.actor.GameActorType.CAMERA_ACTOR;

public class CameraActor extends GameActor {
    private Camera camera;

    public CameraActor(Camera camera) {
        super(CAMERA_ACTOR);
        this.camera = camera;

        setTouchable(Touchable.disabled);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        camera.position.set(getX(), getY(), camera.position.z);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        camera.position.set(getX(), getY(), camera.position.z);
    }

    public void setDebug(float distanceLeftRight, float distanceTopBottom) {
        Actor debugActor = new Actor();
        addActor(debugActor);
        debugActor.setSize(distanceLeftRight, distanceTopBottom);
        debugActor.setPosition(debugActor.getX() - debugActor.getWidth() / 2f, debugActor.getY() - debugActor.getHeight() / 2f);
        debugActor.setTouchable(Touchable.disabled);
        debugActor.debug();
    }
}
