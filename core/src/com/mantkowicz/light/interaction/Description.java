package com.mantkowicz.light.interaction;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.notification.factory.LabelFactory;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.mantkowicz.light.actor.GameActorType.DESCRIPTION;
import static com.mantkowicz.light.notification.NotificationStyle.DEFAULT;

public class Description extends GameActor {
    private final int DESCRIPTION_MAX_WIDTH = 200;
    private Label label;

    protected Description(DescriptionDefinition definition) {
        super(DESCRIPTION);

        label = LabelFactory.createLabel(DEFAULT, definition.getBackground(), definition.getDescription());
        if (label.getWidth() > DESCRIPTION_MAX_WIDTH) {
            label.setWrap(true);
            label.setWidth(DESCRIPTION_MAX_WIDTH);
            label.setHeight(label.getPrefHeight());
        }

        addActor(label);

        GameActor actor = definition.getParent();
        setPosition(actor.getCenter().add(actor.getWidth() / 2f, actor.getHeight() / 2f));

        addAction(sequence(delay(2), fadeOut(2)));
    }
}
