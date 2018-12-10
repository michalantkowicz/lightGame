package com.mantkowicz.light.board.tile.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.TileTouchedEvent;

public class AccessibleTileClickListener extends ClickListener {
    private final Tile tile;
    private final GameEventService gameEventService;

    public AccessibleTileClickListener(Tile tile, GameEventService gameEventService) {
        this.tile = tile;
        this.gameEventService = gameEventService;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Tile touchedTile = resolveTile(x, y);
        if (touchedTile != null) {
            touchedTile.mark();
        }
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Tile touchedTile = resolveTile(x, y);
        if (touchedTile != null && touchedTile.isMarked()) {
            touchedTile.unmark();
            gameEventService.addEvent(new TileTouchedEvent(touchedTile));
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        if (tile.isMarked()) {
            tile.unmark();
        }
    }

    private Tile resolveTile(float x, float y) {
        Vector2 point = tile.localToStageCoordinates(new Vector2(x, y));
        if (tile.doesContainPoint(point)) {
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