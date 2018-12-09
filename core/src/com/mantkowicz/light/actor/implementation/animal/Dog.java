package com.mantkowicz.light.actor.implementation.animal;

import com.badlogic.gdx.graphics.Texture;
import com.mantkowicz.light.actor.BoardGameActor;
import com.mantkowicz.light.configuration.api.PlayerConfiguration;

import static com.mantkowicz.light.actor.GameActorType.DOG;

public class Dog extends BoardGameActor {
    private static final String AVATAR_RESOURCE_NAME = "dog.png";

    public Dog(PlayerConfiguration configuration) {
        super(DOG, configuration.getBoardService());

        Texture avatarTexture = configuration.getResourcesService().getAssetManager().get(AVATAR_RESOURCE_NAME, Texture.class);
        createAvatar(avatarTexture);
    }
}
