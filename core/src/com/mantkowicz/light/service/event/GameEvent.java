package com.mantkowicz.light.service.event;

public abstract class GameEvent<T> {
    private static final int IMMORTAL = Integer.MIN_VALUE;

    private final GameEventType gameEventType;
    private final T eventObject;

    private int age;
    private int maxAge;

    /**
     * @param maxAge how much loops event lives, must be greater or equal 0
     * @throws IllegalArgumentException if maxAge < 0
     */
    protected GameEvent(GameEventType gameEventType, T eventObject, int maxAge) {
        this.gameEventType = gameEventType;
        this.eventObject = eventObject;
        if (maxAge != IMMORTAL && maxAge < 0) {
            throw new IllegalArgumentException("The maxAge must be at least 0");
        }
        this.maxAge = maxAge;
        this.age = 0;
    }

    /**
     * creates immortal game event (will be deleted on demand)
     */
    protected GameEvent(GameEventType gameEventType, T eventObject) {
        this(gameEventType, eventObject, IMMORTAL);
    }

    public GameEventType getGameEventType() {
        return gameEventType;
    }

    public T getEventObject() {
        return eventObject;
    }

    public void increaseAge() {
        age++;
    }

    public boolean isTooOld() {
        if (maxAge != IMMORTAL && age >= maxAge) {
            return true;
        }
        return false;
    }
}
