package com.mantkowicz.light.actor.implementation.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mantkowicz.light.actor.Collecting;
import com.mantkowicz.light.actor.Deposit;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.configuration.api.PlayerConfiguration;
import com.mantkowicz.light.plugin.PlayerCollectResolver;
import com.mantkowicz.light.plugin.implementation.BoardMovementPlugin;
import com.mantkowicz.light.plugin.implementation.CollectPlugin;
import com.mantkowicz.light.plugin.implementation.NotificationPlugin;

import java.util.ArrayList;

import static com.mantkowicz.light.actor.GameActorType.PLAYER;
import static com.mantkowicz.light.actor.implementation.player.PlayerStatus.IDLE;

public class Player extends GameActor implements Collecting {
    private static final String AVATAR_RESOURCE_NAME = "player.png";
    private static final float SPEED = 0.20f;

    private PlayerStatus status;
    private Long lastIdleChange;
    private Deposit deposit;

    public Player(PlayerConfiguration configuration) {
        super(PLAYER, configuration.getBoardService());

        deposit = new Deposit(3, new ArrayList<>());

        Texture avatarTexture = configuration.getResourcesService().getAssetManager().get(AVATAR_RESOURCE_NAME, Texture.class);
        createAvatar(avatarTexture);

        BoardMovementPlugin boardMovementPlugin = new BoardMovementPlugin(this, SPEED, configuration);
        addPlugin(boardMovementPlugin);

        NotificationPlugin notificationPlugin = new NotificationPlugin(this, getNotificationOffset(), configuration);
        addPlugin(notificationPlugin);

        PlayerCollectResolver collectResolver = new PlayerCollectResolver(this, configuration);
        CollectPlugin collectPlugin = new CollectPlugin(collectResolver, configuration);
        addPlugin(collectPlugin);

        setStatus(IDLE);
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        if (IDLE.equals(status)) {
            lastIdleChange = TimeUtils.millis();
        } else {
            lastIdleChange = null;
        }
        this.status = status;
    }

    public long getIdleLength() {
        if (lastIdleChange != null) {
            return TimeUtils.millis() - lastIdleChange;
        }
        return 0;
    }

    @Override
    public Deposit getDeposit() {
        return deposit;
    }
}
