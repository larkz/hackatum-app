package com.github.hackatum.resources;

import java.util.ArrayList;
import java.util.HashSet;

public class ShoppingList extends ArrayList<String> {

    private HashSet<String> items;

    public ShoppingList() {
        // generate set of item classes to choose from
    }

    @Override
    public boolean add(String s) {
        if (!items.contains(s)) {
            return false;
        }
        return super.add(s);
    }
}
