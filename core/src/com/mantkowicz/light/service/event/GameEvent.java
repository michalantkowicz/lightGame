package com.mantkowicz.light.service.event;

public abstract class GameEvent<T> {
    private GameEventType gameEventType;
    private T eventObject;

    public GameEvent(GameEventType gameEventType, T eventObject) {
        this.gameEventType = gameEventType;
        this.eventObject = eventObject;
    }

    public GameEventType getGameEventType() {
        return gameEventType;
    }

    public T getEventObject() {
        return eventObject;
    }
}
