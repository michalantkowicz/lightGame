package com.mantkowicz.light.command;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class RemoveButtonCommand implements Command {
    private final static float DURATION = 0.75f;
    private final TextButton button;

    public RemoveButtonCommand(TextButton button) {
        this.button = button;
    }

    @Override
    public void execute() {
        button.addAction(Actions.sequence(
                Actions.fadeOut(DURATION, Interpolation.sineOut),
                getButtonRemoveAction(button)
        ));
    }

    private static Action getButtonRemoveAction(TextButton button) {
        return new Action() {
            @Override
            public boolean act(float delta) {
                button.remove();
                return true;
            }
        };
    }
}
