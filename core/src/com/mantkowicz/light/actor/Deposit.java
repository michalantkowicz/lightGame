package com.mantkowicz.light.actor;

import java.util.List;

public class Deposit {
    private int capacity;
    private List<Item> items;

    public Deposit(int capacity, List<Item> items) {
        this.capacity = capacity;
        this.items = items;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public Deposit setItems(List<Item> items) {
        if (items != null && items.size() <= capacity) {
            this.items = items;
        } else {
            throw new IllegalArgumentException("The capacity for this deposit is lesser than " + items.size());
        }
        return this;
    }
}
