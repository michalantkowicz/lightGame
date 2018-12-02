package com.mantkowicz.light.plugin;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mantkowicz.light.ui.window.CollectWindow;

class OpenCollectWindowButtonListener extends ClickListener {
    private final TextButton button;
    private final CollectWindow window;
    private final Stage uiStage;

    OpenCollectWindowButtonListener(TextButton button, CollectWindow window, Stage uiStage) {
        this.button = button;
        this.window = window;
        this.uiStage = uiStage;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        uiStage.addActor(window);
        button.remove();
    }
}
