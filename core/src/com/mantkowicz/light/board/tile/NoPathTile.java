package com.mantkowicz.light.board.tile;

import com.mantkowicz.light.service.resources.ResourcesService;

public abstract class NoPathTile extends Tile {

    public NoPathTile(ResourcesService resourcesService, String backgroundTextureName) {
        super(resourcesService, backgroundTextureName);
    }

    @Override
    public boolean isAccessible() {
        return false;
    }
}
