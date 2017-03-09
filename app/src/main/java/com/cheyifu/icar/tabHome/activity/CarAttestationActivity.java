package com.cheyifu.icar.tabHome.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabHome.iView.IattestationView;
import com.cheyifu.icar.tabHome.persenter.CarAttestationPresenter;
import com.cheyifu.icar.utils.AndroidUtils;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;

import de.greenrobot.event.EventBus;


public class CarAttestationActivity extends BaseMVPActivity<CarAttestationPresenter> implements IattestationView,View.OnClickListener {


    private RelativeLayout rl_right;
    private EditText fdj;
    private EditText cjh;
    private EditText cz_name;
    private RelativeLayout back;
    private String car;
    private TextView cphm;

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_attestation);
        car = getIntent().getStringExtra("car");
        initView();
        initListener();
        initData();


    }

    private void initData() {

    }

    private void initView() {
        setContentView(R.layout.activity_car_attestation);
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("车辆认证");
        back = (RelativeLayout) findViewById(R.id.iv_back);
        cphm = (TextView) findViewById(R.id.cphm);
        if (car != null) {
            cphm.setText(car);
        }
        cz_name = (EditText) findViewById(R.id.cz_name);
        cjh = (EditText) findViewById(R.id.cjh);
        fdj = (EditText) findViewById(R.id.fdj);
        rl_right = (RelativeLayout) findViewById(R.id.rl_right);

    }

    private void initListener() {
        back.setOnClickListener(this);
        rl_right.setOnClickListener(this);

    }

    @Override
    protected CarAttestationPresenter createPresenter() {
        return new CarAttestationPresenter();
    }

    @Override
    protected IUI getUI() {
        return CarAttestationActivity.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                AndroidUtils.hideSoftInput(this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                break;
            case R.id.rl_right:
                if (TextUtils.isEmpty(cjh.getText().toString().trim())) {
                    ToastUtil.showStringToast("请输入车架号");
                    return;
                }
                if (TextUtils.isEmpty(fdj.getText().toString().trim())) {
                    ToastUtil.showStringToast("请输入发动机号");
                    return;
                }
                getPresenter().ren_zheng(SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY,""),cphm.getText().toString().trim(),fdj.getText().toString().trim(),cjh.getText().toString().trim());
                break;
        }
    }

    @Override
    public void responseData() {
        //发消息更新界面
        EventBus.getDefault().post("yangzheng_success");
        this.finish();
    }


}
