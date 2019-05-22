package com.mantkowicz.light.service.event;

import java.util.*;

public class GameEventService {
    private final Map<GameEventType, List<GameEvent>> queue = new HashMap<>();

    public void addEvent(GameEvent gameEvent) {
        createIfDoesNotExist(gameEvent.getGameEventType());
        queue.get(gameEvent.getGameEventType()).add(gameEvent);
    }

    @SuppressWarnings("unchecked")
    public <T extends GameEvent> T getEvent(GameEventType eventType, Class<T> type) {
        createIfDoesNotExist(eventType);
        if (containsEvent(eventType)) {
            List<GameEvent> eventList = queue.get(eventType);
            for (int i = 0; i < eventList.size(); i++) {
                if (type.isInstance(eventList.get(i))) {
                    return (T) eventList.get(i);
                }
            }
        }
        throw new IllegalArgumentException("No such game event type");
    }

    @SuppressWarnings("unchecked")
    public <T extends GameEvent> T removeEventFromQueue(GameEventType eventType, Class<T> type) {
        createIfDoesNotExist(eventType);
        if (containsEvent(eventType)) {
            List<GameEvent> eventList = queue.get(eventType);
            for (int i = 0; i < eventList.size(); i++) {
                if (type.isInstance(eventList.get(i))) {
                    return (T) eventList.remove(i);
                }
            }
        }
        throw new IllegalArgumentException("No such game event type");
    }

    public boolean containsEvent(GameEventType eventType) {
        createIfDoesNotExist(eventType);
        return queue.containsKey(eventType) && !queue.get(eventType).isEmpty();
    }

    private void createIfDoesNotExist(GameEventType eventType) {
        if (!queue.containsKey(eventType)) {
            queue.put(eventType, new ArrayList<>());
        }
    }

    /**
     * this method should be called at the end of main loop of the application to provide housekeeping
     */
    public void updateEventsLivesAndClean() {
        for (GameEventType eventType : queue.keySet()) {
            if (queue.get(eventType) != null) {
                Iterator<GameEvent> iterator = queue.get(eventType).iterator();
                while (iterator.hasNext()) {
                    GameEvent gameEvent = iterator.next();
                    gameEvent.increaseAge();
                    if (gameEvent.isTooOld()) {
                        iterator.remove();
                    }
                }
            }
        }
    }
}
