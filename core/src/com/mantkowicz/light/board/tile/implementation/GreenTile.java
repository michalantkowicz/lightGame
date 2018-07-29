package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.Tile;

public class GreenTile extends Tile {
    public GreenTile(AssetManager assetManager) {
        super(assetManager.get("green.png"));
    }

    @Override
    public boolean isAccessible() {
        return true;
    }
}
