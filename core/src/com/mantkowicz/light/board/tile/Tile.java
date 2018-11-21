package com.mantkowicz.light.board.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile extends Group {
    private static final float TAN_30_DIV_6 = 0.289f;
    private static int ID_SEQUENCE = 1;

    private List<TileAttribute> tileAttributes;

    private Color backgroundColor;
    private int id;
    private List<Tile> neighbours;

    public Vector2[] getPolygonVertices() {
        Vector2[] result = new Vector2[polygon.size];
        for (int i = 0; i < polygon.size; i++) {
            result[i] = polygon.get(i);
        }
        return result;
    }

    private Array<Vector2> polygon;
    private Image background;

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

    public Tile(Texture background) {
        this.id = ID_SEQUENCE++;
        this.neighbours = new ArrayList<>();

        this.background = new Image(background);
        this.backgroundColor = new Color(this.background.getColor());

        addActor(this.background);
        this.setSize(this.background.getWidth(), this.background.getHeight());
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

    private Image getBackground() {
        return background;
    }

    private Integer getId() {
        return id;
    }

    public Vector2 getCenter() {
        return new Vector2(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;

        Tile tile = (Tile) o;

        if (!getId().equals(tile.getId())) return false;
        if (tileAttributes != null ? !tileAttributes.equals(tile.tileAttributes) : tile.tileAttributes != null)
            return false;
        if (backgroundColor != null ? !backgroundColor.equals(tile.backgroundColor) : tile.backgroundColor != null)
            return false;
        if (getNeighbours() != null ? !getNeighbours().equals(tile.getNeighbours()) : tile.getNeighbours() != null)
            return false;
        if (polygon != null ? !polygon.equals(tile.polygon) : tile.polygon != null) return false;
        return getBackground() != null ? getBackground().equals(tile.getBackground()) : tile.getBackground() == null;
    }

    @Override
    public int hashCode() {
        int result = tileAttributes != null ? tileAttributes.hashCode() : 0;
        result = 31 * result + (backgroundColor != null ? backgroundColor.hashCode() : 0);
        result = 31 * result + getId();
        result = 31 * result + (polygon != null ? polygon.hashCode() : 0);
        result = 31 * result + (getBackground() != null ? getBackground().hashCode() : 0);
        return result;
    }

    public boolean isNeighbour(Tile tile) {
        for (Tile neighbour : neighbours) {
            if (neighbour.equals(tile)) {
                return true;
            }
        }
        return false;
    }

    public Vector2 getLeftBottom() {
        return new Vector2(getX(), getY());
    }
}
