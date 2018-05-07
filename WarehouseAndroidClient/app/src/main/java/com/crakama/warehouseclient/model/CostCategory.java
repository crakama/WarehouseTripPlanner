package com.crakama.warehouseclient.model;

import com.crakama.warehouseclient.libmoduleExpandable.Model.ParentListItem;

import java.util.List;

/**
 * Created by kate on 05/05/2018.
 * ##Gen
 */

public class CostCategory implements ParentListItem {

    private String name;
    private String age;
    private List<Direction> mListChild;

    public CostCategory(String name, String age, List<Direction> mListChild) {
        this.name = name;
        this.age = age;
        this.mListChild = mListChild;
    }

    public CostCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
