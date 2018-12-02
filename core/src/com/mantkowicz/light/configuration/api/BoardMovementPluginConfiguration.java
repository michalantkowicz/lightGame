package com.mantkowicz.light.configuration.api;

import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.service.event.GameEventService;

public interface BoardMovementPluginConfiguration {
    GameEventService getGameEventService();

    BoardService getBoardService();
}
