package com.mantkowicz.light.path;

import com.mantkowicz.light.board.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PathService {
    private static PathService instance;

    public static PathService getInstance() {
        if (instance == null) {
            instance = new PathService();
        }
        return instance;
    }

    public static void setTiles(List<Tile> board) {
        PathService instance = getInstance();
        for (Tile tile : board) {
            instance.lengthMap.put(tile, 999999);
        }
    }

    private PathService() {
        reset();
    }

    private HashMap<Tile, Integer> lengthMap = new HashMap<>();
    private Tile startTile;
    private Tile endTile;

    public PathService setStartTile(Tile startTile) {
        this.startTile = startTile;
        return this;
    }

    public PathService setEndTile(Tile endTile) {
        this.endTile = endTile;
        return this;
    }

    public List<Tile> getPath() {
        HashMap<Tile, Tile> prev = new HashMap<>();

        lengthMap.put(startTile, 0);

        List<Tile> Q = new ArrayList<>(lengthMap.keySet());

        while (!Q.isEmpty()) {
            Tile u = getNearest(Q);
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

        return result;
    }

    private Tile getNearest(List<Tile> q) {
        int index = 0;
        for (int i = 0; i < q.size(); i++) {
            if (lengthMap.get(q.get(i)) < lengthMap.get(q.get(index))) {
                index = i;
            }
        }
        return q.remove(index);
    }

    public boolean isStartSet() {
        return this.startTile != null;
    }

    public void reset() {
        this.startTile = null;
        this.endTile = null;
        for (Tile key : lengthMap.keySet()) {
            lengthMap.put(key, 999999);
        }
    }
}
