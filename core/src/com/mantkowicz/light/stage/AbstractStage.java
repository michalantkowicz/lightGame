package com.mantkowicz.light.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class AbstractStage extends Stage {
    public AbstractStage(Viewport viewport) {
        super(viewport);
    }

    public abstract StageType getStageType();
}
