package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.actor.implementation.collectible.Chest;
import com.mantkowicz.light.actor.implementation.item.Lighter;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.listener.TileClickListener;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

import java.util.Arrays;

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
        Lighter lighter = new Lighter(configuration);

        chest.setTile(this);
        chest.setItems(Arrays.asList(lighter));

        configuration.getStage().addActor(chest);
    }
}
