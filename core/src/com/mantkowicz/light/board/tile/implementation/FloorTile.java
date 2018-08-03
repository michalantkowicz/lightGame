package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.Tile;

public class FloorTile extends Tile {
    public FloorTile(Long id, AssetManager assetManager) {
        super(id, assetManager.get("floor-tile.png"));
    }

    @Override
    public boolean isAccessible() {
        return true;
    }
}
