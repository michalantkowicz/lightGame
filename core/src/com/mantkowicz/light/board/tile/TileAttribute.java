package com.mantkowicz.light.board.tile;

public enum TileAttribute {
    TILE_CLASS("tileClass");

    private final String value;

    TileAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
