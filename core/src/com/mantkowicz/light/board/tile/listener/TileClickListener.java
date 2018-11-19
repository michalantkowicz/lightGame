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
        Tile touchedTile = resolveTile(x, y);
        touchedTile.mark();
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Tile touchedTile = resolveTile(x, y);
        touchedTile.unmark();
    }

    private Tile resolveTile(float x, float y) {
        Vector2 point = tile.localToStageCoordinates(new Vector2(x, y));
        if(tile.doesContainPoint(point)) {
            return tile;
        } else {
            for (Tile neighbour : tile.getNeighbours()) {
                if (neighbour.doesContainPoint(point)) {
                    return neighbour;
                }
            }
        }
        return null;
    }
}