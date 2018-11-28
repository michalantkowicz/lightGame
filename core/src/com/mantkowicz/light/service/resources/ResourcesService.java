package com.mantkowicz.light.service.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
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

        List<String> textureNames = Arrays.asList("floor-tile.png", "wall-tile.png", "player.png", "chest.png");

        for (String textureName : textureNames) {
            assetManager.load(textureName, Texture.class);
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
