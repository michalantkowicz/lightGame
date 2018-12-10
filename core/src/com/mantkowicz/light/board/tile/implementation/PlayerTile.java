package com.mantkowicz.light.board.tile.implementation;

import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.board.tile.AccessibleTile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.service.event.implementation.PlayerCreatedEvent;
import com.mantkowicz.light.service.resources.ResourcesService;

public class PlayerTile extends AccessibleTile {
    public PlayerTile(ResourcesService resourcesService) {
        super(resourcesService, "floor-tile.png");
    }

    @Override
    public void prepare(GamePrepareConfiguration configuration) {
        super.prepare(configuration);

        Player player = new Player(configuration);
        player.setTile(this);

        configuration.getStage().addActor(player);

        configuration.getGameEventService().addEvent(new PlayerCreatedEvent(player));
    }
}
