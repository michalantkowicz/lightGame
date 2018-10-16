package com.mantkowicz.light.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mantkowicz.light.board.Board;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.map.TiledMapLoader;
import com.mantkowicz.light.map.implementation.tmx.TmxTileMapLoaderProperties;
import com.mantkowicz.light.map.implementation.tmx.TmxTiledMapLoader;

import java.util.List;
import java.util.stream.Collectors;

public class GameScreen implements Screen {
    private AssetManager assetManager;
    private Stage stage;
    private Board board;

    public GameScreen(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void show() {
        board = new Board();

        TmxTileMapLoaderProperties properties = new TmxTileMapLoaderProperties().setTileMapFileName("map2.tmx");
        TiledMapLoader<TmxTileMapLoaderProperties> tmxTiledMapLoader = new TmxTiledMapLoader(assetManager);

        List<Tile> tiles = board.loadTiles(tmxTiledMapLoader, properties);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        for (Tile tile : tiles) {
            stage.addActor(tile);
        }

//        EventListener clickListener = new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println(x + ", " + y);
//            }
//        };

//        stage.addListener(clickListener);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
