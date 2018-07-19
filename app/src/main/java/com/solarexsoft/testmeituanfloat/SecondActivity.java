package com.solarexsoft.testmeituanfloat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 14:50/2018/7/19
 *    Copyright: houruhou
 *    Desc:
 * </pre>
 */
public class SecondActivity extends AppCompatActivity implements SolarexScrollView.OnScrollListener{
    public static final String TAG = "Solarex";

    @BindView(R.id.ssv_main)
    SolarexScrollView ssv_main;
    @BindView(R.id.rl_main)
    RelativeLayout rl_main;
    @BindView(R.id.v_mock)
    View v_mock;
    @BindView(R.id.tv_real)
    TextView tv_real;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        ssv_main.setOnScrollListener(this);
        ssv_main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 初次滑动覆盖
                onScroll(ssv_main.getScrollY());
            }
        });
    }

    @Override
    public void onScroll(int scrollY) {
        Log.d(TAG, "scrollY: " + scrollY + ",y: " + v_mock.getY() + ",top: " + v_mock.getTop());
        int realTop = Math.max(scrollY, v_mock.getTop());
        tv_real.setY(realTop);
    }
}
