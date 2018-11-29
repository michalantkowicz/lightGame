package com.mantkowicz.light.service.event;

import java.util.HashMap;
import java.util.Map;

public class GameEventCountByType {
    private final Map<GameEventType, Long> gameEventCountByType;

    public GameEventCountByType() {
        gameEventCountByType = new HashMap<>();
        for (GameEventType gameEventType : GameEventType.values()) {
            gameEventCountByType.put(gameEventType, 0L);
        }
    }

    public void increase(GameEventType gameEventType) {
        Long gameEventsCount = gameEventCountByType.get(gameEventType);
        gameEventCountByType.put(gameEventType, gameEventsCount + 1);
    }

    public void decrease(GameEventType gameEventType) {
        Long gameEventsCount = gameEventCountByType.get(gameEventType);
        if (gameEventsCount < 0) {
            gameEventsCount = 0L;
        }
        gameEventCountByType.put(gameEventType, gameEventsCount - 1);
    }

    public boolean containsAny(GameEventType gameEventType) {
        return gameEventCountByType.get(gameEventType) > 0;
    }
}
