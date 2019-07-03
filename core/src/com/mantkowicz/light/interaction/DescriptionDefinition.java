package com.mantkowicz.light.interaction;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.actor.GameActorDefinition;
import com.mantkowicz.light.stage.StageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.mantkowicz.light.stage.StageType.NOTIFICATION_STAGE;

@Getter
@Setter
@Builder
public class DescriptionDefinition implements GameActorDefinition<Description> {
    private GameActor parent;
    private String description;
    private Drawable background;

    @Override
    public Description getActor() {
        return new Description(this);
    }

    @Override
    public StageType getTargetStageType() {
        return NOTIFICATION_STAGE;
    }
}
