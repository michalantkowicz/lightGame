package com.mantkowicz.light.lights;

import box2dLight.Light;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mantkowicz.light.board.tile.Tile;

public abstract class GameLight extends Group {
    protected Light light;
    private LightType lightType;
    private Tile tile;

    public GameLight(LightType lightType) {
        this.lightType = lightType;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        setPosition(tile.getCenter().x, tile.getCenter().y);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        light.setPosition(x, y);
    }

    public void setLightPosition(float x, float y) {
        light.setPosition(x, y);
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        light.setColor(color);
    }

    public void setLightColor(Color color) {
        light.setColor(color);
    }
}
