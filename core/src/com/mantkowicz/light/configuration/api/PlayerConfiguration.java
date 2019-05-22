package com.mantkowicz.light.configuration.api;

import box2dLight.RayHandler;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.service.resources.ResourcesService;
import com.mantkowicz.light.stage.NotificationStage;

public interface PlayerConfiguration extends BoardMovementPluginConfiguration, NotificationPluginConfiguration, CollectPluginConfiguration, PlayerCollectResolverConfiguration {
    @Override
    ResourcesService getResourcesService();

    @Override
    GameEventService getGameEventService();

    @Override
    BoardService getBoardService();

    @Override
    RayHandler getRayHandler();

    @Override
    NotificationStage getNotificationStage();

    @Override
    PhraseService getPhraseService();

    @Override
    Stage getUiStage();

    @Override
    Stage getStage();
}

