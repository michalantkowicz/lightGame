package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.Tile;

public class RedTile extends Tile {
    public RedTile(AssetManager assetManager) {
        super(assetManager.get("red.png"));
    }

    @Override
    public boolean isAccessible() {
        return true;
    }
}
