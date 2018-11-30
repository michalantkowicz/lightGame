package com.mantkowicz.light.ui.window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mantkowicz.light.actor.Collectible;
import com.mantkowicz.light.actor.Collecting;
import com.mantkowicz.light.actor.Deposit;
import com.mantkowicz.light.actor.Item;

public class CollectWindow extends Table {
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final int PADDING = 40;
    private Collectible collectible;
    private Collecting collecting;

    public CollectWindow(Skin skin, Collecting collecting, Collectible collectible) {
        this.collectible = collectible;
        this.collecting = collecting;
        initialize();

        add(getContent(skin)).width(WIDTH - PADDING).height(HEIGHT - PADDING).expand().center();

        debug();
    }

    private Table getContent(Skin skin) {
        Table table = new Table();

        for (Item item : collectible.getItems()) {
            table.add(new Image(item.getThumbnail())).expand();
        }

        table.row();

        Deposit deposit = collecting.getDeposit();
        for (int i = 0; i < deposit.getCapacity(); i++) {
            table.add().expand();
        }

        table.row();

        table.debug();
        return table;
}

    private void initialize() {
        setTouchable(Touchable.enabled);
        setSize(WIDTH, HEIGHT);
        setPosition(Gdx.graphics.getWidth() / 2f - getWidth() / 2f, Gdx.graphics.getHeight() / 2f - getHeight() / 2f);
        addListener(new StopClickThroughClickListener());
    }
}
