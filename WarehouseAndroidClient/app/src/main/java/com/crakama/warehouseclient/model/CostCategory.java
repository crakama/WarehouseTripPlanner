package com.crakama.warehouseclient.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by kate on 05/05/2018.
 * ##Gen
 */

public class CostCategory extends ExpandableGroup {
    public CostCategory(String title, List<Direction> items) {
        super(title, items);
    }
}
