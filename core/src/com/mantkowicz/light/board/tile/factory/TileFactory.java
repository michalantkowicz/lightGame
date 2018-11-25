package com.mantkowicz.light.board.tile.factory;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.TileType;
import com.mantkowicz.light.board.tile.implementation.*;

public class TileFactory {
    static public Tile createTile(AssetManager assetManager, TileType tileType) {
        switch (tileType) {
            case FLOOR:
                return new FloorTile(assetManager);
            case WALL:
                return new WallTile(assetManager);
            case TORCH_LIGHT:
                return new TorchLightTile(assetManager);
            case BORDER_TORCH_LIGHT:
                return new BorderTorchLightTile(assetManager);
            case PLAYER:
                return new PlayerTile(assetManager);
            default:
                throw new IllegalArgumentException("There is no tile implementation for this tile type: " + tileType.name());
        }
    }
}
