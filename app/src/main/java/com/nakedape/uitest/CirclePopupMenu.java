package com.nakedape.uitest;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Nathan on 6/12/2015.
 */
public class CirclePopupMenu extends RelativeLayout {

    public Button center, topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight;
    private Context context;
    private int radius = 120;
    private int center_diam = 100, item_diam = 60;

    public CirclePopupMenu(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        setVisibility(GONE);
        setMinimumWidth(radius + 2 * item_diam);
        setMinimumHeight(radius + 2 * item_diam);
        center = new Button(context);
        center.setBackground(getResources().getDrawable(R.drawable.circle_popup_center));
        addView(center);
        topLeft = new Button(context);
        topLeft.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(topLeft);
        top = new Button(context);
        top.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(top);
        topRight = new Button(context);
        topRight.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(topRight);
        right = new Button(context);
        right.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(right);
        bottomRight = new Button(context);
        bottomRight.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(bottomRight);
        bottom = new Button(context);
        bottom.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(bottom);
        bottomLeft = new Button(context);
        bottomLeft.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(bottomLeft);
        left = new Button(context);
        left.setBackground(getResources().getDrawable(R.drawable.circle_popup_item));
        addView(left);
        for (int i = 1; i < getChildCount(); i++){
            getChildAt(i).setOnTouchListener(itemTouchListener);
        }
        this.setOnTouchListener(itemTouchListener);
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
        int diam = 2 * radius + item_diam;
        setMeasuredDimension(diam, diam);
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom){
        if (changed){
            int w = right - left, h = bottom - top;
            int originX = w / 2, originY = h / 2;
            int a = (int)Math.sqrt(radius*radius / 2);
            getChildAt(0).layout(originX - center_diam / 2, originY - center_diam / 2, originX + center_diam / 2, originY + center_diam / 2);

            int xOffset = (item_diam) / 2, yOffset = (item_diam) / 2;
            originX = w / 2 - xOffset; originY = h / 2 - yOffset;
            getChildAt(1).layout(originX - a, originY - a, originX - a + item_diam, originY - a + item_diam);
            getChildAt(2).layout(originX, originY - radius, originX + item_diam, originY - radius + item_diam);
            getChildAt(3).layout(originX + a, originY - a, originX + a + item_diam, originY - a + item_diam);
            getChildAt(4).layout(originX + radius, originY, originX + radius + item_diam, originY - a + item_diam);
        }
    }

    private int getPredictedWidth(){
        if (getMeasuredWidth() == 0)
            return 2 * radius + item_diam;
        else
            return getMeasuredWidth();
    }

    public void ShowAt(int x, int y, MotionEvent event) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(x - getPredictedWidth() / 2, y - getPredictedWidth() / 2, 0, 0);
        setLayoutParams(params);
        setVisibility(View.VISIBLE);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context,
                R.animator.animator_circle_popup_item_highlight);
        set.setTarget(this);
        set.start();
        super.clearFocus();
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
    }

    private OnTouchListener itemTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.d("CirclePopupMenu", "touch event");
            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context,
                    R.animator.animator_circle_popup_item_highlight);
            set.setTarget(v);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    set.start();
                    if (v.equals(getChildAt(0)))
                        return false;
                    else
                        return true;
                case MotionEvent.ACTION_MOVE:
                    set.start();
                    return true;
                case MotionEvent.ACTION_UP:
                    setVisibility(GONE);
                default:
                    return true;
            }
        }
    };
}
