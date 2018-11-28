package com.mantkowicz.light.service.event;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.service.event.type.GameEventType;

public class GameEvent {
    private GameEventType gameEventType;
    private Tile tile;
    private Object eventObject;

    public GameEvent(GameEventType gameEventType, Tile tile) {
        this(gameEventType, tile, null);
    }

    public GameEvent(GameEventType gameEventType, Tile tile, Object eventObject) {
        this.gameEventType = gameEventType;
        this.tile = tile;
        this.eventObject = eventObject;
    }

    GameEventType getGameEventType() {
        return gameEventType;
    }

    public void setGameEventType(GameEventType gameEventType) {
        this.gameEventType = gameEventType;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Object getEventObject() {
        return eventObject;
    }

    public GameEvent setEventObject(Object eventObject) {
        this.eventObject = eventObject;
        return this;
    }
}
