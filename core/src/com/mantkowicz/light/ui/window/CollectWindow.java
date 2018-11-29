package com.mantkowicz.light.ui.window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.mantkowicz.light.plugin.Collectible;

public class CollectWindow extends Window {
    private Collectible collectible;

    public CollectWindow(Skin skin, Collectible collectible) {
        super("Collect", skin);
        this.collectible = collectible;
        addListener(new ActorGestureListener());

        setSize(200, 200);

        Label nameLabel = new Label("Name:", skin);
        TextField nameText = new TextField("", skin);
        Label addressLabel = new Label("Address:", skin);
        TextField addressText = new TextField("", skin);
        Table table = new Table();
        table.add(nameLabel);
        table.add(nameText).width(100);
        table.row();
        table.add(addressLabel);
        table.add(addressText).width(100);
        this.add(table);
    }
}
