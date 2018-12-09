package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.mantkowicz.light.actor.implementation.animal.Dog;
import com.mantkowicz.light.board.tile.AccessibleTile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

public class DogTile extends AccessibleTile {
    public DogTile(AssetManager assetManager) {
        super(assetManager.get("floor-tile.png"));
    }

    @Override
    public void prepare(GamePrepareConfiguration configuration) {
        super.prepare(configuration);

        Dog dog = new Dog(configuration);
        dog.setTile(this);

        configuration.getStage().addActor(dog);
    }
}
