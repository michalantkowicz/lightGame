package com.mantkowicz.light.board.object.implementation.chest;

import com.mantkowicz.light.actor.Item;
import com.mantkowicz.light.actor.implementation.collectible.Chest;
import com.mantkowicz.light.actor.implementation.item.Lighter;
import com.mantkowicz.light.board.object.TileObject;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

import java.util.ArrayList;
import java.util.List;

import static com.mantkowicz.light.board.object.TileObjectType.CHEST;

public class TileChest extends TileObject {
    public TileChest() {
        super(CHEST);
    }

    @Override
    public void prepare(Tile tile, GamePrepareConfiguration configuration) {
        Chest chest = new Chest(configuration);
        chest.setTile(tile);

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

    @Override
    public boolean isAccessible() {
        return true;
    }
}
