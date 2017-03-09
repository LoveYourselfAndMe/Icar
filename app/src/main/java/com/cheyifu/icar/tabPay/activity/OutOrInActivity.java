package com.cheyifu.icar.tabPay.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.tabMy.iview.AboutView;
import com.cheyifu.icar.tabMy.presenter.AboutPresenter;
import com.cheyifu.icar.utils.AndroidUtils;

public class OutOrInActivity extends BaseMVPActivity<AboutPresenter> implements AboutView {


    private TextView no_car_code;
    private TextView no_car_name;
    private TextView no_car_time;
    private String time;
    private String plate;
    private String name;

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_out);
        time = getIntent().getStringExtra("time");
        plate = getIntent().getStringExtra("palte");
        name = getIntent().getStringExtra("name");

        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("通知");
        RelativeLayout back = (RelativeLayout) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtils.hideSoftInput(OutOrInActivity.this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
            }
        });
        initView();
        initData();
    }

    private void initView() {

        no_car_code = (TextView) findViewById(R.id.no_car_code);
        no_car_name = (TextView) findViewById(R.id.no_car_name);
        no_car_time = (TextView) findViewById(R.id.no_car_time);
    }

    private void initData() {
        no_car_code.setText(plate);
        no_car_name.setText(name);
        no_car_time.setText(time);
    }

    @Override
    protected AboutPresenter createPresenter() {
        return new AboutPresenter();
    }

    @Override
    protected IUI getUI() {
        return OutOrInActivity.this;
    }
}
