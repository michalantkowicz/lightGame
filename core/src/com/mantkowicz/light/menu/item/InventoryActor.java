package com.mantkowicz.light.menu.item;

import com.mantkowicz.light.actor.Inventory;
import com.mantkowicz.light.actor.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryActor implements InventoryElement {
    private List<InventoryElement> elements;

    public InventoryActor(Inventory inventory) {
        this.elements = new ArrayList<>();

        for (Item item : inventory.getItems()) {
            ItemActor itemActor = new ItemActor(item);
            itemActor.setParent(this);
            elements.add(itemActor);
        }
    }

    public void addItem(InventoryElement item) {
        elements.add(item);
    }

    @Override
    public void onItemMove(InventoryElement inventoryElement, Item item) {
        for (InventoryElement element : elements) {
            if (!element.equals(inventoryElement)) {
                element.onItemMove(inventoryElement, item);
            }
        }
    }
}
