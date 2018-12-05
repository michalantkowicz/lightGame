package com.mantkowicz.light.actor.implementation;

import com.badlogic.gdx.graphics.Camera;
import com.mantkowicz.light.actor.GameActor;

import static com.mantkowicz.light.actor.GameActorType.CAMERA_ACTOR;

public class CameraActor extends GameActor {
    private Camera camera;

    public CameraActor(Camera camera) {
        super(CAMERA_ACTOR);
        this.camera = camera;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        camera.position.set(getX(), getY(), camera.position.z);
    }

    @Override
    public void clearActions() {
        super.clearActions();
    }
}
