package com.cheyifu.icar.tabHome.activity;

import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.tabHome.iView.IAddCarView;
import com.cheyifu.icar.tabHome.persenter.AddCarPresenter;
import com.cheyifu.icar.tabHome.widget.LicenseKeyboardUtil;
import com.cheyifu.icar.utils.AndroidUtils;
import com.cheyifu.icar.utils.ToastUtil;

public class AddCarActivity extends BaseMVPActivity<AddCarPresenter> implements IAddCarView, View.OnClickListener {


    private RelativeLayout back;
    //    private SafeEdit editText;
    private Button car_submit;

    private TextView inputbox1;

    private TextView inputbox2;

    private TextView inputbox3;

    private TextView inputbox4;

    private TextView inputbox5;

    private TextView inputbox6;

    private TextView inputbox7;


    private LinearLayout boxesContainer;
    private LinearLayout ll_license_input_boxes_content;

    private LicenseKeyboardUtil keyboardUtil;

    private KeyboardView keyboard_view;
    private String carCode;


    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_car);
        initView();
        initListener();
        initData();
    }

    private void initData() {


    }

    private void initListener() {
        back.setOnClickListener(this);
        car_submit.setOnClickListener(this);
        ll_license_input_boxes_content.setOnClickListener(this);

    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("添加车辆");
        back = (RelativeLayout) findViewById(R.id.iv_back);
        car_submit = (Button) findViewById(R.id.car_submit);
//        car_submit.setClickable(false);
        car_submit.setEnabled(false);


        inputbox1 = (TextView) findViewById(R.id.et_car_license_inputbox1);
        inputbox2 = (TextView) findViewById(R.id.et_car_license_inputbox2);
        inputbox3 = (TextView) findViewById(R.id.et_car_license_inputbox3);
        inputbox4 = (TextView) findViewById(R.id.et_car_license_inputbox4);
        inputbox5 = (TextView) findViewById(R.id.et_car_license_inputbox5);
        inputbox6 = (TextView) findViewById(R.id.et_car_license_inputbox6);
        inputbox7 = (TextView) findViewById(R.id.et_car_license_inputbox7);
        boxesContainer = (LinearLayout) findViewById(R.id.ll_car_license_inputbox_content);
        ll_license_input_boxes_content = (LinearLayout) findViewById(R.id.ll_license_input_boxes_content);
        keyboard_view = (KeyboardView) findViewById(R.id.keyboard_view);
//        boxesContainer.setVisibility(View.VISIBLE);
        keyboardUtil = new LicenseKeyboardUtil(keyboard_view, boxesContainer, AddCarActivity.this, new TextView[]{inputbox1, inputbox2, inputbox3,
                inputbox4, inputbox5, inputbox6, inputbox7});
        keyboardUtil.showKeyboard();

        keyboardUtil.setOnInputFinishedListener(new LicenseKeyboardUtil.OnInputFinishedListener() {
            @Override
            public void onInputFinished(String code) {
                ToastUtil.showStringToast("您输入的车牌号码是:" + code);
                keyboard_view.setVisibility(View.INVISIBLE);
                carCode = code;
                car_submit.setEnabled(true);
            }
        });
        keyboardUtil.setOnInputListener(new LicenseKeyboardUtil.OnInput() {
            @Override
            public void onInput() {
                car_submit.setEnabled(false);
            }
        });


    }

    @Override
    protected AddCarPresenter createPresenter() {
        return new AddCarPresenter();
    }

    @Override
    protected IUI getUI() {
        return AddCarActivity.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                AndroidUtils.hideSoftInput(this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                break;
            case R.id.car_submit:


//                String code = editText.getText().toString().trim();
                if (TextUtils.isEmpty(carCode)) {
                    ToastUtil.showStringToast("车牌号码不能为空");
                    return;
                }
//                String carCode = city + code;
//                getPresenter().sendCarCode(carCode);
                getPresenter().sendCarCode(carCode);


                break;
            case R.id.ll_license_input_boxes_content:
                keyboardUtil.showKeyboard();
                break;
        }
    }

    @Override
    public void closeAct() {
        Intent intent = new Intent(AddCarActivity.this, CarMessageActivity.class);
        startActivity(intent);
        this.finish();
    }

}
