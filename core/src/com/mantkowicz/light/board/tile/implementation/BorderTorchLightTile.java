package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.board.tile.listener.TileClickListener;

import static com.mantkowicz.light.board.tile.TileAttribute.BORDER_LIGHT_POSITION;

public class BorderTorchLightTile extends TorchLightTile {
    private static final float BORDER_OFFSET = 5f;
    private int rotation;

    public BorderTorchLightTile(AssetManager assetManager) {
        super(assetManager);
    }

    @Override
    public void prepare(TileClickListener tileClickListener, GamePrepareConfiguration configuration) {
        super.prepare(tileClickListener, configuration);

        rotation = calculateRotation();

        Vector2 lightPosition = new Vector2(torchLight.getX(), torchLight.getY());
        Vector2 newLightPosition = lightPosition.cpy().add(0, getHeight() / 2f - BORDER_OFFSET);
        Vector2 offset = newLightPosition.cpy().sub(lightPosition).rotate(rotation);
        moveLight(offset.x, offset.y);
    }

    private int calculateRotation() {
        String borderLightPositionAttribute = getAttributes().get(BORDER_LIGHT_POSITION.getValue(), String.class);
        BorderLightPosition borderLightPosition = BorderLightPosition.valueOf(borderLightPositionAttribute);
        return borderLightPosition.getValue();
    }
}
