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
            case RED:
                return new RedTile(assetManager);
            case PINK:
                return new PinkTile(assetManager);
            case BROWN:
                return new BrownTile(assetManager);
            case GREEN:
                return new GreenTile(assetManager);
            case GREY:
                return new GreyTile(assetManager);
            default:
                throw new IllegalArgumentException("There is no tile implementation for this tile type: " + tileType.name());
        }
    }
}
