package com.mantkowicz.light.board.tile.implementation.light;

import com.badlogic.gdx.assets.AssetManager;

public class LightTopRightTile extends BorderLightTile {
    public LightTopRightTile(AssetManager assetManager) {
        super(assetManager);
         setRotation(-60);
    }
}
