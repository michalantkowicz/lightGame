package com.mantkowicz.light.board.tile;

import com.mantkowicz.light.board.tile.listener.AccessibleTileClickListener;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.service.resources.ResourcesService;

public abstract class AccessibleTile extends Tile {

    public AccessibleTile(ResourcesService resourcesService, String backgroundTextureName) {
        super(resourcesService, backgroundTextureName);
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
