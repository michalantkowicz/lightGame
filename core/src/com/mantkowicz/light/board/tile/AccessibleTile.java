package com.mantkowicz.light.board.tile;

import com.mantkowicz.light.service.resources.ResourcesService;

public abstract class AccessibleTile extends Tile {

    public AccessibleTile(ResourcesService resourcesService, String backgroundTextureName) {
        super(resourcesService, backgroundTextureName);
    }

    @Override
    public boolean isAccessible() {
        return true;
    }
}
