package com.mantkowicz.light.board.tile.factory;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.TileType;
import com.mantkowicz.light.board.tile.implementation.*;
import com.mantkowicz.light.board.tile.implementation.light.*;

public class TileFactory {
    static public Tile createTile(AssetManager assetManager, TileType tileType) {
        switch (tileType) {
            case FLOOR:
                return new FloorTile(assetManager);
            case WALL:
                return new WallTile(assetManager);
            case LIGHT:
                return new LightTile(assetManager);
            case LIGHT_TOP:
                return new LightTopTile(assetManager);
            case LIGHT_TOP_RIGHT:
                return new LightTopRightTile(assetManager);
            case LIGHT_TOP_LEFT:
                return new LightTopLeftTile(assetManager);
            case LIGHT_BOTTOM:
                return new LightBottomTile(assetManager);
            case LIGHT_BOTTOM_RIGHT:
                return new LightBottomRightTile(assetManager);
            case LIGHT_BOTTOM_LEFT:
                return new LightBottomLeftTile(assetManager);
            case PLAYER:
                return new PlayerTile(assetManager);
            default:
                throw new IllegalArgumentException("There is no tile implementation for this tile type: " + tileType.name());
        }
    }
}
