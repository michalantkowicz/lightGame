package com.mantkowicz.light.map;

import com.mantkowicz.light.board.tile.Tile;

import java.util.List;

public interface TiledMapLoader<T> {
    List<Tile> loadTiles(T properties);
}
