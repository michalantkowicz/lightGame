package com.mantkowicz.light.board.tile.factory;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.TileType;
import com.mantkowicz.light.board.tile.implementation.*;
import com.mantkowicz.light.service.resources.ResourcesService;

public class TileFactory {
    static public Tile createTile(ResourcesService resourcesService, TileType tileType) {
        switch (tileType) {
            case FLOOR:
                return new FloorTile(resourcesService);
            case WALL:
                return new WallTile(resourcesService);
            case TORCH_LIGHT:
                return new TorchLightTile(resourcesService);
            case BORDER_TORCH_LIGHT:
                return new BorderTorchLightTile(resourcesService);
            case PLAYER:
                return new PlayerTile(resourcesService);
            case CHEST:
                return new ChestTile(resourcesService);
            case DOG:
                return new DogTile(resourcesService);
            default:
                throw new IllegalArgumentException("There is no tile implementation for this tile type: " + tileType.name());
        }
    }
}
