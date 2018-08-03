package com.mantkowicz.light.board.tile.factory;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.TileType;
import com.mantkowicz.light.board.tile.implementation.*;

public class TileFactory {
    static public Tile createTile(Long id, AssetManager assetManager, TileType tileType) {
        switch (tileType) {
            case FLOOR:
                return new FloorTile(id, assetManager);
            case WALL:
                return new WallTile(id, assetManager);
            case RED:
                return new RedTile(id, assetManager);
            case PINK:
                return new PinkTile(id, assetManager);
            case BROWN:
                return new BrownTile(id, assetManager);
            case GREEN:
                return new GreenTile(id, assetManager);
            case GREY:
                return new GreyTile(id, assetManager);
            default:
                throw new IllegalArgumentException("There is no tile implementation for this tile type: " + tileType.name());
        }
    }
}
