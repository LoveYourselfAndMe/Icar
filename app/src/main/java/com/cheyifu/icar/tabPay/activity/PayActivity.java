package com.cheyifu.icar.tabPay.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabPay.bean.PayResult;
import com.cheyifu.icar.tabPay.bean.PayResultInfo;
import com.cheyifu.icar.tabPay.bean.orderBean;
import com.cheyifu.icar.tabPay.bean.payFeeBean;
import com.cheyifu.icar.tabPay.iview.IpayMessageView;
import com.cheyifu.icar.tabPay.presenter.PayMessagePresenter;
import com.cheyifu.icar.utils.AndroidUtils;
import com.cheyifu.icar.utils.JSONUtil;
import com.cheyifu.icar.utils.Logger;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

public class PayActivity extends BaseMVPActivity<PayMessagePresenter> implements IpayMessageView, View.OnClickListener {

    private static final int SDK_PAY_FLAG = 1;
    private RelativeLayout wx;
    private RelativeLayout zhifubao;
    private RelativeLayout bank;
    private TextView pay_money;
    private TextView pay_time;
    private TextView pay_plate;
    private TextView out_time;
    private TextView in_time;
    private TextView pay_plate_name;
    private String orderNo;
    private static final String APP_ID = "wx4806c87d56eb2304";
    private IWXAPI iwxapi;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    PayResultInfo payResultInfo = JSONUtil.readJson2Entity(resultInfo, PayResultInfo.class);
                    String trade_no = payResultInfo.getAlipay_trade_app_pay_response().trade_no;


                    // 判断resultStatus 为9000则代表支付成功
                    Logger.i("6666666666==", "");
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Logger.i("zzkkkkkkkkkkk==", resultStatus);
                        getPresenter().getFeeOrder_Ali_query(SPUtils.getString(BaseApplication.getContext(),Constants.SP_PHONE_NUM_KEY,""),orderNo,trade_no);
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay);
        initView();
        initListener();
        initData();
        initWenXin();
        //支付宝测试环境,上线去掉
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    }

    private void initListener() {
        wx.setOnClickListener(this);
        zhifubao.setOnClickListener(this);
        bank.setOnClickListener(this);
    }

    private void initData() {
        String carCode = getIntent().getStringExtra("carCode");
        if (carCode != null) {
            getPresenter().getFee(SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, ""), carCode);
        }


    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("停车缴费");
        RelativeLayout back = (RelativeLayout) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtils.hideSoftInput(PayActivity.this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
            }
        });
        wx = (RelativeLayout) findViewById(R.id.wx);
        zhifubao = (RelativeLayout) findViewById(R.id.zhifubao);
        bank = (RelativeLayout) findViewById(R.id.bank);
        pay_money = (TextView) findViewById(R.id.pay_money);
        pay_time = (TextView) findViewById(R.id.pay_time);
        pay_plate = (TextView) findViewById(R.id.pay_plate);
        out_time = (TextView) findViewById(R.id.out_time);
        in_time = (TextView) findViewById(R.id.in_time);
        pay_plate_name = (TextView) findViewById(R.id.pay_plate_name);
    }


    @Override
    protected PayMessagePresenter createPresenter() {
        return new PayMessagePresenter();
    }

    @Override
    protected IUI getUI() {
        return PayActivity.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wx:
//                if (null != orderNo) {
//                    getPresenter().getFeeOrder(SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, ""), orderNo);
//                }
                ToastUtil.showStringToast("微信支付待开发");

                break;
            case R.id.zhifubao:
//                if (null != orderNo) {
//
//                    getPresenter().getFeeOrder_Ali(SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, ""), orderNo);
//                }
                ToastUtil.showStringToast("支付宝待开发");
                break;
            case R.id.bank:
                ToastUtil.showStringToast("银行卡待开发");
                break;
        }
    }

    @Override
    public void responsePayFee(payFeeBean payFeeBean) {
        pay_plate_name.setText(payFeeBean.getParkingName());
        in_time.setText(payFeeBean.getInTime());
        out_time.setText(payFeeBean.getOutTime());
        pay_plate.setText(payFeeBean.getPlate());
        pay_time.setText(payFeeBean.getStayTime());
        pay_money.setText("¥ " + payFeeBean.getFee());
        //后台返回订单号
        orderNo = payFeeBean.getOrderNo();
    }

    @Override
    public void responsePayFeeOrder(orderBean orderBean) {
        String appid = orderBean.getAppid();
        String partnerid = orderBean.getPartnerid();
        String prepayid = orderBean.getPrepayid();
        String noncestr = orderBean.getNoncestr();
        String timestamp = orderBean.getTimestamp();
        String sign = orderBean.getSign();
        PayReq request = new PayReq();
        request.appId = Constants.APP_ID;
        request.partnerId = partnerid;
        request.prepayId = prepayid;
        request.packageValue = "Sign=WXPay";
        request.nonceStr = noncestr;
        request.timeStamp = timestamp;
        request.sign = sign;
        iwxapi.sendReq(request);
    }

    @Override
    public void responsePayFeeOrder_Ali(orderBean orderBean) {
        //支付宝订单
        if (orderBean.getOrderInfo() != null) {
            final String orderInfo = orderBean.getOrderInfo();
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(PayActivity.this);
                    Map<String, String> result = alipay.payV2(orderInfo, true);
                    Log.i("支付宝msp==", result.toString());

                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };

            Thread payThread = new Thread(payRunnable);
            payThread.start();
        }
    }

    private void initWenXin() {
        iwxapi = WXAPIFactory.createWXAPI(BaseApplication.getContext(), Constants.APP_ID);
        iwxapi.registerApp(Constants.APP_ID);
    }
}
