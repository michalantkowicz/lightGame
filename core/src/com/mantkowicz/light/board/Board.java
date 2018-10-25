package com.mantkowicz.light.board;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.map.TiledMapLoader;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<Tile> tiles = new ArrayList<>();

    public <T> List<Tile> loadTiles(TiledMapLoader<T> tiledMapLoader, T properties) {
        tiles = tiledMapLoader.getTiles(properties);
        return tiles;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
