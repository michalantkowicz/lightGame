package com.mantkowicz.light.actor.implementation.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.configuration.api.OpenButtonForWindowConfiguration;
import com.mantkowicz.light.plugin.OpenCollectWindowButtonListener;
import com.mantkowicz.light.plugin.implementation.RemoveOnPlayerMovePlugin;
import com.mantkowicz.light.ui.window.CollectWindow;

import static com.mantkowicz.light.actor.GameActorType.ACTION_BUTTON;
import static com.mantkowicz.light.board.tile.TilePoint.RIGHT_TOP_CORNER;

public class OpenButtonForWindow extends GameActor {
    public OpenButtonForWindow(OpenButtonForWindowConfiguration configuration, Collectible collectible, CollectWindow collectWindow) {
        super(ACTION_BUTTON);

        Button openButton = getOpenButton(configuration, collectible);
        openButton.addListener(new OpenCollectWindowButtonListener(collectWindow, configuration.getCamera(), configuration.getUiStage()));
        addActor(openButton);

        RemoveOnPlayerMovePlugin removeOnPlayerMovePlugin = new RemoveOnPlayerMovePlugin(configuration.getGameEventService(), this);
        addPlugin(removeOnPlayerMovePlugin);
    }

    private Button getOpenButton(OpenButtonForWindowConfiguration configuration, Collectible collectible) {
        Button openButton = new Button(configuration.getResourcesService().getSkin(), "collect");

        Tile tile = collectible.getTile();
        Vector2 openButtonPosition = tile.calculatePositionForActorAt(openButton, RIGHT_TOP_CORNER);

        openButton.setPosition(openButtonPosition.x, openButtonPosition.y);

        return openButton;
    }
}
