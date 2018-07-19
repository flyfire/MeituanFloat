package com.solarexsoft.testmeituanfloat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 14:51/2018/7/19
 *    Copyright: houruhou
 *    Desc:
 * </pre>
 */
public class SolarexScrollView extends ScrollView {
    OnScrollListener mOnScrollListener;

    public SolarexScrollView(Context context) {
        super(context);
    }

    public SolarexScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SolarexScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(t);
        }
    }

    public void setOnScrollListener(OnScrollListener listener) {
        mOnScrollListener = listener;
    }

    interface OnScrollListener {
        void onScroll(int scrollY);
    }
}
