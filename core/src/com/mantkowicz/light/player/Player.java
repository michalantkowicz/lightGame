package com.mantkowicz.light.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.player.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Player extends Group {
    private Image image;
    private Tile tile;
    private List<Plugin> pluginsQueue = new ArrayList<>();

    public Player(Texture avatar) {
        this.image = new Image(avatar);
        this.addActor(image);
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
