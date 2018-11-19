package com.mantkowicz.light.board.service;

import com.mantkowicz.light.board.Board;
import com.mantkowicz.light.board.path.BoardPath;
import com.mantkowicz.light.board.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
    private Board board;

    public BoardService(Board board) {
        this.board = board;
    }

    public BoardPath getPath(Tile startTile, Tile endTile) {
        HashMap<Tile, Integer> lengthMap = new HashMap<>();
        for (Tile tile : board.getTiles()) {
            if(tile.isAccessible()) {
                lengthMap.put(tile, Integer.MAX_VALUE);
            }
        }

        HashMap<Tile, Tile> prev = new HashMap<>();

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
