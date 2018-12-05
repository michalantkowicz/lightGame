package com.mantkowicz.light.service.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Arrays;
import java.util.List;

public class ResourcesService {
    private final Skin skin;
    private final AssetManager assetManager;

    public ResourcesService() {
        assetManager = new AssetManager();

        TextureLoader.TextureParameter textureParameter = new TextureLoader.TextureParameter();
        textureParameter.minFilter = Texture.TextureFilter.Linear;
        textureParameter.magFilter = Texture.TextureFilter.Linear;

        List<String> textureNames = Arrays.asList("floor-tile.png", "wall-tile.png", "player.png", "chest.png",
                "lighter.png", "item_background.png", "table_background.png", "item_background_pressed.png", "item_background_able_to_put.png",
                "item_background_chosen.png");

        for (String textureName : textureNames) {
            assetManager.load(textureName, Texture.class, textureParameter);
        }

        assetManager.load("ui/uiskin.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"), assetManager.get("ui/uiskin.atlas", TextureAtlas.class));
    }

    public Skin getSkin() {
        return skin;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void dispose() {
        assetManager.dispose();
        skin.dispose();
    }
}
