package com.mantkowicz.light.board.tile;

import box2dLight.RayHandler;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.service.event.GameEventService;

public class GamePrepareConfiguration {
    private AssetManager assetManager;
    private GameEventService gameEventService;
    private BoardService boardService;
    private World world;
    private RayHandler rayHandler;
    private Stage stage;
    private Stage notificationStage;

    public GamePrepareConfiguration(AssetManager assetManager,
                                    GameEventService gameEventService,
                                    BoardService boardService,
                                    World world,
                                    RayHandler rayHandler,
                                    Stage stage,
                                    Stage notificationStage) {
        this.assetManager = assetManager;
        this.gameEventService = gameEventService;
        this.boardService = boardService;
        this.world = world;
        this.rayHandler = rayHandler;
        this.stage = stage;
        this.notificationStage = notificationStage;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public World getWorld() {
        return world;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public Stage getStage() {
        return stage;
    }

    public GameEventService getGameEventService() {
        return gameEventService;
    }

    public Stage getNotificationStage() {
        return notificationStage;
    }

    public BoardService getBoardService() {
        return boardService;
    }
}
