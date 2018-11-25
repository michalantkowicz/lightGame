package com.mantkowicz.light.screen;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mantkowicz.light.board.Board;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.GamePrepareConfiguration;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.listener.TileClickListener;
import com.mantkowicz.light.map.TiledMapLoader;
import com.mantkowicz.light.map.implementation.tmx.TmxTileMapLoaderProperties;
import com.mantkowicz.light.map.implementation.tmx.TmxTiledMapLoader;
import com.mantkowicz.light.service.event.GameEventService;

import java.util.List;

public class GameScreen implements Screen {
    private AssetManager assetManager;
    private GameEventService gameEventService;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;
    private Stage notificationStage;
    private Board board;
    private RayHandler rayHandler;

    public GameScreen(AssetManager assetManager, GameEventService gameEventService, World world, Box2DDebugRenderer debugRenderer) {
        this.assetManager = assetManager;
        this.gameEventService = gameEventService;
        this.world = world;
        this.debugRenderer = debugRenderer;
    }

    @Override
    public void show() {
        board = new Board();

        TmxTileMapLoaderProperties properties = new TmxTileMapLoaderProperties().setTileMapFileName("map.tmx");
        TiledMapLoader<TmxTileMapLoaderProperties> tmxTiledMapLoader = new TmxTiledMapLoader(assetManager);

        List<Tile> tiles = board.loadTiles(tmxTiledMapLoader, properties);

        stage = new Stage(new ScreenViewport());
        notificationStage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);

        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.02f, 0.02f, 0.02f, 0.1f);

        BoardService boardService = new BoardService(board);

        GamePrepareConfiguration configuration = new GamePrepareConfiguration(assetManager,
                gameEventService,
                boardService,
                world,
                rayHandler,
                stage,
                notificationStage);

        for (Tile tile : tiles) {
            stage.addActor(tile);
            tile.toBack();

            TileClickListener listener = new TileClickListener(tile, gameEventService);
            tile.prepare(listener, configuration);
        }
    }

    @Override
    public void render(float delta) {
        world.step(1 / 60f, 6, 2);
        stage.act(delta);
        stage.draw();

        rayHandler.setCombinedMatrix((OrthographicCamera) stage.getCamera());
        rayHandler.updateAndRender();

        notificationStage.act(delta);
        notificationStage.draw();

//        debugRenderer.render(world, stage.getCamera().combined);
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
        rayHandler.dispose();
    }
}
