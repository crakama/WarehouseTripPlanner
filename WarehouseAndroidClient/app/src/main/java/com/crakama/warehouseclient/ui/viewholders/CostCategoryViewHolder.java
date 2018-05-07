package com.crakama.warehouseclient.ui.viewholders;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crakama.warehouseclient.R;
import com.crakama.warehouseclient.libmoduleExpandable.ViewHolder.ParentViewHolder;
import com.crakama.warehouseclient.model.CostCategory;

/**
 * Created by kate on 06/05/2018.
 */

public class CostCategoryViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;


    private TextView tvName;
    private TextView tvAge;
    private ImageView ivCollapse;
    private LinearLayout llItem;


    public CostCategoryViewHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.textview_name);
        tvAge = (TextView) itemView.findViewById(R.id.textview_age);
        ivCollapse = (ImageView) itemView.findViewById(R.id.image_view_collapse);
        llItem = (LinearLayout) itemView.findViewById(R.id.ll_item);
    }
    public void bind(CostCategory biodataHead){

        tvName.setText(biodataHead.getName());
        tvAge.setText(biodataHead.getAge());

        int position = getAdapterPosition();


        if(position%2 == 0) {
            llItem.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            llItem.setBackgroundColor(Color.parseColor("#f1f1f1"));
        }

    }

    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
                ivCollapse.setImageResource(R.drawable.ic_content_remove);
            } else {
                ivCollapse.setImageResource(R.drawable.ic_content_add);
            }
        }
    }
    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            RotateAnimation rotateAnimation;
            if (expanded) { // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else { // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
            ivCollapse.startAnimation(rotateAnimation);
        }
    }
}