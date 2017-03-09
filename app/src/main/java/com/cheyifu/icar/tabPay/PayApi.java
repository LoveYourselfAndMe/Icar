package com.cheyifu.icar.tabPay;

import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.net.BuildCheYiFuApi;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.net.RequestExecutor;
import com.cheyifu.icar.tabPay.bean.orderBean;
import com.cheyifu.icar.tabPay.bean.payFeeBean;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/2/24.
 */
public class PayApi {
    private static  PayApi payApi;
    public static PayApi getDefult(){
        if (null==payApi) {
            payApi=new PayApi();
        }
        return payApi;
    }

    //支付费用页面
    public RequestExecutor<payFeeBean> payFee(Map<String,String> map , final CheYiFuHttpCallbackAdapter<payFeeBean> callback){
        Call<payFeeBean> authCode = BuildCheYiFuApi.getAPIService().payFee(map);
        return new RequestExecutor<payFeeBean>(authCode,callback).call();
    }
    //微信下单
    public RequestExecutor<orderBean> payFeeOrder(Map<String,String> map , final CheYiFuHttpCallbackAdapter<orderBean> callback){
        Call<orderBean> authCode = BuildCheYiFuApi.getAPIService().payFeeOrder(map);
        return new RequestExecutor<orderBean>(authCode,callback).call();
    }
    //支付宝
    public RequestExecutor<orderBean> payFeeOrder_Ali(Map<String,String> map , final CheYiFuHttpCallbackAdapter<orderBean> callback){
        Call<orderBean> authCode = BuildCheYiFuApi.getAPIService().payFeeOrder_ALI(map);
        return new RequestExecutor<orderBean>(authCode,callback).call();
    }
    //支付宝查询支付结果
    public RequestExecutor<orderBean> payFeeOrder_Ali_query(Map<String,String> map , final CheYiFuHttpCallbackAdapter<orderBean> callback){
        Call<orderBean> authCode = BuildCheYiFuApi.getAPIService().payFeeOrder_ALI_query(map);
        return new RequestExecutor<orderBean>(authCode,callback).call();
    }
}
