package com.mantkowicz.light.service.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameEventService {
    private final List<GameEvent> gameEventQueue;
    private final GameEventCountByType gameEventCountByType;

    public GameEventService() {
        gameEventQueue = new ArrayList<>();
        gameEventCountByType = new GameEventCountByType();
    }

    public void addEvent(GameEvent gameEvent) {
        gameEventQueue.add(gameEvent);
        gameEventCountByType.increase(gameEvent.getGameEventType());
    }

    public <T extends GameEvent> T getEvent(GameEventType gameEventType, Class<T> type, boolean removeEventFromQueue) {
        Iterator<GameEvent> iterator = gameEventQueue.iterator();
        while (iterator.hasNext()) {
            GameEvent gameEventItem = iterator.next();
            GameEventType gameEventItemType = gameEventItem.getGameEventType();
            if (gameEventItemType.equals(gameEventType) && type.isInstance(gameEventItem)) {
                if (removeEventFromQueue) {
                    iterator.remove();
                    gameEventCountByType.decrease(gameEventItemType);
                }
                return (T) gameEventItem;
            }
        }
        throw new IllegalArgumentException("No such game event type");
    }

    public boolean containsEvent(GameEventType gameEventType) {
        return gameEventCountByType.containsAny(gameEventType);
    }

    /**
     * this method should be called at the end of main loop of the application to provide housekeeping
     */
    public void updateEventsLifesAndClean() {
        Iterator<GameEvent> iterator = gameEventQueue.iterator();
        while (iterator.hasNext()) {
            GameEvent gameEvent = iterator.next();
            GameEventType gameEventType = gameEvent.getGameEventType();
            gameEvent.increaseAge();
            if (gameEvent.isTooOld()) {
                iterator.remove();
                gameEventCountByType.decrease(gameEventType);
            }
        }
    }
}
