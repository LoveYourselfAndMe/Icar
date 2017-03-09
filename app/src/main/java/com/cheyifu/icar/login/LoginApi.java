package com.cheyifu.icar.login;

import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.login.model.LoginBean;
import com.cheyifu.icar.net.RequestExecutor;
import com.cheyifu.icar.net.BuildCheYiFuApi;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/2/20.
 */
public class LoginApi {
    private  static  LoginApi mDefault;
    public static LoginApi getDefault(){
        if (null == mDefault) {
            mDefault=new LoginApi();
        }
        return mDefault;

    }
    /**
     * 获取验证码
     * @param map
     * @param callback
     * @return
     */
    public RequestExecutor<BaseBean> getAuthCode(Map<String,Object> map , final CheYiFuHttpCallbackAdapter<BaseBean> callback){
        Call<BaseBean> authCode = BuildCheYiFuApi.getAPIService().getAuthCode(map);
        return new RequestExecutor<BaseBean>(authCode,callback).call();
    }

    /**
     * 登录
     * @param map
     * @param callback
     * @return
    */
    public RequestExecutor<LoginBean> login(Map<String,String> map ,final CheYiFuHttpCallbackAdapter<LoginBean> callback){
        Call<LoginBean> loginCall = BuildCheYiFuApi.getAPIService().login(map);
        return new RequestExecutor<LoginBean>(loginCall,callback).call();
    }
}
