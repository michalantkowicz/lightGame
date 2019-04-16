package com.mantkowicz.light.ui.menu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mantkowicz.light.interaction.Interaction;
import com.mantkowicz.light.service.resources.ResourcesService;
import com.mantkowicz.light.service.resources.ThumbnailType;

public class MenuButton extends Button {
    private final Interaction interaction;

    public MenuButton(ResourcesService resourcesService, Interaction interaction) {
        super(resourcesService.getSkin(), "collect");
//        super(getThumbnail(resourcesService, interaction));
        this.interaction = interaction;

        addListener(new MenuButtonListener(this));
    }

    private static TextureRegionDrawable getThumbnail(ResourcesService resourcesService, Interaction interaction) {
        ThumbnailType thumbnailType = interaction.getThumbnailType();
        TextureRegion thumbnail = resourcesService.getThumbnail(thumbnailType);
        return new TextureRegionDrawable(thumbnail);
    }

    public Interaction getInteraction() {
        return interaction;
    }
}
