package com.mantkowicz.light.service.resources;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TargetMarkerImage extends Image {
    Action action;

    public TargetMarkerImage(TextureAtlas.AtlasRegion region) {
        super(region);
    }

    @Override
    public void addAction(Action action) {
        this.action = action;
        clearActions();
        super.addAction(action);
    }

    public void resetAction() {
        if (action != null) {
            action.restart();
        }
    }
}
