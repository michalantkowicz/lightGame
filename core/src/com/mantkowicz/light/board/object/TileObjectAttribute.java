package com.mantkowicz.light.board.object;

public enum TileObjectAttribute {
    OBJECT_CLASS("objectClass"),
    TEXTURE_NAME("textureName"),
    TEXTURE_OFFSET("textureOffset");

    private final String value;

    TileObjectAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
