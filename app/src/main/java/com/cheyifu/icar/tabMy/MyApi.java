package com.cheyifu.icar.tabMy;

import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.net.BuildCheYiFuApi;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.net.RequestExecutor;
import com.cheyifu.icar.tabMy.bean.InfoBean;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/2/23.
 */
public class MyApi {
    private  static  MyApi mDefault;
    public static MyApi getDefault(){
        if (null == mDefault) {
            mDefault=new MyApi();
        }
        return mDefault;

    }
    //退出登录
    public RequestExecutor<BaseBean> exit(Map<String,String> map , final CheYiFuHttpCallbackAdapter<BaseBean> callback){
        Call<BaseBean> authCode = BuildCheYiFuApi.getAPIService().exit(map);
        return new RequestExecutor<BaseBean>(authCode,callback).call();
    }
    //意见反馈
    public RequestExecutor<BaseBean> idearBack(Map<String,String> map , final CheYiFuHttpCallbackAdapter<BaseBean> callback){
        Call<BaseBean> authCode = BuildCheYiFuApi.getAPIService().idearBack(map);
        return new RequestExecutor<BaseBean>(authCode,callback).call();
    }
    //获取个人信息
    public RequestExecutor<InfoBean> getInfo(Map<String,String> map , final CheYiFuHttpCallbackAdapter<InfoBean> callback){
        Call<InfoBean> authCode = BuildCheYiFuApi.getAPIService().getInfo(map);
        return new RequestExecutor<InfoBean>(authCode,callback).call();
    }

}
