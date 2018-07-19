package com.solarexsoft.testmeituanfloat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    public void onGlobalLayout() {
        if (isFirst) {
            mockY = v_mock.getY();
            tv_real.setY(mockY);
            isFirst = false;
        }
    }

    @OnClick(R.id.v_place)
    public void click(){
        Intent intent = new Intent(this, SecondActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            this.startActivity(intent);
        }
    }
}
