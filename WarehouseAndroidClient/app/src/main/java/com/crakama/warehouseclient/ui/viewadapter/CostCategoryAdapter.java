package com.crakama.warehouseclient.ui.viewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crakama.warehouseclient.R;
import com.crakama.warehouseclient.model.Direction;
import com.crakama.warehouseclient.ui.viewholders.CostCategoryViewHolder;
import com.crakama.warehouseclient.ui.viewholders.DirectionViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by kate on 06/05/2018.
 */

public class CostCategoryAdapter extends ExpandableRecyclerViewAdapter<CostCategoryViewHolder,DirectionViewHolder> {
    public CostCategoryAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public CostCategoryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_costcategory, parent, false);
        return new CostCategoryViewHolder(view);
    }

    @Override
    public DirectionViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_directions, parent, false);
        return new DirectionViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(DirectionViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Direction direction = (Direction) group.getItems().get(childIndex);

        holder.setDirection(direction.getDirection());
    }

    @Override
    public void onBindGroupViewHolder(CostCategoryViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreName(group.getTitle());
    }
}
