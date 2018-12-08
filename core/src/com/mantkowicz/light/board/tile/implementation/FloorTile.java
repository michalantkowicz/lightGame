package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.AccessibleTile;

public class FloorTile extends AccessibleTile {
    public FloorTile(AssetManager assetManager) {
        super(assetManager.get("floor-tile.png"));
    }
}
