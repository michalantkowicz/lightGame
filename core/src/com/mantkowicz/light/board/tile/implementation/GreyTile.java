package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.Tile;

public class GreyTile extends Tile {
    public GreyTile(Long id, AssetManager assetManager) {
        super(id, assetManager.get("wall-tile.png"));
    }

    @Override
    public boolean isAccessible() {
        return false;
    }
}
