package com.mantkowicz.light.actor.implementation.item;

import com.badlogic.gdx.graphics.Texture;
import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.actor.Inventory;
import com.mantkowicz.light.actor.Item;
import com.mantkowicz.light.configuration.api.PlayerConfiguration;

import static com.mantkowicz.light.actor.GameActorType.LIGHTER;

public class Lighter extends GameActor implements Item, Collectible {
    private final Texture thumbnail;

    public Lighter(PlayerConfiguration configuration) {
        super(LIGHTER, configuration.getBoardService());
        thumbnail = configuration.getResourcesService().getAssetManager().get("lighter.png", Texture.class);
    }

    @Override
    public Texture getThumbnail() {
        return thumbnail;
    }

    @Override
    public void use() {
        System.out.println("TURN LIGHTER ON");
    }

    @Override
    public void unuse() {
        System.out.println("TURN LIGHTER OFF");
    }

    @Override
    public void beforeCollect() {

    }

    @Override
    public void afterCollect() {

    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
