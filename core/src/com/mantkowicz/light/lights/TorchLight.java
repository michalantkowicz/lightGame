package com.mantkowicz.light.lights;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.lights.action.blinking.BlinkingAction;

import static com.badlogic.gdx.scenes.scene2d.actions.RepeatAction.FOREVER;
import static com.mantkowicz.light.lights.action.blinking.BlinkingIntensity.MEDIUM;
import static com.mantkowicz.light.lights.action.blinking.BlinkingSpeed.NORMAL;

public class TorchLight extends Group {
    private static final int RAYS = 50;
    private static final int DISTANCE = 120;
    private static final Color COLOR = new Color(0.4f, 0.4f, 0.4f, 1f);

    private PointLight pointLight;
    private Tile tile;

    public TorchLight(RayHandler rayHandler) {
        pointLight = new PointLight(rayHandler, RAYS);
        pointLight.setDistance(DISTANCE);
        pointLight.setColor(COLOR);

        BlinkingAction blinkingAction = new BlinkingAction(pointLight, MEDIUM, NORMAL);
        addAction(Actions.repeat(FOREVER, blinkingAction));
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
        pointLight.setPosition(x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
