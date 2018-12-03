package com.mantkowicz.light.actor.implementation.collectible;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mantkowicz.light.actor.BoardGameActor;
import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.Inventory;
import com.mantkowicz.light.configuration.api.PlayerConfiguration;

import java.util.ArrayList;

import static com.mantkowicz.light.actor.GameActorType.CHEST;

public class Chest extends BoardGameActor implements Collectible {
    private static final String AVATAR_RESOURCE_NAME = "chest.png";

    private final Inventory inventory;

    public Chest(PlayerConfiguration configuration) {
        super(CHEST, configuration.getBoardService());

        inventory = new Inventory(2, new ArrayList<>());
        setName("Nice Chest");

        Texture avatarTexture = configuration.getResourcesService().getAssetManager().get(AVATAR_RESOURCE_NAME, Texture.class);
        createAvatar(avatarTexture);

        setTouchable(Touchable.disabled);
    }

    @Override
    public void beforeCollect() {
        System.out.println("I'll be collect!");
        remove();
    }

    @Override
    public void afterCollect() {
        System.out.println("I'm being collected!");
        remove();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
