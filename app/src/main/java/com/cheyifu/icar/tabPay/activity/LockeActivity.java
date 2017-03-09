package com.cheyifu.icar.tabPay.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.tabPay.iview.LockView;
import com.cheyifu.icar.tabPay.presenter.LockePresenter;

public class LockeActivity extends BaseMVPActivity<LockePresenter> implements LockView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locke);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {

    }

    @Override
    protected LockePresenter createPresenter() {
        return null;
    }

    @Override
    protected IUI getUI() {
        return null;
    }
}
