package com.davis.sdj.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by davis on 16/6/16.
 */
public class MyScrollViewSmooh extends ScrollView {

    private ScrollViewListener scrollViewListener = null;
    private indexScrollViewListener indexScrollViewListener = null;

    public MyScrollViewSmooh(Context context) {
        super(context);
    }

    public MyScrollViewSmooh(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyScrollViewSmooh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    public void setIndexScrollViewListener(MyScrollViewSmooh.indexScrollViewListener indexScrollViewListener) {
        this.indexScrollViewListener = indexScrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
        if (indexScrollViewListener != null) {
            if(getScrollY() + getHeight() - getPaddingTop()-getPaddingBottom() == getChildAt(0).getHeight()){
                indexScrollViewListener.onScrolledToBottom();
            }
        }
    }

    public interface ScrollViewListener {

        void onScrollChanged(MyScrollViewSmooh scrollView, int x, int y, int oldx, int oldy);

    }
    public interface indexScrollViewListener {

        void onScrolledToBottom();

    }
}
