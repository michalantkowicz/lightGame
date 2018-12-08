package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.board.tile.AccessibleTile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

public class PlayerTile extends AccessibleTile {
    public PlayerTile(AssetManager assetManager) {
        super(assetManager.get("floor-tile.png"));
    }

    @Override
    public void prepare(GamePrepareConfiguration configuration) {
        super.prepare(configuration);

        Player player = new Player(configuration);
        player.setTile(this);

        configuration.getStage().addActor(player);
    }
}
