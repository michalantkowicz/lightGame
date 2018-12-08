package com.mantkowicz.light.actor.implementation.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mantkowicz.light.actor.BoardGameActor;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.LookAtMeEvent;

public class PlayerClickListener extends ClickListener {
    private BoardGameActor boardGameActor;
    private GameEventService gameEventService;

    public PlayerClickListener(BoardGameActor boardGameActor, GameEventService gameEventService) {
        this.boardGameActor = boardGameActor;
        this.gameEventService = gameEventService;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        gameEventService.addEvent(new LookAtMeEvent(boardGameActor));
    }
}
