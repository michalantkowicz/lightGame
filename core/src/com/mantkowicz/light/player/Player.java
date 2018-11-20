package com.mantkowicz.light.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.player.plugin.BoardMovementPlugin;
import com.mantkowicz.light.player.plugin.Plugin;
import com.mantkowicz.light.service.event.GameEventService;

import java.util.ArrayList;
import java.util.List;

public class Player extends Group {
    private Image image;
    private GameEventService gameEventService;
    private Tile tile;
    private List<Plugin> pluginsQueue;

    public Player(Texture avatar, GameEventService gameEventService) {
        this.image = new Image(avatar);
        this.gameEventService = gameEventService;
        this.addActor(image);

        pluginsQueue = new ArrayList<>();
        pluginsQueue.add(new BoardMovementPlugin(this, gameEventService));
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        this.setPosition(tile.getX() + tile.getWidth()/2f - image.getWidth()/2f,
                tile.getY() + tile.getHeight()/2f - image.getHeight()/2f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for(Plugin plugin : pluginsQueue) {
            plugin.run();
        }
    }
}
