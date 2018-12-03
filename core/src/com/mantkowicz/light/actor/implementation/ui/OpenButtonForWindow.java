package com.mantkowicz.light.actor.implementation.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.configuration.api.OpenButtonForWindowConfiguration;
import com.mantkowicz.light.plugin.OpenCollectWindowButtonListener;
import com.mantkowicz.light.plugin.implementation.RemoveOnPlayerMovePlugin;
import com.mantkowicz.light.ui.window.CollectWindow;

import static com.mantkowicz.light.actor.GameActorType.OPEN_BUTTON;

public class OpenButtonForWindow extends GameActor {
    public OpenButtonForWindow(OpenButtonForWindowConfiguration configuration, Collectible collectible, CollectWindow collectWindow) {
        super(OPEN_BUTTON);

        TextButton openButton = getOpenButton(configuration, collectible);
        openButton.addListener(new OpenCollectWindowButtonListener(collectWindow, configuration.getUiStage()));
        addActor(openButton);

        RemoveOnPlayerMovePlugin removeOnPlayerMovePlugin = new RemoveOnPlayerMovePlugin(configuration.getGameEventService(), this);
        addPlugin(removeOnPlayerMovePlugin);
    }

    private TextButton getOpenButton(OpenButtonForWindowConfiguration configuration, Collectible collectible) {
        TextButton openButton = new TextButton("OPEN", configuration.getResourcesService().getSkin());

        Vector2 openButtonPosition = collectible.getTile().getNotificationCenterPosition().sub(openButton.getWidth() / 2f, 0);
        openButton.setPosition(openButtonPosition.x, openButtonPosition.y);

        return openButton;
    }
}
