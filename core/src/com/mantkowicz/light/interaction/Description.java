package com.mantkowicz.light.interaction;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.notification.factory.LabelFactory;

import static com.mantkowicz.light.actor.GameActorType.DESCRIPTION;
import static com.mantkowicz.light.notification.NotificationStyle.DEFAULT;

public class Description extends GameActor {
    private Label label;

    protected Description(DescriptionDefinition definition) {
        super(DESCRIPTION);

        label = LabelFactory.createLabel(DEFAULT, definition.getDescription());
        label.setPosition(-label.getWidth() / 2f, -label.getHeight() / 2f);

        addActor(label);

        setPosition(definition.getPosition().x, definition.getPosition().y);
    }
}
