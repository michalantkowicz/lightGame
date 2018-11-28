package com.mantkowicz.light.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.actor.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public abstract class GameActor extends Group {
    private Tile tile;
    private final List<Plugin> pluginsQueue;

    protected GameActor() {
        pluginsQueue = new ArrayList<>();
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

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        Vector2 position = tile.calculatePositionForCenteredActor(this);
        this.setPosition(position.x, position.y);
    }

    protected Vector2 getNotificationOffset() {
        return new Vector2(0, getHeight() / 2f + 20);
    }

    public Vector2 getCenter() {
        return new Vector2(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }
}
