package com.mantkowicz.light.map.implementation.tmx;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.TileType;
import com.mantkowicz.light.board.tile.factory.TileFactory;
import com.mantkowicz.light.map.TiledMapLoader;
import com.mantkowicz.light.util.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TmxTiledMapLoader implements TiledMapLoader<TmxTileMapLoaderProperties> {
    private AssetManager assetManager;
    private Map<Tuple<Integer, Integer>, Tile> tilesMap = new HashMap<>();

    public TmxTiledMapLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public List<Tile> getTiles(TmxTileMapLoaderProperties properties) {
        TiledMap tiledMap = new TmxMapLoader().load(properties.getTileMapFileName());

        int layerWidth = getTileLayer(tiledMap).getWidth();
        int layerHeight = getTileLayer(tiledMap).getHeight();

        //create tiles
        for (int x = 0; x < layerWidth; x++) {
            for (int y = 0; y < layerHeight; y++) {
                Tuple<Integer, Integer> cellPosition = Tuple.of(x, y);

                Tile tile = createNewTile(tiledMap, cellPosition);
                tilesMap.put(cellPosition, tile);
            }
        }

        //collect neighbours
        for (int x = 0; x < layerWidth; x++) {
            for (int y = 0; y < layerHeight; y++) {
                Tuple<Integer, Integer> cellPosition = Tuple.of(x, y);

                if (tilesMap.containsKey(cellPosition)) {
                    Tile tile = tilesMap.get(cellPosition);

                    Integer tileX = cellPosition.getX();
                    Integer tileY = cellPosition.getY();

                    addNeighbour(tile, Tuple.of(tileX - 1, tileY - (tileX % 2)));
                    addNeighbour(tile, Tuple.of(tileX + 1, tileY - (tileX % 2)));
                    addNeighbour(tile, Tuple.of(tileX, tileY - 1));
                    addNeighbour(tile, Tuple.of(tileX, tileY + 1));
                    addNeighbour(tile, Tuple.of(tileX - 1, tileY + 1 - (tileX % 2)));
                    addNeighbour(tile, Tuple.of(tileX + 1, tileY + 1 - (tileX % 2)));
                }
            }
        }

        tiledMap.dispose();

        return new ArrayList<>(tilesMap.values());
    }

    private void addNeighbour(Tile tile, Tuple<Integer, Integer> neighbourPosition) {
        if (tilesMap.containsKey(neighbourPosition)) {
            Tile neighbour = tilesMap.get(neighbourPosition);
            if (tile.isAccessible() && neighbour.isAccessible()) {
                if (!tile.getNeighbours().contains(neighbour)) {
                    tile.addNeighbour(neighbour);
                }
            }
        }
    }

    private Tile createNewTile(TiledMap tiledMap, Tuple<Integer, Integer> cellPosition) {
        TiledMapTileLayer tileLayer = getTileLayer(tiledMap);
        TiledMapTileSet tileSet = getTileSet(tiledMap);

        Integer x = cellPosition.getX();
        Integer y = cellPosition.getY();

        float tileHeight = tileLayer.getTileHeight();
        float tileWidth = Math.round((Math.sqrt(3) * tileHeight) / 2f);

        TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);

        if (cell != null) {
            int tileDefinitionId = cell.getTile().getId();
            Tile tile = createTile(tileSet, tileDefinitionId);
            Vector2 position = new Vector2(x * tileWidth, (y - (x % 2) / 2f) * tileHeight);
            position.add(new Vector2(50, 50));
            tile.setPosition(position.x, position.y);
            return tile;
        }
        return null;
    }

    private TiledMapTileLayer getTileLayer(TiledMap tiledMap) {
        return tiledMap
                .getLayers()
                .getByType(TiledMapTileLayer.class)
                .first();
    }

    private TiledMapTileSet getTileSet(TiledMap tiledMap) {
        return tiledMap
                .getTileSets()
                .getTileSet(0);
    }

    private Tile createTile(TiledMapTileSet tileSet, int tileDefinitionId) {
        TileType type = TileType.valueOf(tileSet.getTile(tileDefinitionId).getProperties().get("tileClass", String.class));
        return TileFactory.createTile(assetManager, type);
    }
}
