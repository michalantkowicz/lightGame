package com.mantkowicz.light.board.tile.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.service.event.GameEvent;
import com.mantkowicz.light.service.event.GameEventService;

import static com.mantkowicz.light.service.event.type.GameEventType.TILE_TOUCHED;

public class TileClickListener extends InputListener {
    private final Tile tile;
    private final GameEventService gameEventService;

    public TileClickListener(Tile tile, GameEventService gameEventService) {
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
        if (touchedTile != null) {
            touchedTile.unmark();
            gameEventService.addEvent(new GameEvent(TILE_TOUCHED, touchedTile));
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