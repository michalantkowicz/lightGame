package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.mantkowicz.light.board.tile.Tile;

public class BrownTile extends Tile {
    public BrownTile(Long id, AssetManager assetManager) {
        super(id, assetManager.get("brown.png", Texture.class));
    }

    @Override
    public boolean isAccessible() {
        return true;
    }
}
