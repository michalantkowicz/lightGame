package com.mantkowicz.light.board.tile;

import com.badlogic.gdx.graphics.Texture;
import com.mantkowicz.light.board.tile.listener.NoPathTileClickListener;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

public abstract class NoPathTile extends Tile {

    public NoPathTile(Texture background) {
        super(background);
    }

    @Override
    public boolean isAccessible() {
        return false;
    }

    @Override
    public void prepare(GamePrepareConfiguration configuration) {
        NoPathTileClickListener listener = new NoPathTileClickListener(this, configuration.getNotificationStage(), configuration.getPhraseService());
        addListener(listener);
    }
}
