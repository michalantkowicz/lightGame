package com.mantkowicz.light.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mantkowicz.light.ui.menu.MenuButton;

public class MenuStage extends Stage {
    public MenuStage(Viewport viewport) {
        super(viewport);
    }

    @Override
    public void clear() {
        MenuButton button = getButton();
        if (button != null) {
            button.interact();
        }
        super.clear();
    }

    private MenuButton getButton() {
        Vector2 pointerPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        screenToStageCoordinates(pointerPosition);
        Actor actor = hit(pointerPosition.x, pointerPosition.y, true);
        if (actor != null) {
            return (MenuButton) (actor.getParent()); //FIXME: this is bad style
        } else {
            return null;
        }
    }
}
