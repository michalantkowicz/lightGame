package com.mantkowicz.light.board.tile;

public enum TileAttribute {
    TILE_CLASS("tileClass"),
    LIGHT_TYPE("lightType"),
    LIGHT_COLOR_HEX("lightColorHex"),
    BORDER_LIGHT_POSITION("borderLightPosition");

    private String value;

    TileAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
