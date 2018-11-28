package com.mantkowicz.light.board.tile.implementation;

public enum BorderLightPosition {
    TOP(0),
    TOP_RIGHT(-60),
    TOP_LEFT(60),
    BOTTOM(180),
    BOTTOM_RIGHT(-120),
    BOTTOM_LEFT(120);

    private final int value;

    BorderLightPosition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
