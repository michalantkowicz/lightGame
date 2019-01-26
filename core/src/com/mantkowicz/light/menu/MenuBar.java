package com.mantkowicz.light.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mantkowicz.light.actor.Collecting;
import com.mantkowicz.light.actor.implementation.item.Lighter;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.service.resources.ResourcesService;

import static com.mantkowicz.light.game.Main.SCREEN_HEIGHT;

public class MenuBar extends Group {
    private final MenuBarItem item;
    private Collecting actor;
    private Camera camera;

    private ResourcesService resourcesService;

    public MenuBar(ResourcesService resourcesService, Camera camera) {
        super();
        this.resourcesService = resourcesService;
        this.camera = camera;
        debug();

        Image menuBar = new Image(resourcesService.getTexture("menuBarBackground"));
        menuBar.setPosition(-menuBar.getWidth() / 2f, 0);
        addActor(menuBar);


        Table table = new Table();
        table.setSize(430, 150);
        table.setPosition(menuBar.getWidth() / 2f - table.getWidth() - 30, menuBar.getHeight() / 2f - table.getHeight() / 2f);

        item = new MenuBarItem(resourcesService);
        table.add(item).expand();
        table.add(new MenuBarItem(resourcesService)).expand();
        table.add(new MenuBarItem(resourcesService)).expand();

        addActor(table);

        table.debug();
    }

    public MenuBar setActor(Collecting actor) {
        this.actor = actor;
        return this;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(camera.position.x, camera.position.y - SCREEN_HEIGHT / 2f + 40);

        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            item.setItem(new Lighter(new GamePrepareConfiguration(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    resourcesService, camera
                    )));
        }
    }
}
