package com.mantkowicz.light.board.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.mantkowicz.light.board.tile.listener.TileClickListener;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile extends Group {
    private static final float TAN_30_DIV_6 = 0.289f;
    private Color backgroundColor;
    protected Long id;
    protected List<Tile> neighbours;
    protected Array<Vector2> polygon;
    protected Image background;

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        initTilePolygon();
    }

    private void initTilePolygon() {
        polygon = new Array<>();
        float cornerOffset = TAN_30_DIV_6 * getHeight();
        float halfOfHeight = 0.5f * getHeight();
        polygon.add(new Vector2(getX() + cornerOffset, getY()));
        polygon.add(new Vector2(getX() + getWidth() - cornerOffset, getY()));
        polygon.add(new Vector2(getX() + getWidth(), getY() + halfOfHeight));
        polygon.add(new Vector2(getX() + getWidth() - cornerOffset, getY() + getHeight()));
        polygon.add(new Vector2(getX() + cornerOffset, getY() + getHeight()));
        polygon.add(new Vector2(getX(), getY() + halfOfHeight));
    }

    public boolean doesContainPoint(Vector2 point) {
        return Intersector.isPointInPolygon(this.polygon, point);
    }

    public Tile(Long id, Texture background) {
        this.id = id;
        this.neighbours = new ArrayList<>();

        this.background = new Image(background);
        this.backgroundColor = this.background.getColor();

        addActor(this.background);
        this.setSize(this.background.getWidth(), this.background.getHeight());

        this.addListener(new TileClickListener(this));
    }

    public void mark() {
        this.background.setColor(Color.BLUE);
    }

    public void unmark() {
        this.background.setColor(backgroundColor);
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
