package com.crakama.warehouseclient.ui.viewholders;

import android.view.View;
import android.widget.TextView;

import com.crakama.warehouseclient.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by kate on 06/05/2018.
 */

public class DirectionViewHolder extends ChildViewHolder {
    private TextView artistName;

    public DirectionViewHolder(View itemView) {
        super(itemView);
        artistName = (TextView)itemView.findViewById(R.id.list_item_directions);
    }

    public void setDirection(String name){
        artistName.setText(name);
    }
}
