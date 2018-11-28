package com.mantkowicz.light.actor.plugin.implementation;

import com.mantkowicz.light.actor.implementation.collectable.Chest;
import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.actor.plugin.Plugin;
import com.mantkowicz.light.configuration.CollectPluginConfiguration;
import com.mantkowicz.light.service.event.GameEvent;
import com.mantkowicz.light.service.event.GameEventService;

import static com.mantkowicz.light.actor.implementation.player.PlayerStatus.IDLE;
import static com.mantkowicz.light.service.event.type.GameEventType.COLLISION;

public class CollectPlugin extends Plugin {
    private final Player player;
    private final GameEventService gameEventService;

    public CollectPlugin(Player player, CollectPluginConfiguration configuration) {
        this.player = player;
        this.gameEventService = configuration.getGameEventService();
    }

    @Override
    public void run() {
        if (gameEventService.containsEvent(COLLISION) && IDLE.equals(player.getStatus())) {
            GameEvent gameEvent = gameEventService.getEvent(COLLISION, true);
            System.out.println("Collision with chest: " + ((Chest) gameEvent.getEventObject()).getName());
        }
    }
}
