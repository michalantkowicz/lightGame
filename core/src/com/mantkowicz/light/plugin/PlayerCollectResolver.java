package com.mantkowicz.light.plugin;

import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.configuration.api.PlayerCollectResolverConfiguration;
import com.mantkowicz.light.plugin.implementation.RemoveOnPlayerMovePlugin;
import com.mantkowicz.light.ui.button.ActionButton;
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
        CollectWindow collectWindow = new CollectWindow(configuration, player, collectible);

        OpenCollectWindowButtonListener openCollectWindowButtonListener = new OpenCollectWindowButtonListener(collectWindow, configuration.getCamera(), configuration.getUiStage());

        ActionButton actionButton = new ActionButton(configuration, "COLLECT", openCollectWindowButtonListener);

        RemoveOnPlayerMovePlugin plugin = new RemoveOnPlayerMovePlugin(configuration.getGameEventService(), actionButton);
        actionButton.addActionButtonPlugin(plugin);

        configuration.getUiStage().addActor(actionButton);
    }
}
