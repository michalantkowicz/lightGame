package com.mantkowicz.light.interaction;

import com.badlogic.gdx.math.Vector2;
import com.mantkowicz.light.actor.GameActorDefinition;
import com.mantkowicz.light.stage.StageType;

import static com.mantkowicz.light.stage.StageType.NOTIFICATION_STAGE;

public class DescriptionDefinition implements GameActorDefinition<Description> {
    private Vector2 position;
    private String description;

    @Override
    public Description getActor() {
        return new Description(this);
    }

    @Override
    public StageType getTargetStageType() {
        return NOTIFICATION_STAGE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public DescriptionDefinition setPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DescriptionDefinition setDescription(String description) {
        this.description = description;
        return this;
    }
}
