package com.mantkowicz.light.map;

public interface TiledMapLoader<T> {
    TileSet loadTiles(T properties);
}
