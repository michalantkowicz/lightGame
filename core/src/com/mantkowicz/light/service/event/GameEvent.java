package com.mantkowicz.light.service.event;

public abstract class GameEvent<T> {
    private final GameEventType gameEventType;
    private final T eventObject;

    protected GameEvent(GameEventType gameEventType, T eventObject) {
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
