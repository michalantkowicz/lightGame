package com.mantkowicz.light.actor;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int capacity;
    private List<Item> items;

    public Inventory(int capacity, List<Item> items) {
        this.capacity = capacity;
        if (items == null) {
            initializeItemsWithNulls(capacity);
        } else {
            this.items = items;
            completeWithNulls(this.items, capacity);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public Inventory setItemsAndCompleteWithNulls(List<Item> inputItems) {
        if (inputItems != null && inputItems.size() <= capacity) {
            this.items = inputItems;
            completeWithNulls(this.items, capacity);
        } else {
            throw new IllegalArgumentException("The capacity for this inventory is lesser than " + inputItems.size());
        }
        return this;
    }

    private void initializeItemsWithNulls(int capacity) {
        this.items = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            items.add(null);
        }
    }

    private void completeWithNulls(List<Item> list, int desiredCapacity) {
        while (list.size() < desiredCapacity) {
            list.add(null);
        }
    }
}
