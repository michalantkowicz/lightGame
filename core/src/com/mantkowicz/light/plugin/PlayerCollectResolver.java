package com.mantkowicz.light.plugin;

import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.configuration.api.PlayerCollectResolverConfiguration;
import com.mantkowicz.light.ui.window.CollectWindow;

public class PlayerCollectResolver implements CollectResolver {
    private PlayerCollectResolverConfiguration configuration;
    private Player player;

    public PlayerCollectResolver(Player player, PlayerCollectResolverConfiguration configuration) {
        this.player = player;
        this.configuration = configuration;
    }

    @Override
    public void resolveCollect(Collectible collectible) {
        CollectWindow collectWindow = new CollectWindow(configuration, player, collectible);
        configuration.getUiStage().addActor(collectWindow);
    }
}
