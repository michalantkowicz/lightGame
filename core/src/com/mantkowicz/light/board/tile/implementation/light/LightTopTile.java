package com.mantkowicz.light.board.tile.implementation.light;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.GamePrepareConfiguration;
import com.mantkowicz.light.board.tile.listener.TileClickListener;

public class LightTopTile extends BorderLightTile {
    public LightTopTile(AssetManager assetManager) {
        super(assetManager);
        setRotation(0);
    }
}
