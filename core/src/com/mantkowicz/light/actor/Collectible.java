package com.mantkowicz.light.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mantkowicz.light.board.tile.Tile;

public interface Collectible {
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

    TextureRegion getThumbnail();
}
