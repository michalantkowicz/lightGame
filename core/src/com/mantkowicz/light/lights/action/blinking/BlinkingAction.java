package com.mantkowicz.light.lights.action.blinking;

import box2dLight.PointLight;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class BlinkingAction extends SequenceAction {

    public BlinkingAction(PointLight pointLight, BlinkingIntensity intensity, BlinkingSpeed speed) {
        Action singleBlinkAction = getSingleBlinkAction(pointLight, intensity, pointLight.getDistance());
        addAction(singleBlinkAction);
        addAction(Actions.delay(speed.getValue()));
    }

    private Action getSingleBlinkAction(PointLight pointLight, BlinkingIntensity intensity, float baseDistance) {
        return new Action() {
            @Override
            public boolean act(float delta) {
                float randomStart = baseDistance - intensity.getValue();
                float randomEnd = baseDistance + intensity.getValue();
                pointLight.setDistance(MathUtils.random(randomStart, randomEnd));
                return true;
            }
        };
    }
}
