package com.mantkowicz.light.board.object.factory;

import com.badlogic.gdx.maps.MapProperties;
import com.mantkowicz.light.board.object.TileObject;
import com.mantkowicz.light.board.object.TileObjectType;
import com.mantkowicz.light.board.object.implementation.TileSprite;

import static com.mantkowicz.light.board.object.TileObjectAttribute.TEXTURE_NAME;

public class TileObjectFactory {
    public static TileObject createTileObject(TileObjectType objectType, MapProperties objectProperties) {
        switch (objectType) {
            case SPRITE:
                if (!containsNotNullProperty(objectProperties, TEXTURE_NAME.getValue())) {
                    throw new IllegalArgumentException("This type of tile object must contain not null property: " + TEXTURE_NAME.getValue());
                }
                return new TileSprite(objectProperties);
            default:
                throw new IllegalArgumentException("There is no tile implementation for this tile object type: " + objectType.name());
        }
    }

    private static boolean containsNotNullProperty(MapProperties properties, String propertyName) {
        return properties.containsKey(propertyName)
                && properties.get(propertyName, String.class) != null
                && !properties.get(propertyName, String.class).isEmpty();
    }
}
