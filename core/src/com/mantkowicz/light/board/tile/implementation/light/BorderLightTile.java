package com.mantkowicz.light.board.tile.implementation.light;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.mantkowicz.light.board.tile.GamePrepareConfiguration;
import com.mantkowicz.light.board.tile.implementation.LightTile;
import com.mantkowicz.light.board.tile.listener.TileClickListener;

public abstract class BorderLightTile extends LightTile {
    protected static final float BORDER_OFFSET = 5f;
    private int rotation;

    public BorderLightTile(AssetManager assetManager) {
        super(assetManager);
    }

    @Override
    public void prepare(TileClickListener tileClickListener, GamePrepareConfiguration configuration) {
        super.prepare(tileClickListener, configuration);

        Vector2 lightPosition = new Vector2(torchLight.getX(), torchLight.getY());
        Vector2 newLightPosition = lightPosition.cpy().add(0,  getHeight() / 2f - BORDER_OFFSET);
        Vector2 offset = newLightPosition.cpy().sub(lightPosition).rotate(rotation);
        moveLight(offset.x, offset.y);
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
