package com.mantkowicz.light.menu.item;

import com.mantkowicz.light.actor.Item;

public interface InventoryElement {
    void onItemMove(InventoryElement inventoryElement, Item item);
}
