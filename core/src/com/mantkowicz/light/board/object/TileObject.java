package com.mantkowicz.light.board.object;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

public abstract class TileObject {
    private TileObjectType objectType;

    public TileObject(TileObjectType objectType) {
        this.objectType = objectType;
    }

    public abstract void prepare(Tile tile, GamePrepareConfiguration configuration);

    public abstract boolean isAccessible();
}
