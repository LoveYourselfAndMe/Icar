package com.cheyifu.icar.tabHome;

import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.net.BuildCheYiFuApi;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.net.RequestExecutor;
import com.cheyifu.icar.tabHome.model.CarMessageBean;
import com.cheyifu.icar.tabHome.model.CurrentBean;
import com.cheyifu.icar.tabHome.model.StopBean;
import com.cheyifu.icar.tabHome.model.imageBean;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/2/20.
 */
public class HomeApi {
    private  static  HomeApi mDefault;
    public static HomeApi getDefault(){
        if (null == mDefault) {
            mDefault=new HomeApi();
        }
        return mDefault;

    }
    /**
     * 添加车辆
     * @param map
     * @param callback
     * @return
     */
    public RequestExecutor<BaseBean> addCarCode(Map<String,String> map , final CheYiFuHttpCallbackAdapter<BaseBean> callback){
        Call<BaseBean> addCall = BuildCheYiFuApi.getAPIService().addCarCode(map);
        return new RequestExecutor<BaseBean>(addCall,callback).call();
    }
    /**
     * 车辆信息
     * @param map
     * @param callback
     * @return
     */
    public RequestExecutor<CarMessageBean> queryCar(Map<String,String> map , final CheYiFuHttpCallbackAdapter<CarMessageBean> callback){
        Call<CarMessageBean> addCall = BuildCheYiFuApi.getAPIService().queryCar(map);
        return new RequestExecutor<CarMessageBean>(addCall,callback).call();
    }
    /**
     * 正在进行
     * @param map
     * @param callback
     * @return
     */
    public RequestExecutor<CurrentBean> getCurrent(Map<String,String> map , final CheYiFuHttpCallbackAdapter<CurrentBean> callback){
        Call<CurrentBean> addCall = BuildCheYiFuApi.getAPIService().getCurrent(map);
        return new RequestExecutor<CurrentBean>(addCall,callback).call();
    }
    /**
     * 停车记录
     * @param map
     * @param callback
     * @return
     */
    public RequestExecutor<StopBean> getHistory(Map<String,Object> map , final CheYiFuHttpCallbackAdapter<StopBean> callback){
        Call<StopBean> addCall = BuildCheYiFuApi.getAPIService().getHistory(map);
        return new RequestExecutor<StopBean>(addCall,callback).call();
    }
    /**
     * 车辆认证
     * @param map
     * @param callback
     * @return
     */
    public RequestExecutor<BaseBean> renzheng(Map<String,String> map , final CheYiFuHttpCallbackAdapter<BaseBean> callback){
        Call<BaseBean> addCall = BuildCheYiFuApi.getAPIService().renzheng(map);
        return new RequestExecutor<BaseBean>(addCall,callback).call();
    }
    /**
     * 违章查询
     * @param map
     * @param callback
     * @return
     */
    public RequestExecutor<BaseBean> violation_query(Map<String,String> map , final CheYiFuHttpCallbackAdapter<BaseBean> callback){
        Call<BaseBean> addCall = BuildCheYiFuApi.getAPIService().violation_query(map);
        return new RequestExecutor<BaseBean>(addCall,callback).call();
    }
    /**
     * 首页图片
     * @param map
     * @param callback
     * @return
     */
    public RequestExecutor<imageBean> images(Map<String,String> map , final CheYiFuHttpCallbackAdapter<imageBean> callback){
        Call<imageBean> addCall = BuildCheYiFuApi.getAPIService().iamges(map);
        return new RequestExecutor<imageBean>(addCall,callback).call();
    }
}
