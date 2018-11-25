package com.mantkowicz.light.board.tile.implementation.light;

import com.badlogic.gdx.assets.AssetManager;

public class LightBottomRightTile extends BorderLightTile {
    public LightBottomRightTile(AssetManager assetManager) {
        super(assetManager);
        setRotation(-120);
    }
}
