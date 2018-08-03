package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.Tile;

public class PinkTile extends Tile {
    public PinkTile(Long id, AssetManager assetManager) {
        super(id, assetManager.get("pink.png"));
    }

    @Override
    public boolean isAccessible() {
        return true;
    }
}
