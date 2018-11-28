package com.mantkowicz.light.configuration;

import box2dLight.RayHandler;
import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.phrase.PhraseService;
import com.mantkowicz.light.stage.NotificationStage;

public interface PlayerConfiguration extends BoardMovementPluginConfiguration, NotificationPluginConfiguration, CollectPluginConfiguration {
    AssetManager getAssetManager();

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
}

