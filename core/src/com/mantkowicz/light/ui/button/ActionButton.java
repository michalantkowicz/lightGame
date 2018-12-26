package com.mantkowicz.light.ui.button;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.configuration.api.UIConfiguration;
import com.mantkowicz.light.plugin.Plugin;

import static com.mantkowicz.light.actor.GameActorType.ACTION_BUTTON;

public class ActionButton extends GameActor {
    private final Camera camera;
    private TextButton button;

    public ActionButton(UIConfiguration configuration, String text, EventListener listener) {
        super(ACTION_BUTTON);
        this.camera = configuration.getCamera();

        button = new TextButton(text, configuration.getResourcesService().getSkin(), "action");
        button.addListener(listener);

        addActor(button);
        setPosition(getPosition());
    }

    public void addActionButtonPlugin(Plugin plugin) {
        addPlugin(plugin);
    }

    private Vector2 getPosition() {
        Vector3 cameraPosition = camera.position;
        return new Vector2(cameraPosition.x, cameraPosition.y).sub(button.getWidth() / 2f, button.getHeight() / 2f);
    }
}
