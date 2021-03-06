package com.mantkowicz.light.configuration;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.configuration.api.BasicConfiguration;
import com.mantkowicz.light.configuration.api.BoardConfiguration;
import com.mantkowicz.light.configuration.api.PlayerConfiguration;
import com.mantkowicz.light.configuration.api.StagesConfiguration;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.service.resources.ResourcesService;
import com.mantkowicz.light.stage.AbstractStage;
import com.mantkowicz.light.stage.NotificationStage;

import java.util.List;

public class GamePrepareConfiguration implements PlayerConfiguration, BoardConfiguration, StagesConfiguration, BasicConfiguration {
    private final GameEventService gameEventService;

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    private BoardService boardService;
    private final World world;
    private final RayHandler rayHandler;
    private final Stage stage;
    private final NotificationStage notificationStage;
    private final Stage uiStage;
    private final PhraseService phraseService;
    private final ResourcesService resourcesService;
    private final OrthographicCamera camera;
    private final List<AbstractStage> stages;

    public GamePrepareConfiguration(GameEventService gameEventService,
                                    BoardService boardService,
                                    World world,
                                    RayHandler rayHandler,
                                    Stage stage,
                                    NotificationStage notificationStage,
                                    PhraseService phraseService,
                                    Stage uiStage,
                                    ResourcesService resourcesService,
                                    OrthographicCamera camera,
                                    List<AbstractStage> stages) {
        this.gameEventService = gameEventService;
        this.boardService = boardService;
        this.world = world;
        this.rayHandler = rayHandler;
        this.stage = stage;
        this.notificationStage = notificationStage;
        this.phraseService = phraseService;
        this.uiStage = uiStage;
        this.resourcesService = resourcesService;
        this.camera = camera;
        this.stages = stages;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public GameEventService getGameEventService() {
        return gameEventService;
    }

    @Override
    public BoardService getBoardService() {
        return boardService;
    }

    @Override
    public NotificationStage getNotificationStage() {
        return notificationStage;
    }

    @Override
    public PhraseService getPhraseService() {
        return phraseService;
    }

    @Override
    public Stage getUiStage() {
        return uiStage;
    }

    public ResourcesService getResourcesService() {
        return resourcesService;
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    public List<AbstractStage> getStages() {
        return stages;
    }
}
