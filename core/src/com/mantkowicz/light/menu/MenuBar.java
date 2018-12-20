package com.mantkowicz.light.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mantkowicz.light.actor.Collecting;
import com.mantkowicz.light.service.resources.ResourcesService;
import com.mantkowicz.light.ui.window.CollectItem;

import static com.mantkowicz.light.ui.window.CollectItemType.COLLECTIBLE_ITEM;

public class MenuBar extends Table {
    private Collecting actor;
    private Cell inventoryCell;
    private Cell buttonsCell;

    public MenuBar(ResourcesService resourcesService) {
        debug();
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(resourcesService.getAssetManager().get("table_background.png", Texture.class)));
        setBackground(textureRegionDrawable);
        pad(20);

        Table inventoryTable = new Table();
        inventoryTable.add(new CollectItem(resourcesService.getAssetManager(), COLLECTIBLE_ITEM, null));
        inventoryTable.add(new CollectItem(resourcesService.getAssetManager(), COLLECTIBLE_ITEM, null));
        inventoryTable.add(new CollectItem(resourcesService.getAssetManager(), COLLECTIBLE_ITEM, null));

        Table buttonsTable = new Table();
        buttonsTable.add(new Button(resourcesService.getSkin(), "mail"));
        buttonsTable.add(new Button(resourcesService.getSkin(), "settings"));

        inventoryCell = add(inventoryTable).expand();
        buttonsCell = add(buttonsTable).expand();

        inventoryTable.debug();
        buttonsTable.debug();
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        setSize(stage.getWidth(), stage.getHeight() * .15f);
        inventoryCell.minWidth(Value.percentWidth(.8f)).expandY();
        buttonsCell.minWidth(Value.percentWidth(.2f)).expandY();
    }

    public MenuBar setActor(Collecting actor) {
        this.actor = actor;
        return this;
    }
}
