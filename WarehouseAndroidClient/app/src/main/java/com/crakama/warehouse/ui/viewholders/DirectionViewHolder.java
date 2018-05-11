package com.crakama.warehouse.ui.viewholders;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.crakama.warehouse.R;
import com.crakama.warehouse.libmoduleExpandable.ViewHolder.ChildViewHolder;
import com.crakama.warehouse.model.Direction;

/**
 * Created by kate on 06/05/2018.
 */
public class DirectionViewHolder extends ChildViewHolder {
    private TextView row;
    private TextView column;
    private TextView height;
    public DirectionViewHolder(View itemView) {
        super(itemView);
        row = (TextView) itemView.findViewById(R.id.text_view_row);
        column  = (TextView) itemView.findViewById(R.id.text_view_col);
        height  = (TextView) itemView.findViewById(R.id.text_view_level);
    }
    public void bind(Direction direction){
        row.setText(direction.getRow());
        column.setText(direction.getColumn());
        height.setText(direction.getHeight());
    }

}