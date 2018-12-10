package com.mantkowicz.light.board.tile;

import com.mantkowicz.light.board.tile.listener.NoPathTileClickListener;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.service.resources.ResourcesService;

public abstract class NoPathTile extends Tile {

    public NoPathTile(ResourcesService resourcesService, String backgroundTextureName) {
        super(resourcesService, backgroundTextureName);
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
