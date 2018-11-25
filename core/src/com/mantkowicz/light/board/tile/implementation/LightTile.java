package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.tile.GamePrepareConfiguration;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.listener.TileClickListener;
import com.mantkowicz.light.lights.TorchLight;

public class LightTile extends Tile {
    protected TorchLight torchLight;

    public LightTile(AssetManager assetManager) {
        super(assetManager.get("floor-tile.png"));
    }

    @Override
    public boolean isAccessible() {
        return true;
    }

    @Override
    public void prepare(TileClickListener tileClickListener, GamePrepareConfiguration configuration) {
        super.prepare(tileClickListener, configuration);

        this.torchLight = new TorchLight(configuration.getRayHandler());
        torchLight.setTile(this);
        configuration.getStage().addActor(torchLight);
    }

    protected void moveLight(float x, float y) {
        torchLight.setPosition(torchLight.getX() + x, torchLight.getY() + y);
    }
}
