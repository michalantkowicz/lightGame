package com.mantkowicz.light.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mantkowicz.light.screen.GameScreen;
import com.mantkowicz.light.service.event.GameEventService;

import java.util.Arrays;
import java.util.List;

public class Main extends Game {
    private AssetManager assetManager;
    private GameEventService gameEventService;
    private RayHandler rayHandler;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    @Override
    public void create() {
        gameEventService = new GameEventService();

        assetManager = new AssetManager();
        loadAssets();

        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        rayHandler = new RayHandler(world);

        setScreen(new GameScreen(assetManager, gameEventService, world, debugRenderer));
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
        world.dispose();
        assetManager.dispose();
    }
}
