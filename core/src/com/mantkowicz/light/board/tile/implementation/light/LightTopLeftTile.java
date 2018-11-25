package com.mantkowicz.light.board.tile.implementation.light;

import com.badlogic.gdx.assets.AssetManager;

public class LightTopLeftTile extends BorderLightTile {
    public LightTopLeftTile(AssetManager assetManager) {
        super(assetManager);
        setRotation(60);
    }
}
