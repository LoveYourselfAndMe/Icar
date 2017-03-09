package com.cheyifu.icar.tabMy.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabMy.iview.BackView;
import com.cheyifu.icar.tabMy.presenter.BackPresenter;
import com.cheyifu.icar.utils.AndroidUtils;
import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.utils.SPUtils;

public class IdearBackActivity extends BaseMVPActivity<BackPresenter> implements BackView {


    private EditText et_back;
    private RelativeLayout rl_submit_idear;
    private String key;
    private TextView tv;

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_idear_back);
        initView();

    }


    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("意见反馈");
        RelativeLayout back = (RelativeLayout) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtils.hideSoftInput(IdearBackActivity.this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
            }
        });
        et_back = (EditText) findViewById(R.id.et_back);
        tv = (TextView) findViewById(R.id.tv);
        rl_submit_idear = (RelativeLayout) findViewById(R.id.rl_submit_idear);

        rl_submit_idear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().IdearBack(SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, ""),et_back.getText().toString().trim());
            }
        });
        et_back.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.toString().length();
                tv.setText(String.valueOf(300 - length));

            }
        });
    }

    @Override
    protected BackPresenter createPresenter() {
        return new BackPresenter();
    }

    @Override
    protected IUI getUI() {
        return IdearBackActivity.this;
    }
}
