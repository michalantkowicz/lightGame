package com.mantkowicz.light.board.object;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

public abstract class TileObject {
    private TileObjectType objectType;

    public TileObject(TileObjectType objectType) {
        this.objectType = objectType;
    }

    public abstract Actor prepare(GamePrepareConfiguration configuration);

    public abstract boolean isAccessible();
}
