package com.mantkowicz.light.screen;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mantkowicz.light.board.Board;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.feature.Feature;
import com.mantkowicz.light.feature.implementation.CameraTrackingFeature;
import com.mantkowicz.light.feature.implementation.FPSFeature;
import com.mantkowicz.light.feature.implementation.MenuFeature;
import com.mantkowicz.light.feature.implementation.StageFeature;
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
    private Board board;
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
        gameStage = new GameStage(viewport, resourcesService);
        menuStage = new MenuStage(viewport);
        notificationStage = new NotificationStage(viewport);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(menuStage);
        multiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(multiplexer);

        rayHandler = prepareLights();


        // Preparing configuration after loading tiles
        GamePrepareConfiguration configuration = prepareConfiguration();

        board = Board.load(configuration, "map.tmx");

        configuration.setBoardService(new BoardService(gameEventService, board));


        for (Tile tile : board.getTiles()) {
            tile.prepare(configuration);
        }

        gameStage.addActor(board);


        if (gameEventService.containsEvent(PLAYER_CREATED)) {
            PlayerCreatedEvent gameEvent = gameEventService.removeEventFromQueue(PLAYER_CREATED, PlayerCreatedEvent.class);
            CameraTrackingFeature cameraTrackingFeature = new CameraTrackingFeature(gameEvent.getEventObject(), configuration);
            addFeature(cameraTrackingFeature);
        }

        addFeature(new MenuFeature(configuration));
        addFeature(new StageFeature(configuration));
        addFeature(new FPSFeature(gameStage, new Vector2(300, 520)));
    }

    private RayHandler prepareLights() {
        RayHandler rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.02f, 0.02f, 0.02f, 2 * 0.5f);
//        rayHandler.setAmbientLight(0.02f, 0.02f, 0.02f, 1f);
        return rayHandler;
    }

    private GamePrepareConfiguration prepareConfiguration() {
        PhraseService phraseService = new PhraseService();
        OrthographicCamera camera = (OrthographicCamera) gameStage.getCamera();

        return new GamePrepareConfiguration(gameEventService,
                null,
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
        gameStage.act(delta);
        gameStage.draw();

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
