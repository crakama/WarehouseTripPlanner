package com.crakama.warehouse.ui.viewadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crakama.warehouse.R;
import com.crakama.warehouse.libmoduleExpandable.Adapter.ExpandableRecyclerAdapter;
import com.crakama.warehouse.libmoduleExpandable.Model.ParentListItem;
import com.crakama.warehouse.model.CostCategory;
import com.crakama.warehouse.model.Direction;
import com.crakama.warehouse.ui.viewholders.CostCategoryViewHolder;
import com.crakama.warehouse.ui.viewholders.DirectionViewHolder;

import java.util.List;


/**
 * Created by kate on 06/05/2018.
 */

public class CostCategoryAdapter extends ExpandableRecyclerAdapter<CostCategoryViewHolder, DirectionViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public CostCategoryAdapter(@NonNull List<? extends ParentListItem> parentItemList, Context mContext) {
        super(parentItemList);
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    @Override
    public CostCategoryViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mLayoutInflater.inflate(R.layout.list_item_costcategory, parentViewGroup, false);
        return new CostCategoryViewHolder(view);
    }

    @Override
    public DirectionViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mLayoutInflater.inflate(R.layout.list_item_directions, childViewGroup, false);
        return new DirectionViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(CostCategoryViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        CostCategory head = (CostCategory) parentListItem;
        parentViewHolder.bind(head);
    }
    @Override
    public void onBindChildViewHolder(DirectionViewHolder childViewHolder, int position, Object childListItem) {
        Direction child = (Direction) childListItem;
        childViewHolder.bind(child);
    }
}