package com.mantkowicz.light.service.event;

import com.mantkowicz.light.service.event.type.GameEventCountByType;
import com.mantkowicz.light.service.event.type.GameEventType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameEventService {
    private List<GameEvent> gameEventQueue;
    private GameEventCountByType gameEventCountByType;

    public GameEventService() {
        gameEventQueue = new ArrayList<>();
        gameEventCountByType = new GameEventCountByType();
    }

    public void addEvent(GameEvent gameEvent) {
        gameEventQueue.add(gameEvent);
        gameEventCountByType.increase(gameEvent.getGameEventType());
    }

    public GameEvent getEvent(GameEventType gameEventType, boolean removeEventFromQueue) {
        Iterator<GameEvent> iterator = gameEventQueue.iterator();
        while (iterator.hasNext()) {
            GameEvent gameEventItem = iterator.next();
            GameEventType gameEventItemType = gameEventItem.getGameEventType();
            if (gameEventItemType.equals(gameEventType)) {
                if(removeEventFromQueue) {
                    iterator.remove();
                    gameEventCountByType.decrease(gameEventItemType);
                }
                return gameEventItem;
            }
        }
        throw new IllegalArgumentException("No such game event type");
    }

    public boolean containsEvent(GameEventType gameEventType) {
        return gameEventCountByType.containsAny(gameEventType);
    }
}
