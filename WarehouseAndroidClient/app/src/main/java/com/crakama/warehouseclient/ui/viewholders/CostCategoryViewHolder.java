package com.crakama.warehouseclient.ui.viewholders;

import android.view.View;
import android.widget.TextView;

import com.crakama.warehouseclient.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by kate on 06/05/2018.
 */

public class CostCategoryViewHolder extends GroupViewHolder {
    private TextView genreTitle;

    public CostCategoryViewHolder(View itemView) {
        super(itemView);
        genreTitle = (TextView)itemView.findViewById(R.id.list_item_costcategory);
    }

    public void setGenreName(String name){
        genreTitle.setText(name);
    }
}
