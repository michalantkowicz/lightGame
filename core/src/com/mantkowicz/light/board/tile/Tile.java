package com.mantkowicz.light.board.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile extends Group {
    protected List<Tile> neighbours;
    protected Image background;

    public Tile(Texture background) {
        this.background = new Image(background);
        addActor(this.background);



        this.neighbours = new ArrayList<>();
    }

    public abstract boolean isAccessible();

    public List<Tile> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Tile neighbour) {
        this.neighbours.add(neighbour);
    }
}
