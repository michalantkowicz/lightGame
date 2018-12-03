package com.mantkowicz.light.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mantkowicz.light.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public abstract class GameActor extends Group {
    private static long ID_COUNTER = 0;

    private final long id;
    private final List<Plugin> pluginsQueue;
    private final GameActorType gameActorType;

    protected GameActor(GameActorType gameActorType) {
        this.gameActorType = gameActorType;
        this.id = ID_COUNTER++;
        pluginsQueue = new ArrayList<>();
    }

    public GameActorType getGameActorType() {
        return gameActorType;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (Plugin plugin : pluginsQueue) {
            plugin.run();
        }
    }

    protected void addPlugin(Plugin plugin) {
        pluginsQueue.add(plugin);
    }

    protected void createAvatar(Texture texture) {
        Image avatar = new Image(texture);
        this.addActor(avatar);
        setSize(avatar.getWidth(), avatar.getHeight());
    }

    public Vector2 getCenter() {
        return new Vector2(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameActor actor = (GameActor) o;

        if (id != actor.id) return false;
        if (pluginsQueue != null ? !pluginsQueue.equals(actor.pluginsQueue) : actor.pluginsQueue != null) return false;
        return getGameActorType() == actor.getGameActorType();
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (pluginsQueue != null ? pluginsQueue.hashCode() : 0);
        result = 31 * result + (getGameActorType() != null ? getGameActorType().hashCode() : 0);
        return result;
    }
}
