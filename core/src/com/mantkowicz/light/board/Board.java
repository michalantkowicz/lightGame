package com.mantkowicz.light.board;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.map.TiledMapLoader;
import com.mantkowicz.light.map.implementation.tmx.TmxTileMapLoaderProperties;
import com.mantkowicz.light.map.implementation.tmx.TmxTiledMapLoader;
import com.mantkowicz.light.service.resources.ResourcesService;

import java.util.ArrayList;
import java.util.List;

public class Board extends Actor {
    private List<Tile> tiles = new ArrayList<>();

    private <T> List<Tile> loadTiles(TiledMapLoader<T> tiledMapLoader, T properties) {
        tiles = tiledMapLoader.loadTiles(properties);
        return tiles;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public static Board load(ResourcesService resourcesService, String mapName) {
        TmxTileMapLoaderProperties properties = new TmxTileMapLoaderProperties().setTileMapFileName("map2_outer.tmx");
        TiledMapLoader<TmxTileMapLoaderProperties> tmxTiledMapLoader = new TmxTiledMapLoader(resourcesService);

        Board board = new Board();
        board.loadTiles(tmxTiledMapLoader, properties);
        return board;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        toBack();
        for (Tile tile : tiles) {
            tile.draw(batch, parentAlpha);
        }
    }
}
