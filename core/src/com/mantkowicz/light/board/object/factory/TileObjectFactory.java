package com.mantkowicz.light.board.object.factory;

import com.badlogic.gdx.maps.MapProperties;
import com.mantkowicz.light.board.object.TileObject;
import com.mantkowicz.light.board.object.TileObjectAttribute;
import com.mantkowicz.light.board.object.TileObjectType;
import com.mantkowicz.light.board.object.implementation.chest.TileChest;
import com.mantkowicz.light.board.object.implementation.dog.TileDog;
import com.mantkowicz.light.board.object.implementation.light.TileLight;
import com.mantkowicz.light.board.object.implementation.player.TilePlayer;
import com.mantkowicz.light.board.object.implementation.sprite.TileSprite;

import static com.mantkowicz.light.board.object.TileObjectAttribute.LIGHT_TYPE;
import static com.mantkowicz.light.board.object.TileObjectAttribute.TEXTURE_NAME;

public class TileObjectFactory {
    public static TileObject createTileObject(TileObjectType objectType, MapProperties objectProperties) {
        switch (objectType) {
            case SPRITE:
                assertMandatoryProperties(objectProperties, TEXTURE_NAME);
                return new TileSprite(objectProperties);
            case CHEST:
                return new TileChest();
            case LIGHT:
                assertMandatoryProperties(objectProperties, LIGHT_TYPE);
                return new TileLight(objectProperties);
            case PLAYER:
                return new TilePlayer();
            case DOG:
                return new TileDog();
            default:
                throw new IllegalArgumentException("There is no tile implementation for this tile object type: " + objectType.name());
        }
    }

    private static void assertMandatoryProperties(MapProperties properites, TileObjectAttribute... attributes) {
        for (TileObjectAttribute attribute : attributes) {
            if (!doesContainNotNullProperty(properites, attribute.getValue())) {
                throw new IllegalStateException("Tile object does not contain mandatory property " + attribute.getValue());
            }
        }
    }

    private static boolean doesContainNotNullProperty(MapProperties properties, String propertyName) {
        return properties.containsKey(propertyName)
                && properties.get(propertyName, String.class) != null
                && !properties.get(propertyName, String.class).isEmpty();
    }
}
