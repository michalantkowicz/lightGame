package com.mantkowicz.light.board.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile extends Group {
    protected Long id;
    protected List<Tile> neighbours;
    protected Image background;

    public Tile(Long id, Texture background) {
        this.id = id;
        this.neighbours = new ArrayList<>();

        this.background = new Image(background);
        addActor(this.background);
    }

    public abstract boolean isAccessible();

    public List<Tile> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Tile neighbour) {
        this.neighbours.add(neighbour);
    }

    public Image getBackground() {
        return background;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return getId().equals(tile.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
