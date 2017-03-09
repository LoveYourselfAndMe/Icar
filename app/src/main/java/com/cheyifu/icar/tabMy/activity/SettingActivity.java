package com.cheyifu.icar.tabMy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.login.LoginActivity;
import com.cheyifu.icar.tabMy.iview.ISettingView;
import com.cheyifu.icar.tabMy.presenter.SettingPresenter;
import com.cheyifu.icar.utils.AndroidUtils;
import com.cheyifu.icar.utils.Logger;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;
import com.cheyifu.icar.utils.isFascClick;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import de.greenrobot.event.EventBus;

public class SettingActivity extends BaseMVPActivity<SettingPresenter> implements ISettingView, View.OnClickListener {


    private RelativeLayout exit;
    private RelativeLayout idear_back;
    private RelativeLayout check_version;
    private ToggleButton close_open;
    private TextView tv_vername;

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        initView();
        initListener();
        initData();

    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("设置中心");
        RelativeLayout back = (RelativeLayout) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtils.hideSoftInput(SettingActivity.this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
            }
        });

        //判断推送开启和关闭的状态默认是打开的状态
//        boolean open_close = SPUtils.getBoolean(SettingActivity.this, Constants.PUSSH, true);
//        if (open_close) {
//            close_open.setChecked(true);
//        } else {
//            close_open.setChecked(false);
//        }
        exit = (RelativeLayout) findViewById(R.id.rl_exit);
        idear_back = (RelativeLayout) findViewById(R.id.idear_back);
        check_version = (RelativeLayout) findViewById(R.id.check_version);
        tv_vername = (TextView) findViewById(R.id.tv_vername);
        close_open = (ToggleButton) findViewById(R.id.close_open);


    }

    private void initListener() {
        exit.setOnClickListener(this);
        idear_back.setOnClickListener(this);
        check_version.setOnClickListener(this);

    }

    private void initData() {
        String verName = AndroidUtils.getVerName(BaseApplication.getContext());
        tv_vername.setText("V  " + verName);

//推送的开启和关闭
        close_open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    JPushInterface.resumePush(BaseApplication.getContext());
                    ToastUtil.showStringToast("消息提醒已开启");
                    //保存按钮的状态
                    SPUtils.putBoolean(SettingActivity.this, Constants.PUSSH, true);
                } else {
                    JPushInterface.stopPush(BaseApplication.getContext());
                    boolean pushStopped = JPushInterface.isPushStopped(BaseApplication.getContext());
                    if (pushStopped) {
                        ToastUtil.showStringToast("消息提醒已关闭");
                        //保存按钮的状态
                        SPUtils.putBoolean(SettingActivity.this, Constants.PUSSH, false);
                    }
                }

            }
        });
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected IUI getUI() {
        return SettingActivity.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_exit:
                //拦截连续点击的事件
                if (isFascClick.isClick()) {
                    ToastUtil.showStringToast("不能连续点击");
                    return;
                }
                String token = SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, "");
                getPresenter().exit(token);

                break;
            case R.id.idear_back:
                Intent idear_back = new Intent(SettingActivity.this, IdearBackActivity.class);
                startActivity(idear_back);
                break;
            case R.id.check_version:
                ToastUtil.showStringToast("无须更新");

                break;
        }
    }

    @Override
    public void colaseActivity() {

//        Intent intent=new Intent(SettingActivity.this, LoginActivity.class);
//        startActivity(intent);

        //设置推送的别名
        JPushInterface.setAlias(BaseApplication.getContext(), "", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Logger.i("设置别名成功", i + "");
            }
        });
        SPUtils.clearByKey(Constants.SP_PHONE_NUM_KEY, BaseApplication.getContext());
        SPUtils.clearByKey(Constants.PHONE_NUM, BaseApplication.getContext());
        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        EventBus.getDefault().post("finish");
//        LoginActivity.launch(SettingActivity.this);
        this.finish();


//        System.exit(0);
    }


}
