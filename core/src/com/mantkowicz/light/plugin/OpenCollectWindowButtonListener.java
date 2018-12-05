package com.mantkowicz.light.plugin;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mantkowicz.light.ui.window.CollectWindow;

public class OpenCollectWindowButtonListener extends ClickListener {
    private final CollectWindow window;
    private final Camera camera;
    private final Stage uiStage;

    public OpenCollectWindowButtonListener(CollectWindow window, Camera camera, Stage uiStage) {
        this.window = window;
        this.camera = camera;
        this.uiStage = uiStage;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        uiStage.addActor(window);
        Vector2 position = getPosition();
        window.setPosition(position.x, position.y);
    }

    private Vector2 getPosition() {
        Vector3 cameraPosition = camera.position;
        return new Vector2(cameraPosition.x, cameraPosition.y).sub(window.getWidth() / 2f, window.getHeight() / 2f);
    }
}
