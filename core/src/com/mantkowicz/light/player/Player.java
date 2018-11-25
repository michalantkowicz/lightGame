package com.mantkowicz.light.player;

import box2dLight.RayHandler;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.player.plugin.BoardMovementPlugin;
import com.mantkowicz.light.player.plugin.NotificationPlugin;
import com.mantkowicz.light.player.plugin.Plugin;
import com.mantkowicz.light.service.event.GameEventService;

import java.util.ArrayList;
import java.util.List;

public class Player extends Group {
    private Image image;
    private GameEventService gameEventService;
    private BoardService boardService;
    private Tile tile;
    private List<Plugin> pluginsQueue;

    public Player(AssetManager assetManager, GameEventService gameEventService, BoardService boardService, RayHandler rayHandler, Stage notificationStage) {
        Texture avatar = assetManager.get("player.png");
        this.image = new Image(avatar);
        setSize(image.getWidth(), image.getHeight());
        this.gameEventService = gameEventService;
        this.addActor(image);

        pluginsQueue = new ArrayList<>();
        pluginsQueue.add(new BoardMovementPlugin(this, gameEventService, boardService));
        Vector2 notificationOffset = new Vector2(0, getHeight() / 2f + 10);
        pluginsQueue.add(new NotificationPlugin(this, notificationOffset, rayHandler, notificationStage));
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        this.setPosition(tile.getX() + tile.getWidth() / 2f - getWidth() / 2f,
                tile.getY() + tile.getHeight() / 2f - getHeight() / 2f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (Plugin plugin : pluginsQueue) {
            plugin.run();
        }
    }

    public Vector2 getCenter() {
        return new Vector2(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }
}
