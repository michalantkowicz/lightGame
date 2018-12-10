package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.graphics.Color;
import com.mantkowicz.light.board.tile.AccessibleTile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.lights.GameLight;
import com.mantkowicz.light.lights.LightType;
import com.mantkowicz.light.lights.factory.LightFactory;
import com.mantkowicz.light.service.resources.ResourcesService;

import static com.mantkowicz.light.board.tile.TileAttribute.LIGHT_COLOR_HEX;
import static com.mantkowicz.light.board.tile.TileAttribute.LIGHT_TYPE;

public class TorchLightTile extends AccessibleTile {
    GameLight torchLight;

    public TorchLightTile(ResourcesService resourcesService) {
        super(resourcesService, "floor-tile.png");
    }

    @Override
    public void prepare(GamePrepareConfiguration configuration) {
        super.prepare(configuration);
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
