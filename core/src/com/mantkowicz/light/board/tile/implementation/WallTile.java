package com.mantkowicz.light.board.tile.implementation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.mantkowicz.light.board.tile.NoPathTile;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;

public class WallTile extends NoPathTile {
    public WallTile(AssetManager assetManager) {
        super(assetManager.get("wall-tile.png"));
    }

    @Override
    public void prepare(GamePrepareConfiguration configuration) {
        super.prepare(configuration);

        BodyDef wallBodyDef = new BodyDef();
        Vector2 tilePosition = this.getLeftBottom();
        wallBodyDef.position.set(this.stageToLocalCoordinates(tilePosition));
        Body wallBody = configuration.getWorld().createBody(wallBodyDef);

        ChainShape hexShape = new ChainShape();
        hexShape.createLoop(this.getPolygonVertices());
        wallBody.createFixture(hexShape, 0.0f);

        hexShape.dispose();
    }
}
