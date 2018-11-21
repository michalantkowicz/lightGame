package com.mantkowicz.light.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mantkowicz.light.board.Board;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.listener.TileClickListener;
import com.mantkowicz.light.map.TiledMapLoader;
import com.mantkowicz.light.map.implementation.tmx.TmxTileMapLoaderProperties;
import com.mantkowicz.light.map.implementation.tmx.TmxTiledMapLoader;
import com.mantkowicz.light.player.Player;
import com.mantkowicz.light.service.event.GameEventService;

import java.util.List;

public class GameScreen implements Screen {
    private AssetManager assetManager;
    private GameEventService gameEventService;
    private Stage stage;
    private Board board;

    public GameScreen(AssetManager assetManager, GameEventService gameEventService) {
        this.assetManager = assetManager;
        this.gameEventService = gameEventService;
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
            tile.addListener(new TileClickListener(tile, gameEventService));
            stage.addActor(tile);
        }

        Player player = new Player(assetManager.get("player.png"), gameEventService, new BoardService(board));
        player.setTile(tiles.get(0));

        stage.addActor(player);
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
