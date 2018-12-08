package com.mantkowicz.light.board.tile;

import com.badlogic.gdx.graphics.Texture;
import com.mantkowicz.light.board.tile.listener.AccessibleTileClickListener;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

public abstract class AccessibleTile extends Tile {

    protected AccessibleTile(Texture background) {
        super(background);
    }

    @Override
    public boolean isAccessible() {
        return true;
    }

    @Override
    public void prepare(GamePrepareConfiguration configuration) {
        AccessibleTileClickListener listener = new AccessibleTileClickListener(this, configuration.getGameEventService());
        addListener(listener);
    }
}
