package com.mantkowicz.light.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Item {
    TextureRegion getThumbnail();

    void use();

    void unuse();
}
