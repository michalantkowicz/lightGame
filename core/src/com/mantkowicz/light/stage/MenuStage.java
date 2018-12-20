package com.mantkowicz.light.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mantkowicz.light.actor.Collecting;
import com.mantkowicz.light.menu.MenuBar;
import com.mantkowicz.light.service.resources.ResourcesService;

public class MenuStage extends Stage {
    private final MenuBar menuBar;

    public MenuStage(ResourcesService resourcesService, Viewport viewport) {
        super(viewport);
        menuBar = new MenuBar(resourcesService);
        menuBar.setPosition(0, 0);
        addActor(menuBar);
    }

    public void setCollectingActor(Collecting actor) {
        menuBar.setActor(actor);
    }
}
