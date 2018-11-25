package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.mantkowicz.light.board.tile.GamePrepareConfiguration;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.listener.TileClickListener;
import com.mantkowicz.light.lights.GameLight;
import com.mantkowicz.light.lights.LightType;
import com.mantkowicz.light.lights.factory.LightFactory;

import static com.mantkowicz.light.board.tile.TileAttribute.LIGHT_COLOR_HEX;
import static com.mantkowicz.light.board.tile.TileAttribute.LIGHT_TYPE;

public class TorchLightTile extends Tile {
    GameLight torchLight;

    public TorchLightTile(AssetManager assetManager) {
        super(assetManager.get("floor-tile.png"));
    }

    @Override
    public boolean isAccessible() {
        return true;
    }

    @Override
    public void prepare(TileClickListener tileClickListener, GamePrepareConfiguration configuration) {
        super.prepare(tileClickListener, configuration);
        String lightTypeAttribute = getAttributes().get(LIGHT_TYPE.getValue(), String.class);
        torchLight = LightFactory.createLight(configuration.getRayHandler(), LightType.valueOf(lightTypeAttribute));
        torchLight.setTile(this);
        if (getAttributes().containsKey(LIGHT_COLOR_HEX.getValue())) {
            String lightColorAttribute = getAttributes().get(LIGHT_COLOR_HEX.getValue(), String.class);
            Color lightColor = Color.valueOf(lightColorAttribute);
            torchLight.setColor(lightColor);
        }
        configuration.getStage().addActor(torchLight);
    }

    void moveLight(float x, float y) {
        torchLight.setPosition(torchLight.getX() + x, torchLight.getY() + y);
    }
}
