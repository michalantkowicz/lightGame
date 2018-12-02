package com.mantkowicz.light.ui.window;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mantkowicz.light.actor.Item;

import static com.mantkowicz.light.ui.window.CollectWindowItemState.*;

public class CollectWindowItemListener extends ClickListener {
    private static final CollectItem NO_ITEM = null;
    private CollectWindow window;
    private CollectItem collectItem;

    CollectWindowItemListener(CollectWindow window, CollectItem collectItem) {
        this.window = window;
        this.collectItem = collectItem;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        CollectWindowItemState itemState = collectItem.getState();
        if (UNCHOSEN.equals(itemState) && !window.isAnyItemChosen()) {
            collectItem.setState(CHOSEN);
            window.setChosenItem(collectItem);
            window.onItemChosen();
        } else if (ABLE_TO_PUT.equals(itemState) && window.isAnyItemChosen()) {
            CollectItem chosenCollectItem = window.getChosenItem();
            CollectItem targetCollectItem = this.collectItem;

            // Swapping items
            Item chosenItem = chosenCollectItem.removeItem();
            Item targetItem = targetCollectItem.removeItem();

            targetCollectItem.setItem(chosenItem);
            chosenCollectItem.setItem(targetItem);

            window.setChosenItem(NO_ITEM);
            window.onItemUnchosen();
        } else if (CHOSEN.equals(itemState)) {
            collectItem.setState(UNCHOSEN);
            window.setChosenItem(NO_ITEM);
            window.onItemUnchosen();
        }
    }
}
