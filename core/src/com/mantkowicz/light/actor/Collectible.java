package com.mantkowicz.light.actor;

import com.mantkowicz.light.board.tile.Tile;

public interface Collectible {
    void beforeCollect();

    void afterCollect();

    Inventory getInventory();

    String getName();

    Tile getTile();
}
