package com.mantkowicz.light.plugin;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.configuration.api.PlayerCollectResolverConfiguration;
import com.mantkowicz.light.ui.window.CollectWindow;

public class PlayerCollectResolver implements CollectResolver {
    private Player player;
    private Stage uiStage;
    private Skin skin;

    public PlayerCollectResolver(Player player, PlayerCollectResolverConfiguration configuration) {
        this.player = player;
        this.uiStage = configuration.getUiStage();
        this.skin = configuration.getResourcesService().getSkin();
    }

    @Override
    public void resolveCollect(Collectible collectible) {
        CollectWindow collectWindow = new CollectWindow(skin, player, collectible);
        uiStage.addActor(collectWindow);
    }
}
