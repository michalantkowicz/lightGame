package com.mantkowicz.light.ui.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuButtonListener extends ClickListener {
    private final MenuButton menuButton;

    public MenuButtonListener(MenuButton menuButton) {
        this.menuButton = menuButton;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
        menuButton.addAction(Actions.scaleTo(1.5f, 1.5f, .2f));
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);
        menuButton.addAction(Actions.scaleTo(1f, 1f, .2f));
    }
}
