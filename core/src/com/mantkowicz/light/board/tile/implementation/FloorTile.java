package com.mantkowicz.light.board.tile.implementation;

import com.mantkowicz.light.board.tile.AccessibleTile;
import com.mantkowicz.light.service.resources.ResourcesService;

public class FloorTile extends AccessibleTile {
    public FloorTile(ResourcesService resourcesService) {
        super(resourcesService, "floor-tile.png");
    }
}
