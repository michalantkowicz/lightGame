package com.mantkowicz.light.actor.implementation.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.Collecting;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.actor.plugin.implementation.BoardMovementPlugin;
import com.mantkowicz.light.actor.plugin.implementation.CollectPlugin;
import com.mantkowicz.light.actor.plugin.implementation.NotificationPlugin;
import com.mantkowicz.light.configuration.PlayerConfiguration;

import static com.mantkowicz.light.actor.GameActorType.PLAYER;
import static com.mantkowicz.light.actor.implementation.player.PlayerStatus.IDLE;

public class Player extends GameActor implements Collecting {
    private static final String AVATAR_RESOURCE_NAME = "player.png";
    private static final float SPEED = 0.20f;

    private PlayerStatus status;
    private Long lastIdleChange;

    public Player(PlayerConfiguration configuration) {
        super(PLAYER, configuration.getBoardService());

        Texture avatarTexture = configuration.getAssetManager().get(AVATAR_RESOURCE_NAME, Texture.class);
        createAvatar(avatarTexture);

        BoardMovementPlugin boardMovementPlugin = new BoardMovementPlugin(this, SPEED, configuration);
        addPlugin(boardMovementPlugin);

        NotificationPlugin notificationPlugin = new NotificationPlugin(this, getNotificationOffset(), configuration);
        addPlugin(notificationPlugin);

        CollectPlugin collectPlugin = new CollectPlugin(this, configuration);
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
    public void collect(Collectible collectible) {
        System.out.println("I've collected " + collectible.getName());
    }
}
