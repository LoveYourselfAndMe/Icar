package com.cheyifu.icar.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.login.Iview.ILoginView;
import com.cheyifu.icar.login.presenter.LoginPresenter;
import com.cheyifu.icar.main.MainActivity;
import com.cheyifu.icar.utils.AppManager;
import com.cheyifu.icar.utils.CharCheckUtil;
import com.cheyifu.icar.utils.Logger;
import com.cheyifu.icar.utils.NetStateUtil;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;
import com.cheyifu.icar.utils.isFascClick;
import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;

import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends BaseMVPActivity<LoginPresenter> implements ILoginView {

    private static final int MSG_SET_ALIAS = 1001;
    private static final String TAG = "JPush设置===";

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Logger.i(TAG, "Set alias in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;

                default:
                    Logger.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "激光推送Set  alias success";
                    Logger.i(TAG, logs);

                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Logger.i(TAG, logs);
                    if (NetStateUtil.hasNetWorkConection(getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        Logger.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Logger.i(TAG, logs);
            }

        }

    };


    @Bind(R.id.rl_submit)
    RelativeLayout submit;

    @Bind(R.id.activity_login)

    RelativeLayout activity_login;

    @Bind(R.id.tiaokuan)
    LinearLayout tiaokuan;

    @Bind(R.id.et_user)
    EditText et_user;

    @Bind(R.id.et_pass)
    EditText et_pass;

    @Bind(R.id.get_code)
    TextView get_code;
    @Bind(R.id.rl_get_code)
    RelativeLayout rl_get_code;
    private CountDownTimer mTimer;

    @OnClick(R.id.tiaokuan)
    public void tiaokuan() {
        Intent intent=new Intent(this,TosActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_submit)
    public void setSubmit() {
        login();
    }

    @OnClick(R.id.rl_get_code)
    public void setGet_code() {
        String phone = et_user.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showStringToast("请输入手机号");
            return;
        }
        boolean phoneNum = CharCheckUtil.isPhoneNum(phone);
        if (!phoneNum) {
            ToastUtil.showStringToast("手机号码不正确");
            return;
        }
        getCode(phone);

    }

    private String CODE = "code";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    SPUtils.clearByKey(CODE, BaseApplication.getContext());
                    break;
            }
        }
    };

    public void getCode(String phone) {
        isGetCodeSuccess();
        getPresenter().checkCode(phone);

    }


    private void login() {
        //拦截连续点击的事件
        if (isFascClick.isClick()) {
            ToastUtil.showStringToast("不能连续点击");
            return;
        }
        String username = et_user.getText().toString().trim();
        String userpass = et_pass.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            ToastUtil.showStringToast("手机号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(userpass)) {
            ToastUtil.showStringToast("验证码不能为空！");
            return;
        }

        //保存用户名和密码
        Constants.phone = username;
        SPUtils.putString(this, Constants.PHONE_NUM, username);
//        SPUtils.putString(this, "PASSWORD", userpass);
        getPresenter().login(username, userpass);


    }


    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        String ischeck = SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, null);
        if (!TextUtils.isEmpty(ischeck)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        AppManager.getAppManager().getTopWith(LoginActivity.class);
        setContentView(R.layout.activity_login);


//        //隐藏软键盘
//        activity_login.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                return AndroidUtils.hideKeyboardboolean(LoginActivity.this, getCurrentFocus());
//            }
//        });
        //设置推送的别名
        JPushInterface.setAlias(BaseApplication.getContext(), "", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Logger.i("设置别名成功", i + "");
            }
        });


    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected IUI getUI() {
        return LoginActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    @Override
    public void closeAct() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.PHONE_NUM, phone);//保存手机号
//        intent.putExtras(bundle);
        startActivity(intent);
        //设置别名
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, Constants.phone));
        this.finish();
    }



    @Override
    protected void onResume() {
        super.onResume();
        //清空别名
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, ""));
    }

    public void isGetCodeSuccess() {
        mTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                get_code.setText(millisUntilFinished / 1000 + "秒后获取");
                get_code.setClickable(false);
            }

            @Override
            public void onFinish() {
                get_code.setText("获取验证码");
                get_code.setClickable(true);
                stopTimer();
            }
        };
        mTimer.start();
    }

    public void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public static void launch(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
