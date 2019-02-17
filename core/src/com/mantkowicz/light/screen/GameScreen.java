package com.mantkowicz.light.screen;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mantkowicz.light.board.Board;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.feature.Feature;
import com.mantkowicz.light.feature.implementation.CameraTrackingFeature;
import com.mantkowicz.light.map.TiledMapLoader;
import com.mantkowicz.light.map.implementation.tmx.TmxTileMapLoaderProperties;
import com.mantkowicz.light.map.implementation.tmx.TmxTiledMapLoader;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.PlayerCreatedEvent;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.service.resources.ResourcesService;
import com.mantkowicz.light.stage.MenuStage;
import com.mantkowicz.light.stage.NotificationStage;

import java.util.ArrayList;
import java.util.List;

import static com.mantkowicz.light.game.Main.SCREEN_HEIGHT;
import static com.mantkowicz.light.game.Main.SCREEN_WIDTH;
import static com.mantkowicz.light.service.event.GameEventType.PLAYER_CREATED;

public class GameScreen implements Screen {
    private final ResourcesService resourcesService;
    private final GameEventService gameEventService;
    private final World world;
    private Stage stage;
    private MenuStage menuStage;
    private Stage uiStage;
    private NotificationStage notificationStage;
    private final Board board = new Board();
    private RayHandler rayHandler;
    private List<Feature> features = new ArrayList<>();

    public GameScreen(ResourcesService resourcesService, GameEventService gameEventService, World world) {
        this.resourcesService = resourcesService;
        this.gameEventService = gameEventService;
        this.world = world;
    }

    @Override
    public void show() {
        Viewport viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        Viewport menuViewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport);
        menuStage = new MenuStage(resourcesService, menuViewport);
        uiStage = new Stage(viewport);
        notificationStage = new NotificationStage(viewport);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);

        rayHandler = prepareLights();

        List<Tile> tiles = loadTiles(board);

        // Preparing configuration after loading tiles
        GamePrepareConfiguration configuration = prepareConfiguration();

        for (Tile tile : tiles) {
            stage.addActor(tile);
            tile.toBack();

            tile.prepare(configuration);
        }

        if (gameEventService.containsEvent(PLAYER_CREATED)) {
            PlayerCreatedEvent gameEvent = gameEventService.getEvent(PLAYER_CREATED, PlayerCreatedEvent.class, true);
            CameraTrackingFeature cameraTrackingFeature = new CameraTrackingFeature(gameEvent.getEventObject(), configuration);
            addFeature(cameraTrackingFeature);
        }
    }

    private RayHandler prepareLights() {
        RayHandler rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.02f, 0.02f, 0.02f, 0.5f);
//        rayHandler.setAmbientLight(0.02f, 0.02f, 0.02f, 1f);
        return rayHandler;
    }

    private List<Tile> loadTiles(Board board) {
        TmxTileMapLoaderProperties properties = new TmxTileMapLoaderProperties().setTileMapFileName("map.tmx");
        TiledMapLoader<TmxTileMapLoaderProperties> tmxTiledMapLoader = new TmxTiledMapLoader(resourcesService);

        return board.loadTiles(tmxTiledMapLoader, properties);
    }

    private GamePrepareConfiguration prepareConfiguration() {
        BoardService boardService = new BoardService(gameEventService, board);
        PhraseService phraseService = new PhraseService();
        OrthographicCamera camera = (OrthographicCamera) stage.getCamera();

        return new GamePrepareConfiguration(gameEventService,
                boardService,
                world,
                rayHandler,
                stage,
                notificationStage,
                menuStage,
                phraseService,
                uiStage,
                resourcesService,
                camera);
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

        menuStage.act(delta);
        menuStage.draw();

        uiStage.act(delta);
        uiStage.draw();

        for (Feature feature : features) {
            feature.run(delta);
        }

        gameEventService.updateEventsLifesAndClean();
    }

    protected void addFeature(Feature feature) {
        features.add(feature);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        menuStage.getViewport().update(width, height, true);
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
