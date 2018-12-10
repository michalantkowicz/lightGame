package com.mantkowicz.light.board.path;

import com.mantkowicz.light.board.tile.Tile;

import java.util.List;

public class BoardPath {
    private List<Tile> path;

    public BoardPath(List<Tile> path) {
        this.path = path;
    }

    public List<Tile> getPathNodes() {
        return path;
    }

    public void setPath(List<Tile> path) {
        this.path = path;
    }

    public boolean isEmpty() {
        return path == null || path.isEmpty();
    }

    public boolean exists() {
        return !(path == null || path.isEmpty());
    }

    public Tile getLastNode() {
        if (path != null && !path.isEmpty()) {
            return path.get(path.size() - 1);
        }
        return null;
    }
}
