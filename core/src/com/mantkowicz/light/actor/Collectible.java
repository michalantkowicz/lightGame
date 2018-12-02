package com.mantkowicz.light.actor;

public interface Collectible {
    void beforeCollect();

    void afterCollect();

    Inventory getInventory();

    String getName();
}
