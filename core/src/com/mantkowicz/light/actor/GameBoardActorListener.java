package com.mantkowicz.light.actor;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.MenuOpenEvent;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;

public class GameBoardActorListener extends InputListener {
    private final GameBoardActor gameBoardActor;
    private final GameEventService gameEventService;
    private MenuOpenAction menuOpenAction;

    public GameBoardActorListener(GameBoardActor gameBoardActor, GameEventService gameEventService) {
        this.gameBoardActor = gameBoardActor;
        this.gameEventService = gameEventService;
    }
    
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        menuOpenAction = new MenuOpenAction();
        gameBoardActor.addAction(delay(2f, menuOpenAction));
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        gameBoardActor.removeAction(menuOpenAction);
    }

    private class MenuOpenAction extends Action {
        @Override
        public boolean act(float delta) {
            gameEventService.addEvent(new MenuOpenEvent(gameBoardActor));
            return true;
        }
    }
}
