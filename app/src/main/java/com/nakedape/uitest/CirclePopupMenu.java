package com.nakedape.uitest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Nathan on 6/12/2015.
 */
public class CirclePopupMenu extends RelativeLayout {

    public ImageView center, topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight;
    private Context context;
    private int radius = 100;
    private int itemWidth = 60, itemHeight = 60;

    public CirclePopupMenu(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        setMinimumWidth(300);
        setMinimumHeight(300);
        center = new ImageView(context);
        center.setBackground(getResources().getDrawable(R.drawable.circle_popup_center));
        addView(center);
        topLeft = new ImageView(context);
        topLeft.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(topLeft);
        top = new ImageView(context);
        top.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(top);
        topRight = new ImageView(context);
        topRight.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(topRight);
        right = new ImageView(context);
        right.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(right);
        bottomRight = new ImageView(context);
        bottomRight.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(bottomRight);
        bottom = new ImageView(context);
        bottom.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(bottom);
        bottomLeft = new ImageView(context);
        bottomLeft.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(bottomLeft);
        left = new ImageView(context);
        left.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(left);
    }


    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        PositionViews(w, h);
        super.onSizeChanged(w, h, oldw, oldh);
    }
    private void PositionViews(int w, int h){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(120, 120);
        int xOffset = (itemWidth) / 2, yOffset = (itemHeight) / 2;
        params.setMargins(w / 2 - 60, h / 2 - 60, 0, 0);
        center.setLayoutParams(params);

        int originX = w / 2, originY = h / 2;
        int a = -(int)Math.sqrt(radius*radius / 2);

        params = new RelativeLayout.LayoutParams(itemWidth, itemHeight);
        params.setMargins(originX - a - xOffset, originY + a - yOffset, 0, 0);
        topLeft.setLayoutParams(params);

        params = new RelativeLayout.LayoutParams(itemWidth, itemHeight);
        params.setMargins(originX - xOffset, originY - radius - yOffset, 0, 0);
        top.setLayoutParams(params);

        params = new RelativeLayout.LayoutParams(itemWidth, itemHeight);
        params.setMargins(originX + a - xOffset, originY + a - yOffset, 0, 0);
        topRight.setLayoutParams(params);

        params = new RelativeLayout.LayoutParams(itemWidth, itemHeight);
        params.setMargins(originX + radius - xOffset, originY - yOffset, 0, 0);
        right.setLayoutParams(params);

        params = new RelativeLayout.LayoutParams(itemWidth, itemHeight);
        params.setMargins(originX + a - xOffset, originY - a - yOffset, 0, 0);
        bottomRight.setLayoutParams(params);

        params = new RelativeLayout.LayoutParams(itemWidth, itemHeight);
        params.setMargins(originX - xOffset, originY + radius - yOffset, 0, 0);
        bottom.setLayoutParams(params);

        params = new RelativeLayout.LayoutParams(itemWidth, itemHeight);
        params.setMargins(originX - a - xOffset, originY - a - yOffset, 0, 0);
        bottomLeft.setLayoutParams(params);

        params = new RelativeLayout.LayoutParams(itemWidth, itemHeight);
        params.setMargins(originX - radius - xOffset, originY - yOffset, 0, 0);
        left.setLayoutParams(params);
    }
}
