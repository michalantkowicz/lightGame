package com.mantkowicz.light.service.event.implementation;

import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.service.event.GameEvent;

import static com.mantkowicz.light.service.event.GameEventType.PLAYER_MOVE;

public class PlayerMoveEvent extends GameEvent<Player> {
    /**
     * @param maxAge how much loops event lives, must be greater or equal 0
     * @throws IllegalArgumentException if maxAge < 0
     */
    public PlayerMoveEvent(Player player, int maxAge) {
        super(PLAYER_MOVE, player, maxAge);
    }
}
