package com.cheyifu.icar.tabPay.presenter;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabPay.PayApi;
import com.cheyifu.icar.tabPay.bean.orderBean;
import com.cheyifu.icar.tabPay.bean.payFeeBean;
import com.cheyifu.icar.tabPay.iview.IpayMessageView;
import com.cheyifu.icar.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/24.
 */
public class PayMessagePresenter extends BasePresenter<IpayMessageView> {
    public void getFee(String key, String code) {
        Map<String, String> map = new HashMap<>();
        map.put("token", key);
        map.put("plate", code);
        PayApi.getDefult().payFee(map, new CheYiFuHttpCallbackAdapter<payFeeBean>() {
            @Override
            public void completed(payFeeBean response) {
                if (null != response) {
                    if (response.getResult() == 0) {
                        getUI().responsePayFee(response);
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
    //微信下单
    public void getFeeOrder(String key, String order) {
        Map<String, String> map = new HashMap<>();
        map.put("token", key);
        map.put("orderNo", order);
        PayApi.getDefult().payFeeOrder(map, new CheYiFuHttpCallbackAdapter<orderBean>() {
            @Override
            public void completed(orderBean response) {
                if (null != response) {
                    if (response.getResult() == 0) {
                        getUI().responsePayFeeOrder(response);

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
    //支付宝
    public void getFeeOrder_Ali(String key, String order) {
        Map<String, String> map = new HashMap<>();
        map.put("token", key);
        map.put("orderNo", order);
        PayApi.getDefult().payFeeOrder_Ali(map, new CheYiFuHttpCallbackAdapter<orderBean>() {
            @Override
            public void completed(orderBean response) {
                if (null != response) {
                    if (response.getResult() == 0) {
                        getUI().responsePayFeeOrder_Ali(response);

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
    //支付宝查询支付结果接口
    public void getFeeOrder_Ali_query(String token,String orderNo,String trade_no ){
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("orderNo", orderNo);
        map.put("transactionId", trade_no);
        PayApi.getDefult().payFeeOrder_Ali_query(map, new CheYiFuHttpCallbackAdapter<orderBean>() {
            @Override
            public void completed(orderBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                      ToastUtil.showStringToast("支付成功啦啦啦啦");
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
}
