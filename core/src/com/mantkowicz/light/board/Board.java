package com.mantkowicz.light.board;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.configuration.api.BoardConfiguration;
import com.mantkowicz.light.map.TileSet;
import com.mantkowicz.light.map.TiledMapLoader;
import com.mantkowicz.light.map.implementation.tmx.TmxTileMapLoaderProperties;
import com.mantkowicz.light.map.implementation.tmx.TmxTiledMapLoader;

import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Board extends Actor {
    private final List<Tile> tiles;

    public Board(BoardConfiguration configuration, TileSet tileSet) {
        this.tiles = tileSet.getTiles();
        setupPositionAndSize(tiles);
        addListener(new BoardClickListener(configuration, this));
    }

    private void setupPositionAndSize(List<Tile> tiles) {
        if (tiles.size() > 0) {
            float minX = tiles.get(0).getX();
            float maxX = tiles.get(0).getX();
            float minY = tiles.get(0).getY();
            float maxY = tiles.get(0).getY();

            for (Tile tile : tiles) {
                minX = min(minX, tile.getX());
                maxX = max(maxX, tile.getX());
                minY = min(minY, tile.getY());
                maxY = max(maxY, tile.getY());
            }

            setPosition(minX, minY);
            setSize(maxX - minX + tiles.get(0).getWidth(), maxY - minY + tiles.get(0).getHeight());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        toBack();
        for (Tile tile : tiles) {
            tile.draw(batch, parentAlpha);
        }
    }

    public static Board load(BoardConfiguration configuration, String mapName) {
        TmxTileMapLoaderProperties properties = new TmxTileMapLoaderProperties().setTileMapFileName(mapName);
        TiledMapLoader<TmxTileMapLoaderProperties> tmxTiledMapLoader = new TmxTiledMapLoader(configuration.getResourcesService());
        return new Board(configuration, tmxTiledMapLoader.loadTiles(properties));
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }
}
