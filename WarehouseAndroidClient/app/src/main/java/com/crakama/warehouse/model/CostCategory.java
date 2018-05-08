package com.crakama.warehouse.model;

import com.crakama.warehouse.libmoduleExpandable.Model.ParentListItem;

import java.util.List;

/**
 * Created by kate on 05/05/2018.
 * ##Gen
 */

public class CostCategory implements ParentListItem {

    private String distanceCategory;
    private String cost;
    private List<Direction> mListChild;

    public CostCategory(String distanceCategory, String cost, List<Direction> mListChild) {
        this.distanceCategory = distanceCategory;
        this.cost = cost;
        this.mListChild = mListChild;
    }

    public CostCategory() {
    }

    public String getDistanceCategory() {
        return distanceCategory;
    }

    public void setDistanceCategory(String distanceCategory) {
        this.distanceCategory = distanceCategory;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String age) {
        this.cost = cost;
    }

    public List<Direction> getmListChild() {
        return mListChild;
    }

    public void setmListChild(List<Direction> mListChild) {
        this.mListChild = mListChild;
    }

    @Override
    public List<?> getChildItemList() {
        return mListChild;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
