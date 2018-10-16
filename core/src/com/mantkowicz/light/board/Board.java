package com.mantkowicz.light.board;

import com.badlogic.gdx.math.Vector2;
import com.mantkowicz.light.board.path.BoardPath;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.map.TiledMapLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    public List<Tile> tiles = new ArrayList<>();

    public <T> List<Tile> loadTiles(TiledMapLoader<T> tiledMapLoader, T properties) {
        tiles = tiledMapLoader.getTiles(properties);
        return tiles;
    }

    public Tile getTileAt(Vector2 position) {
        throw new UnsupportedOperationException("This operation is not implemented yet");
    }

    public BoardPath getPath(Vector2 startPosition, Vector2 endPosition) {
        HashMap<Tile, Integer> lengthMap = new HashMap<>();
        for (Tile tile : tiles) {
            lengthMap.put(tile, 999999);
        }

        HashMap<Tile, Tile> prev = new HashMap<>();

        Tile startTile = getTileAt(startPosition);
        Tile endTile = getTileAt(endPosition);

        lengthMap.put(startTile, 0);

        List<Tile> Q = new ArrayList<>(lengthMap.keySet());

        while (!Q.isEmpty()) {
            Tile u = getNearest(lengthMap, Q);
            for (Tile v : u.getNeighbours()) {
                if (lengthMap.get(u) + 1 < lengthMap.get(v)) {
                    lengthMap.put(v, lengthMap.get(u) + 1);
                    prev.put(v, u);
                }
            }
        }

        List<Tile> result = new ArrayList<>();

        if (lengthMap.get(endTile) < Integer.MAX_VALUE) {
            while (prev.get(endTile) != null) {
                result.add(endTile);
                endTile = prev.get(endTile);
            }
        }

        return new BoardPath(result);
    }

    private Tile getNearest(Map<Tile, Integer> lengthMap, List<Tile> q) {
        int index = 0;
        for (int i = 0; i < q.size(); i++) {
            if (lengthMap.get(q.get(i)) < lengthMap.get(q.get(index))) {
                index = i;
            }
        }
        return q.remove(index);
    }
}
