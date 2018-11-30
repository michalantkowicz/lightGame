package com.mantkowicz.light.actor.implementation.collectible;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.actor.Item;
import com.mantkowicz.light.configuration.api.PlayerConfiguration;
import com.mantkowicz.light.actor.Collectible;

import java.util.List;

import static com.mantkowicz.light.actor.GameActorType.CHEST;

public class Chest extends GameActor implements Collectible {
    private static final String AVATAR_RESOURCE_NAME = "chest.png";
    private List<Item> items;

    public Chest(PlayerConfiguration configuration) {
        super(CHEST, configuration.getBoardService());

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
    public List<Item> getItems() {
        return items;
    }

    public Chest setItems(List<Item> items) {
        this.items = items;
        return this;
    }
}
