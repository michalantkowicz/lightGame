package com.mantkowicz.light.board.tile.factory;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.TileType;
import com.mantkowicz.light.board.tile.implementation.FloorTile;
import com.mantkowicz.light.board.tile.implementation.WallTile;
import com.mantkowicz.light.service.resources.ResourcesService;

public class TileFactory {
    static public Tile createTile(ResourcesService resourcesService, TileType tileType) {
        switch (tileType) {
            case FLOOR:
                return new FloorTile(resourcesService);
            case WALL:
                return new WallTile(resourcesService);
            default:
                throw new IllegalArgumentException("There is no tile implementation for this tile type: " + tileType.name());
        }
    }
}
