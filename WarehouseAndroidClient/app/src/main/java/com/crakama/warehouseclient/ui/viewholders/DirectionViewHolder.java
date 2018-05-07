package com.crakama.warehouseclient.ui.viewholders;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.crakama.warehouseclient.R;
import com.crakama.warehouseclient.libmoduleExpandable.ViewHolder.ChildViewHolder;
import com.crakama.warehouseclient.model.Direction;

/**
 * Created by kate on 06/05/2018.
 */

public class DirectionViewHolder extends ChildViewHolder {

    private TextView tvStatus;
    private TextView tvDesc;

    public DirectionViewHolder(View itemView) {
        super(itemView);
        tvDesc = (TextView) itemView.findViewById(R.id.text_view_desc);
        tvStatus  = (TextView) itemView.findViewById(R.id.text_view_status);
    }

    public void bind(Direction biodataChildModel){
        Log.d("TAG", "appears " + getAdapterPosition());
        tvStatus.setText(biodataChildModel.getStatus());
        tvDesc.setText(biodataChildModel.getDescriptions());
    }

}