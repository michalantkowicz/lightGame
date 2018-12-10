package com.mantkowicz.light.board.tile.implementation;

import com.mantkowicz.light.actor.Item;
import com.mantkowicz.light.actor.implementation.collectible.Chest;
import com.mantkowicz.light.actor.implementation.item.Lighter;
import com.mantkowicz.light.board.tile.AccessibleTile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.service.resources.ResourcesService;

import java.util.ArrayList;
import java.util.List;

public class ChestTile extends AccessibleTile {
    public ChestTile(ResourcesService resourcesService) {
        super(resourcesService, "floor-tile.png");
    }

    @Override
    public void prepare(GamePrepareConfiguration configuration) {
        super.prepare(configuration);

        Chest chest = new Chest(configuration);
        chest.setTile(this);

        List<Item> content = getContent(configuration);
        chest.getInventory().setItemsAndCompleteWithNulls(content);

        configuration.getStage().addActor(chest);
    }

    private List<Item> getContent(GamePrepareConfiguration configuration) {
        Lighter lighter = new Lighter(configuration);

        List<Item> content = new ArrayList<>();
        content.add(lighter);

        return content;
    }
}
