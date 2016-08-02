package com.barber.shop.queue.system.util;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TabHost;

public class DisableCoordinatorLayout extends CoordinatorLayout {
    private boolean mPassScrolling = true;

    public DisableCoordinatorLayout(Context context) {
        super(context);
    }

    public DisableCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DisableCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return mPassScrolling && super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    public void setPassScrolling(boolean passScrolling) {
        mPassScrolling = passScrolling;
    }
}