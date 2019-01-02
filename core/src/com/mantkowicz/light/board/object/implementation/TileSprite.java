package com.mantkowicz.light.board.object.implementation;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mantkowicz.light.board.object.TileObject;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

import static com.mantkowicz.light.board.object.TileObjectAttribute.TEXTURE_NAME;
import static com.mantkowicz.light.board.object.TileObjectType.SPRITE;

public class TileSprite extends TileObject {
    private final String textureName;

    public TileSprite(MapProperties objectProperties) {
        super(SPRITE);
        textureName = objectProperties.get(TEXTURE_NAME.getValue(), String.class);
    }

    @Override
    public Actor prepare(GamePrepareConfiguration configuration) {
        Image image = new Image(configuration.getResourcesService().getTexture(textureName));
        image.setPosition(74 - image.getWidth() / 2f, 64 - image.getHeight() / 2f);
        return image;
    }

    @Override
    public boolean isAccessible() {
        return false;
    }
}
