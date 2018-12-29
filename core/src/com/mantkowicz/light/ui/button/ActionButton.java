package com.mantkowicz.light.ui.button;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.configuration.api.UIConfiguration;
import com.mantkowicz.light.plugin.Plugin;
import com.mantkowicz.light.plugin.implementation.CenterActorByCameraPlugin;
import com.mantkowicz.light.plugin.implementation.RemoveOnPlayerMovePlugin;

import static com.mantkowicz.light.actor.GameActorType.ACTION_BUTTON;
import static com.mantkowicz.light.game.Main.SCREEN_WIDTH;

public class ActionButton extends GameActor {
    private final Camera camera;
    private TextButton button;

    public ActionButton(UIConfiguration configuration, String text, EventListener listener) {
        super(ACTION_BUTTON);
        this.camera = configuration.getCamera();

        button = new TextButton(text, configuration.getResourcesService().getSkin(), "action");
        button.addListener(listener);

        addActor(button);

        setSize(button.getWidth(), button.getHeight());

        Plugin centerActorByCameraPlugin = new CenterActorByCameraPlugin(this, configuration.getCamera(), new Vector2(0, SCREEN_WIDTH / 2f));
        addPlugin(centerActorByCameraPlugin);

        RemoveOnPlayerMovePlugin removeOnPlayerMovePlugin = new RemoveOnPlayerMovePlugin(configuration.getGameEventService(), this);
        addPlugin(removeOnPlayerMovePlugin);
    }
}
