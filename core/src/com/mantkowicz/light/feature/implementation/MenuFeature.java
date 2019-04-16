package com.mantkowicz.light.feature.implementation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mantkowicz.light.actor.GameBoardActor;
import com.mantkowicz.light.configuration.api.MenuFeatureConfiguration;
import com.mantkowicz.light.feature.Feature;
import com.mantkowicz.light.interaction.Interaction;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.MenuOpenEvent;
import com.mantkowicz.light.service.resources.ResourcesService;
import com.mantkowicz.light.ui.menu.MenuButton;

import java.util.ArrayList;
import java.util.List;

import static com.mantkowicz.light.service.event.GameEventType.MENU_OPEN;

public class MenuFeature implements Feature {
    private static final float MENU_RADIUS = 100f;
    private final float BUTTON_OFFSET_DEGREES = 60f;

    private final Stage stage;
    private final GameEventService gameEventService;
    private final ResourcesService resourcesService;

    public MenuFeature(MenuFeatureConfiguration configuration) {
        this.stage = configuration.getUiStage();
        this.gameEventService = configuration.getGameEventService();
        this.resourcesService = configuration.getResourcesService();
    }

    @Override
    public void run(float delta) {
        if (gameEventService.containsEvent(MENU_OPEN)) {
            MenuOpenEvent event = gameEventService.getEvent(MENU_OPEN, MenuOpenEvent.class, true);
            GameBoardActor actor = event.getEventObject();

            for (MenuButton button : createMenuButtons(actor)) {
                stage.addActor(button);
            }
        }
    }

    private List<MenuButton> createMenuButtons(GameBoardActor actor) {
        List<MenuButton> buttons = new ArrayList<>();
        List<Interaction> interactions = actor.getInteractions();

        for (int i = 0; i < interactions.size(); i++) {
            MenuButton button = new MenuButton(resourcesService, interactions.get(i));

            Vector2 position = calculatePosition(button, i, interactions.size()).add(actor.getCenter());
            button.setPosition(position.x, position.y);

            buttons.add(button);
        }

        return buttons;
    }

    private Vector2 calculatePosition(Button button, int elementIndex, int elementsCount) {
        float firstElementRotation = -(BUTTON_OFFSET_DEGREES * (elementsCount - 1)) / 2f;

        return new Vector2(0, MENU_RADIUS)
                .rotate(firstElementRotation + (elementIndex * BUTTON_OFFSET_DEGREES))
                .sub(button.getWidth() / 2f, button.getHeight() / 2f);
    }
}
