package com.mantkowicz.light.screen;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mantkowicz.light.board.Board;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.board.tile.listener.TileClickListener;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.map.TiledMapLoader;
import com.mantkowicz.light.map.implementation.tmx.TmxTileMapLoaderProperties;
import com.mantkowicz.light.map.implementation.tmx.TmxTiledMapLoader;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.stage.NotificationStage;

import java.util.List;

public class GameScreen implements Screen {
    private final AssetManager assetManager;
    private final GameEventService gameEventService;
    private final World world;
    private Stage stage;
    private NotificationStage notificationStage;
    private final Board board = new Board();
    private RayHandler rayHandler;

    public GameScreen(AssetManager assetManager, GameEventService gameEventService, World world) {
        this.assetManager = assetManager;
        this.gameEventService = gameEventService;
        this.world = world;
    }

    @Override
    public void show() {
        stage = prepareStage();
        notificationStage = prepareNotificationStage();
        rayHandler = prepareLights();
        GamePrepareConfiguration configuration = prepareConfiguration();

        List<Tile> tiles = loadTiles(board);

        for (Tile tile : tiles) {
            stage.addActor(tile);
            tile.toBack();

            TileClickListener listener = new TileClickListener(tile, gameEventService);
            tile.prepare(listener, configuration);
        }
    }

    private Stage prepareStage() {
        Stage stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        return stage;
    }

    private NotificationStage prepareNotificationStage() {
        return new NotificationStage(new ScreenViewport());
    }

    private RayHandler prepareLights() {
        RayHandler rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.02f, 0.02f, 0.02f, 0.1f);
        return rayHandler;
    }

    private List<Tile> loadTiles(Board board) {
        TmxTileMapLoaderProperties properties = new TmxTileMapLoaderProperties().setTileMapFileName("map.tmx");
        TiledMapLoader<TmxTileMapLoaderProperties> tmxTiledMapLoader = new TmxTiledMapLoader(assetManager);

        return board.loadTiles(tmxTiledMapLoader, properties);
    }

    private GamePrepareConfiguration prepareConfiguration() {
        BoardService boardService = new BoardService(board);
        PhraseService phraseService = new PhraseService();

        return new GamePrepareConfiguration(assetManager,
                gameEventService,
                boardService,
                world,
                rayHandler,
                stage,
                notificationStage,
                phraseService);
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
