package com.mantkowicz.light.plugin;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.command.RemoveButtonCommand;
import com.mantkowicz.light.configuration.api.PlayerCollectResolverConfiguration;
import com.mantkowicz.light.ui.window.CollectWindow;

public class PlayerCollectResolver implements CollectResolver {
    private final PlayerCollectResolverConfiguration configuration;
    private final Player player;

    public PlayerCollectResolver(Player player, PlayerCollectResolverConfiguration configuration) {
        this.player = player;
        this.configuration = configuration;
    }

    @Override
    public void resolveCollect(Collectible collectible) {
        Stage uiStage = configuration.getUiStage();
        CollectWindow collectWindow = new CollectWindow(configuration, player, collectible);
        TextButton openButton = getOpenButton();
        openButton.addListener(new OpenCollectWindowButtonListener(openButton, collectWindow, uiStage));
        player.addCommandToExecuteOnMovement(new RemoveButtonCommand(openButton));
        uiStage.addActor(openButton);
    }

    private TextButton getOpenButton() {
        TextButton openButton = new TextButton("OPEN", configuration.getResourcesService().getSkin());

        Vector2 openButtonPosition = player.getTile().calculatePositionForCenteredActor(openButton);
        openButtonPosition.add(player.getNotificationOffset());
        openButton.setPosition(openButtonPosition.x, openButtonPosition.y);

        return openButton;
    }
}
