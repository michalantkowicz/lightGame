package com.mantkowicz.light.screen;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.board.Board;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.feature.Feature;
import com.mantkowicz.light.feature.implementation.CameraTrackingFeature;
import com.mantkowicz.light.feature.implementation.MenuFeature;
import com.mantkowicz.light.feature.implementation.StageFeature;
import com.mantkowicz.light.map.TiledMapLoader;
import com.mantkowicz.light.map.implementation.tmx.TmxTileMapLoaderProperties;
import com.mantkowicz.light.map.implementation.tmx.TmxTiledMapLoader;
import com.mantkowicz.light.notification.NotificationStyle;
import com.mantkowicz.light.notification.factory.LabelFactory;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.PlayerCreatedEvent;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.service.resources.ResourcesService;
import com.mantkowicz.light.stage.GameStage;
import com.mantkowicz.light.stage.MenuStage;
import com.mantkowicz.light.stage.NotificationStage;

import java.util.ArrayList;
import java.util.List;

import static com.mantkowicz.light.game.Main.SCREEN_HEIGHT;
import static com.mantkowicz.light.game.Main.SCREEN_WIDTH;
import static com.mantkowicz.light.service.event.GameEventType.PLAYER_CREATED;
import static java.util.Arrays.asList;

public class GameScreen implements Screen {
    private final ResourcesService resourcesService;
    private final GameEventService gameEventService;
    private final World world;
    private GameStage gameStage;
    private MenuStage menuStage;
    private NotificationStage notificationStage;
    private final Board board = new Board();
    private RayHandler rayHandler;
    private List<Feature> features = new ArrayList<>();

    private Label FPS;

    public GameScreen(ResourcesService resourcesService, GameEventService gameEventService, World world) {
        this.resourcesService = resourcesService;
        this.gameEventService = gameEventService;
        this.world = world;
    }

    @Override
    public void show() {
        Viewport viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        gameStage = new GameStage(viewport, resourcesService);
        menuStage = new MenuStage(viewport);
        notificationStage = new NotificationStage(viewport);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(menuStage);
        multiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(multiplexer);

        rayHandler = prepareLights();

        List<Tile> tiles = loadTiles(board);

        // Preparing configuration after loading tiles
        GamePrepareConfiguration configuration = prepareConfiguration();

        for (Tile tile : tiles) {
            gameStage.addActor(tile);
            tile.toBack();

            tile.prepare(configuration);
        }

        if (gameEventService.containsEvent(PLAYER_CREATED)) {
            PlayerCreatedEvent gameEvent = gameEventService.removeEventFromQueue(PLAYER_CREATED, PlayerCreatedEvent.class);
            Player eventObject = gameEvent.getEventObject();
            this.player = eventObject;
            CameraTrackingFeature cameraTrackingFeature = new CameraTrackingFeature(eventObject, configuration);
            addFeature(cameraTrackingFeature);
        }

        addFeature(new MenuFeature(configuration));
        addFeature(new StageFeature(configuration));


        TiledMap map = new TmxMapLoader().load("map2_outer.tmx");
        renderer = new HexagonalTiledMapRenderer(map, 1f);
        renderer.setView((OrthographicCamera) gameStage.getCamera());

        FPS = LabelFactory.createLabel(NotificationStyle.DEFAULT, "60");
        notificationStage.addActor(FPS);
    }

    Player player;
    HexagonalTiledMapRenderer renderer;

    private RayHandler prepareLights() {
        RayHandler rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.02f, 0.02f, 0.02f, 0.5f);
//        rayHandler.setAmbientLight(0.02f, 0.02f, 0.02f, 1f);
        return rayHandler;
    }

    private List<Tile> loadTiles(Board board) {
        TmxTileMapLoaderProperties properties = new TmxTileMapLoaderProperties().setTileMapFileName("map2_outer.tmx");
        TiledMapLoader<TmxTileMapLoaderProperties> tmxTiledMapLoader = new TmxTiledMapLoader(resourcesService);

        return board.loadTiles(tmxTiledMapLoader, properties);
    }

    private GamePrepareConfiguration prepareConfiguration() {
        BoardService boardService = new BoardService(gameEventService, board);
        PhraseService phraseService = new PhraseService();
        OrthographicCamera camera = (OrthographicCamera) gameStage.getCamera();

        return new GamePrepareConfiguration(gameEventService,
                boardService,
                world,
                rayHandler,
                gameStage,
                notificationStage,
                phraseService,
                menuStage,
                resourcesService,
                camera,
                asList(gameStage, notificationStage, menuStage));
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            gameStage.fadeOut();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            gameStage.fadeIn();
        }

        world.step(1 / 60f, 6, 2);

        renderer.render();
        gameStage.getCamera().update();
        renderer.setView((OrthographicCamera) gameStage.getCamera());

        FPS.setPosition(player.getX() + 30, player.getY() + 30);
        FPS.setText(String.valueOf(Gdx.graphics.getFramesPerSecond()));

        gameStage.act(delta);
//        gameStage.draw();

        rayHandler.setCombinedMatrix((OrthographicCamera) gameStage.getCamera());
        rayHandler.updateAndRender();

        notificationStage.act(delta);
        notificationStage.draw();

        menuStage.act(delta);
        menuStage.draw();

        for (Feature feature : features) {
            feature.run(delta);
        }

        gameEventService.updateEventsLivesAndClean();
    }

    protected void addFeature(Feature feature) {
        features.add(feature);
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
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
        gameStage.dispose();
        rayHandler.dispose();
    }
}
