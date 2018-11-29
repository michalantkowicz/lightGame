package com.mantkowicz.light.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mantkowicz.light.plugin.Plugin;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public abstract class GameActor extends Group {
    private static long ID_COUNTER = 0;

    private final long id;
    private final List<Plugin> pluginsQueue;
    private final BoardService boardService;
    private GameActorType gameActorType;
    private Tile tile;

    protected GameActor(GameActorType gameActorType, BoardService boardService) {
        this.gameActorType = gameActorType;
        this.id = ID_COUNTER++;
        this.boardService = boardService;
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

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        if (this.tile != null) {
            boardService.unregisterGameActor(this.tile, this);
        }

        this.tile = tile;

        boardService.registerGameActor(tile, this);

        Vector2 position = tile.calculatePositionForCenteredActor(this);
        this.setPosition(position.x, position.y);
    }

    protected Vector2 getNotificationOffset() {
        return new Vector2(0, getHeight() / 2f + 20);
    }

    public Vector2 getCenter() {
        return new Vector2(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }

    @Override
    public boolean remove() {
        if (this.tile != null) {
            boardService.unregisterGameActor(this.tile, this);
        }
        return super.remove();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameActor gameActor = (GameActor) o;

        if (id != gameActor.id) return false;
        return getTile() != null ? getTile().equals(gameActor.getTile()) : gameActor.getTile() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (getTile() != null ? getTile().hashCode() : 0);
        return result;
    }
}
