package com.mantkowicz.light.configuration;

import box2dLight.RayHandler;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.stage.NotificationStage;

public class GamePrepareConfiguration implements PlayerConfiguration {
    private final AssetManager assetManager;
    private final GameEventService gameEventService;
    private final BoardService boardService;
    private final World world;
    private final RayHandler rayHandler;
    private final Stage stage;
    private final NotificationStage notificationStage;
    private final PhraseService phraseService;

    public GamePrepareConfiguration(AssetManager assetManager,
                                    GameEventService gameEventService,
                                    BoardService boardService,
                                    World world,
                                    RayHandler rayHandler,
                                    Stage stage,
                                    NotificationStage notificationStage,
                                    PhraseService phraseService) {
        this.assetManager = assetManager;
        this.gameEventService = gameEventService;
        this.boardService = boardService;
        this.world = world;
        this.rayHandler = rayHandler;
        this.stage = stage;
        this.notificationStage = notificationStage;
        this.phraseService = phraseService;
    }

    @Override
    public AssetManager getAssetManager() {
        return assetManager;
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
}
