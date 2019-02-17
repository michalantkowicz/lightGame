package com.mantkowicz.light.menu.item;

import com.mantkowicz.light.actor.Item;

public class ItemActor implements InventoryElement {
    private InventoryActor parent;
    private Item item;
    private InventoryElementState state;

    public ItemActor(Item item) {
        this.item = item;
    }

    public InventoryActor getParent() {
        return parent;
    }

    public ItemActor setParent(InventoryActor parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public void onItemMove(InventoryElement inventoryElement, Item item) {

    }
}
