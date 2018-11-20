package com.mantkowicz.light.player.plugin;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.player.Player;
import com.mantkowicz.light.service.event.GameEvent;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.type.GameEventType;

import static com.mantkowicz.light.service.event.type.GameEventType.TILE_TOUCHED;

public class BoardMovementPlugin extends Plugin {
    private Player player;
    private GameEventService gameEventService;

    public BoardMovementPlugin(Player player, GameEventService gameEventService) {
        this.player = player;
        this.gameEventService = gameEventService;
    }

    @Override
    public void run() {
        if(gameEventService.containsEvent(TILE_TOUCHED)) {
            GameEvent gameEvent = gameEventService.getEvent(TILE_TOUCHED);
            Tile tile = gameEvent.getTile();
            if(player.getTile().isNeighbour(tile)) {
                player.setTile(tile);
            }
        }
    }
}
