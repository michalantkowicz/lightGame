package com.mantkowicz.light.board.tile;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mantkowicz.light.path.PathService;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile extends Group {
    protected Long id;
    protected List<Tile> neighbours;
    protected Image background;

    public Tile(Long id, Texture background) {
        this.id = id;
        this.background = new Image(background);
        addActor(this.background);

        this.neighbours = new ArrayList<>();

        if (isAccessible()) {
            this.addListener(createLeftClickListener());
            this.addListener(createRightClickListener());
        }

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        Label label = new Label(this.getId().toString(), style);
        label.setPosition(30, 20);

        addActor(label);

    }

    private EventListener createRightClickListener() {
        return new ClickListener(Input.Buttons.RIGHT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PathService.getInstance().setStartTile(Tile.this);
                Tile.this.getBackground().setColor(0.6f, 0.8f, 0.6f, 1f);
                super.clicked(event, x, y);
            }
        };
    }

    private ClickListener createLeftClickListener() {
        return new ClickListener(Input.Buttons.LEFT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PathService.getInstance().setEndTile(Tile.this);
                List<Tile> path = PathService.getInstance().getPath();
                for (Tile tile : path) {
                    tile.getBackground().setColor(0.6f, 0.6f, 0.8f, 1f);
                }
                PathService.getInstance().reset();
                super.clicked(event, x, y);
            }
        };
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
