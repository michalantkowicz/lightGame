package com.mantkowicz.light.actor;

import com.badlogic.gdx.graphics.Texture;

public interface Item {
    Texture getThumbnail();

    void use();

    void unuse();
}
