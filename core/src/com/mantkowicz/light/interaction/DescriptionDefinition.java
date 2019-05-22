package com.mantkowicz.light.interaction;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.actor.GameActorDefinition;
import com.mantkowicz.light.stage.StageType;

import static com.mantkowicz.light.stage.StageType.NOTIFICATION_STAGE;

public class DescriptionDefinition implements GameActorDefinition<Description> {
    private GameActor parent;
    private String description;
    private Drawable background;

    DescriptionDefinition(GameActor parent, String description, Drawable background) {
        this.parent = parent;
        this.description = description;
        this.background = background;
    }

    public static DescriptionDefinitionBuilder builder() {
        return new DescriptionDefinitionBuilder();
    }

    @Override
    public Description getActor() {
        return new Description(this);
    }

    @Override
    public StageType getTargetStageType() {
        return NOTIFICATION_STAGE;
    }

    public GameActor getParent() {
        return this.parent;
    }

    public String getDescription() {
        return this.description;
    }

    public Drawable getBackground() {
        return this.background;
    }

    public void setParent(GameActor parent) {
        this.parent = parent;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public static class DescriptionDefinitionBuilder {
        private GameActor parent;
        private String description;
        private Drawable background;

        DescriptionDefinitionBuilder() {
        }

        public DescriptionDefinition.DescriptionDefinitionBuilder parent(GameActor parent) {
            this.parent = parent;
            return this;
        }

        public DescriptionDefinition.DescriptionDefinitionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public DescriptionDefinition.DescriptionDefinitionBuilder background(Drawable background) {
            this.background = background;
            return this;
        }

        public DescriptionDefinition build() {
            return new DescriptionDefinition(parent, description, background);
        }

        public String toString() {
            return "DescriptionDefinition.DescriptionDefinitionBuilder(parent=" + this.parent + ", description=" + this.description + ", background=" + this.background + ")";
        }
    }
}
