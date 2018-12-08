package com.mantkowicz.light.service.event.implementation;

import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.service.event.GameEvent;

import static com.mantkowicz.light.service.event.GameEventType.PLAYER_CREATED;

public class PlayerCreatedEvent extends GameEvent<Player> {
    public PlayerCreatedEvent(Player player) {
        super(PLAYER_CREATED, player);
    }
}
