package com.crakama.warehouseclient.ui.viewadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crakama.warehouseclient.R;
import com.crakama.warehouseclient.libmoduleExpandable.Adapter.ExpandableRecyclerAdapter;
import com.crakama.warehouseclient.libmoduleExpandable.Model.ParentListItem;
import com.crakama.warehouseclient.model.CostCategory;
import com.crakama.warehouseclient.model.Direction;
import com.crakama.warehouseclient.ui.viewholders.CostCategoryViewHolder;
import com.crakama.warehouseclient.ui.viewholders.DirectionViewHolder;

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