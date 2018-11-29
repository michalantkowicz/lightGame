package com.mantkowicz.light.service.event.implementation;

import com.mantkowicz.light.plugin.Collectible;
import com.mantkowicz.light.service.event.GameEvent;

import static com.mantkowicz.light.service.event.GameEventType.COLLECT;

public class CollectEvent extends GameEvent<Collectible> {
    public CollectEvent(Collectible collectible) {
        super(COLLECT, collectible);
    }
}
