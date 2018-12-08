package com.mantkowicz.light.feature;

/**
 * This interface is created to differ Plugins attached to specific GameActors
 * and more general 'Plugins' attached to whole screen
 */
public interface Feature {
    void run(float delta);
}
