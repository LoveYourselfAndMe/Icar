package com.cheyifu.icar.login.presenter;

import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.login.Iview.ILoginView;
import com.cheyifu.icar.login.LoginApi;
import com.cheyifu.icar.login.model.LoginBean;
import com.cheyifu.icar.main.MainActivity;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.utils.Logger;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2017/2/14.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {
    public void login(final String phone, String code) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile",phone);
        map.put("smsCode",code);
        LoginApi.getDefault().login(map, new CheYiFuHttpCallbackAdapter<LoginBean>() {
            @Override
            public void completed(LoginBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                        //保存返回的ID
                        String token = response.getToken();

                        SPUtils.putString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY,token);
//                        MainActivity.launch(getContext(), phone);
                        getUI().closeAct();
                    } else {
                        ToastUtil.showStringToast(response.getStrError());
                    }

                }

            }

            @Override
            public void failure(Throwable throwable) {
                ToastUtil.showToast(R.string.net_error);
            }
        });


    }

    public void checkCode(String phone) {
        Map<String, Object> map = new Hashtable<>();
        map.put("mobile", phone);
        LoginApi.getDefault().getAuthCode(map, new CheYiFuHttpCallbackAdapter<BaseBean>() {
            @Override
            public void completed(BaseBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                        //开始倒计时
//                        getUI().isGetCodeSuccess();
                        ToastUtil.showStringToast("验证码发送成功");
                    } else {
                        ToastUtil.showStringToast(response.getStrError());
                    }
                }
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });

    }

}
