package com.solarexsoft.testmeituanfloat;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener{
    public static final String TAG = "Solarex";

    @BindView(R.id.sv_main)
    NestedScrollView sv_main;
    @BindView(R.id.rl_main)
    RelativeLayout rl_main;
    @BindView(R.id.v_mock)
    View v_mock;
    @BindView(R.id.tv_real)
    TextView tv_real;

    boolean isFirst = true;
    float mockY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rl_main.getViewTreeObserver().addOnGlobalLayoutListener(this);
        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sv_main.setOnScrollChangeListener(new OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int
                        oldScrollY) {
                    if (scrollY < mockY) {
                        tv_real.setY(v_mock.getY());
                    } else {
                        tv_real.setY(scrollY);
                    }
                }
            });
        } else {
            rl_main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    makeRealScroll(sv_main.getScrollY());
                }
            });
        }*/
        /*
        tv_real.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                makeRealScroll(sv_main.getScrollY());
            }
        });
        */
        sv_main.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int
                    oldScrollX, int oldScrollY) {
                if (scrollY < mockY) {
                    tv_real.setY(v_mock.getY());
                } else {
                    tv_real.setY(scrollY);
                }
            }
        });
    }

    private void makeRealScroll(int scrollY) {
        Log.d(TAG, "makeRealScroll scrollY = " + scrollY);
        Log.d(TAG, "makeRealScroll Y:" + v_mock.getY() + ",top: " + v_mock.getTop());
        int realTop = Math.max(scrollY, v_mock.getTop());
        tv_real.layout(0, realTop, tv_real.getWidth(), realTop + tv_real.getHeight());
    }

    @Override
    public void onGlobalLayout() {
        if (isFirst) {
            mockY = v_mock.getY();
            tv_real.setY(mockY);
        }
    }
}
