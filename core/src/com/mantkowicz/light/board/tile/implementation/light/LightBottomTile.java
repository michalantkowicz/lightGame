package com.mantkowicz.light.board.tile.implementation.light;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.GamePrepareConfiguration;
import com.mantkowicz.light.board.tile.listener.TileClickListener;

public class LightBottomTile extends BorderLightTile {
    public LightBottomTile(AssetManager assetManager) {
        super(assetManager);
        setRotation(180);
    }
}
