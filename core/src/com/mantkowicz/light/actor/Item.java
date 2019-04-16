package com.mantkowicz.light.actor;

import com.mantkowicz.light.thumbnail.Thumbnail;

public interface Item extends Thumbnail {
    void use();

    void unuse();
}
