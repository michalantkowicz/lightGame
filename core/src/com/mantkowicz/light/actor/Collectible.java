package com.mantkowicz.light.actor;

import com.mantkowicz.light.actor.Item;

import java.util.List;

public interface Collectible {
    void beforeCollect();

    void afterCollect();

    List<Item> getItems();

    String getName();
}
