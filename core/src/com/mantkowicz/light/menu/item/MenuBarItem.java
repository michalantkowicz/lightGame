package com.mantkowicz.light.menu.item;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.service.resources.ResourcesService;

import static com.mantkowicz.light.actor.Collectible.DEFAULT;

public class MenuBarItem extends Group {
    private final Image background, cover, defaultItemImage;
    private Image itemImage = null;
    private Collectible item;

    public MenuBarItem(ResourcesService resourcesService) {
        background = new Image(resourcesService.getTexture("inventoryItemBackground"));
        cover = new Image(resourcesService.getTexture("depositItemCover"));
        defaultItemImage = new Image(resourcesService.getTexture("depositItemDefault"));

        addActor(background);
        addActor(cover);

        setSize(background.getWidth(), background.getHeight());

        debug();

        setItem(DEFAULT);
    }

    public Collectible getItem() {
        return item;
    }

    public MenuBarItem setItem(Collectible item) {
        this.item = item;

        if (item == DEFAULT) {
            setItemImage(defaultItemImage);
        } else {
            setItemImage(new Image(item.getThumbnail()));
        }
        return this;
    }

    private void setItemImage(Image image) {
        image.setPosition(background.getWidth() / 2f - image.getWidth() / 2f, background.getHeight() / 2f - image.getHeight() / 2f);

        if (itemImage != null) {
            itemImage.remove();
        }

        itemImage = image;
        addActor(itemImage);

        itemImage.toFront();
        cover.toFront();
    }
}
