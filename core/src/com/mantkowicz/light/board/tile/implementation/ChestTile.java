package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.actor.Item;
import com.mantkowicz.light.actor.implementation.collectible.Chest;
import com.mantkowicz.light.actor.implementation.item.Lighter;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.listener.TileClickListener;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ChestTile extends Tile {
    public ChestTile(AssetManager assetManager) {
        super(assetManager.get("floor-tile.png"));
    }

    @Override
    public boolean isAccessible() {
        return true;
    }

    @Override
    public void prepare(TileClickListener tileClickListener, GamePrepareConfiguration configuration) {
        super.prepare(tileClickListener, configuration);

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
