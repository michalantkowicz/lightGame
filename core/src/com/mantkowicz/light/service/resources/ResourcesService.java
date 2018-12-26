package com.mantkowicz.light.service.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;
import java.util.List;

public class ResourcesService {
    private final Skin skin;
    private final AssetManager assetManager;
    private final Array<TextureAtlas> atlases = new Array<>();

    public ResourcesService() {
        assetManager = new AssetManager();

        TextureLoader.TextureParameter textureParameter = new TextureLoader.TextureParameter();
        textureParameter.minFilter = Texture.TextureFilter.Linear;
        textureParameter.magFilter = Texture.TextureFilter.Linear;

        List<String> textureNames = Arrays.asList("floor-tile.png", "wall-tile.png", "player.png", "chest.png",
                "lighter.png", "item_background.png", "table_background.png", "item_background_pressed.png", "item_background_able_to_put.png",
                "item_background_chosen.png", "dog.png", "black.png");

        for (String textureName : textureNames) {
            assetManager.load(textureName, Texture.class, textureParameter);
        }

        assetManager.load("skin/skin.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        assetManager.getAll(TextureAtlas.class, atlases);
        skin = new Skin(Gdx.files.internal("skin/skin.json"), assetManager.get("skin/skin.atlas", TextureAtlas.class));
    }

    public AtlasRegion getAtlasRegion(String regionName) {
        AtlasRegion region = null;

        for (TextureAtlas atlas : atlases) {
            region = atlas.findRegion(regionName);

            if (region != null) {
                break;
            }
        }
        if (region == null) {
            throw new IllegalArgumentException("Couldn't find region: " + regionName);
        }
        return region;
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
