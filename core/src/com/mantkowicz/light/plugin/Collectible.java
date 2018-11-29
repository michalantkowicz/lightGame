package com.mantkowicz.light.plugin;

public interface Collectible {
    void beforeCollect();

    void afterCollect();

    String getName();
}
