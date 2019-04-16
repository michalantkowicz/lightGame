package com.mantkowicz.light.actor;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.thumbnail.Thumbnail;

public interface Collectible extends Thumbnail {
    Collectible DEFAULT = null;

    /**
     * Defines whether the collectible should collide with neighbour tiles
     */
    boolean isDistant();

    void beforeCollect();

    void afterCollect();

    Inventory getInventory();

    String getName();

    Tile getTile();
}
