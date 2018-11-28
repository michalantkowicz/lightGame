package com.mantkowicz.light.actor.implementation.collectable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.configuration.PlayerConfiguration;

import static com.mantkowicz.light.actor.GameActorType.CHEST;

public class Chest extends GameActor {
    private static final String AVATAR_RESOURCE_NAME = "chest.png";

    public Chest(PlayerConfiguration configuration) {
        super(CHEST, configuration.getBoardService());

        setName("Nice Chest");

        Texture avatarTexture = configuration.getAssetManager().get(AVATAR_RESOURCE_NAME, Texture.class);
        createAvatar(avatarTexture);

        setTouchable(Touchable.disabled);
    }
}
