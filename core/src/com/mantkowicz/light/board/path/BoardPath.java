package com.mantkowicz.light.board.path;

import com.mantkowicz.light.board.tile.Tile;

import java.util.List;

public class BoardPath {
    List<Tile> path;

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
}
