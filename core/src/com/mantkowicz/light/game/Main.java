package com.mantkowicz.light.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mantkowicz.light.screen.GameScreen;

import java.util.Arrays;
import java.util.List;

public class Main extends Game {
    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        loadAssets();
        setScreen(new GameScreen(assetManager));
    }

    private void loadAssets() {
        List<String> textureNames = Arrays.asList("brown.png", "floor.png", "floor-tile.png",
                "green.png", "pink.png", "red.png", "wall-tile.png", "player.png");

        for (String textureName : textureNames) {
            assetManager.load(textureName, Texture.class);
        }

        assetManager.finishLoading();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
