package com.mantkowicz.light.board.tile.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mantkowicz.light.board.tile.Tile;

public class TileClickListener extends InputListener {
    private Tile tile;

    public TileClickListener(Tile tile) {
        this.tile = tile;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Vector2 point = new Vector2(x, y);
        point = tile.localToStageCoordinates(point);
        if (tile.doesContainPoint(point)) {
            tile.mark();
        } else {
            for (Tile neighbour : tile.getNeighbours()) {
                if (neighbour.doesContainPoint(point)) {
                    neighbour.mark();
                }
            }
        }
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Vector2 point = new Vector2(x, y);
        point = tile.localToStageCoordinates(point);
        if (tile.doesContainPoint(point)) {
            tile.unmark();
        } else {
            for (Tile neighbour : tile.getNeighbours()) {
                if (neighbour.doesContainPoint(point)) {
                    neighbour.unmark();
                }
            }
        }
    }
}