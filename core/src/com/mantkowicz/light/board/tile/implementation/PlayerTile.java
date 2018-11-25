package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.GamePrepareConfiguration;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.listener.TileClickListener;
import com.mantkowicz.light.player.Player;

public class PlayerTile extends Tile {
    public PlayerTile(AssetManager assetManager) {
        super(assetManager.get("floor-tile.png"));
    }

    @Override
    public boolean isAccessible() {
        return true;
    }

    @Override
    public void prepare(TileClickListener tileClickListener, GamePrepareConfiguration configuration) {
        super.prepare(tileClickListener, configuration);

        Player player = new Player(configuration.getAssetManager(),
                configuration.getGameEventService(),
                configuration.getBoardService(),
                configuration.getRayHandler(),
                configuration.getNotificationStage());
        player.setTile(this);
        configuration.getStage().addActor(player);
    }
}
