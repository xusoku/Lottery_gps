package com.davis.sdj.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.davis.lottery.R;

/**
 * Created by wbz360 on 2015/12/2.
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout
{

    private float xLast;
    private float yLast;
    private float xDistance;
    private float yDistance;

    public MySwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        int [] colors={R.color.colormain,
                R.color.material_green,
                R.color.material_red,
                R.color.material_blue};
        this.setColorSchemeResources(colors);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if(xDistance > yDistance){
                    return false;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
