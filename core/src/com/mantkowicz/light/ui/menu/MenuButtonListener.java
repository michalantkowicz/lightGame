package com.mantkowicz.light.ui.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuButtonListener extends ClickListener {
    private final MenuButton menuButton;

    public MenuButtonListener(MenuButton menuButton) {
        this.menuButton = menuButton;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        menuButton.getInteraction().interact();
    }
}
