package com.mantkowicz.light.board.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile extends Group {
    private static final float TAN_30_DIV_6 = 0.289f;

    protected Long id;
    protected List<Tile> neighbours;

    public Array<Vector2> getPolygon() {
        return polygon;
    }

    protected Array<Vector2> polygon;
    protected Image background;

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);

        polygon = new Array<>();
        polygon.add(new Vector2(getX() + (TAN_30_DIV_6 * getHeight()), getY()));
        polygon.add(new Vector2(getX() + getWidth() - (TAN_30_DIV_6 * getHeight()), getY()));
        polygon.add(new Vector2(getX() + getWidth(), getY() + (0.5f * getHeight())));
        polygon.add(new Vector2(getX() + getWidth() - (TAN_30_DIV_6 * getHeight()), getY() + getHeight()));
        polygon.add(new Vector2(getX() + (TAN_30_DIV_6 * getHeight()), getY() + getHeight()));
        polygon.add(new Vector2(getX(), getY() + (0.5f * getHeight())));
    }

    public Tile(Long id, Texture background) {
        this.id = id;
        this.neighbours = new ArrayList<>();

        this.background = new Image(background);




        addActor(this.background);
//        this.background.debug();
        this.setSize(this.background.getWidth(), this.background.getHeight());
        this.debug();

        this.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 point = new Vector2(x, y);
                point = localToStageCoordinates(point);
                if(Intersector.isPointInPolygon(polygon, point)) {
                    setBlue();
                } else {
                    if(neighbours.isEmpty()) System.out.println("Neighbours are empty!");
                    for (Tile neighbour : neighbours) {
                        if(Intersector.isPointInPolygon(neighbour.getPolygon(), point)) {
                            neighbour.setBlue();
                        }
                    }
                }
                return true;
//                return super.touchDown(event, x, y, pointer, button);
            }
        });

//        this.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("I'm clicked!");
//            }
//        });
    }

    public void setBlue() {
        this.background.setColor(Color.BLUE);
    }

    public void setBrown() {
        this.background.setColor(Color.BROWN);
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
