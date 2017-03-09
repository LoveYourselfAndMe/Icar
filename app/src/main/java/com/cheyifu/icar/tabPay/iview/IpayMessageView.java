package com.cheyifu.icar.tabPay.iview;

import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.tabPay.bean.orderBean;
import com.cheyifu.icar.tabPay.bean.payFeeBean;

/**
 * Created by Administrator on 2017/2/24.
 */
public interface IpayMessageView extends IUI {
    void responsePayFee(payFeeBean payFeeBean);
    void responsePayFeeOrder(orderBean orderBean);
    void responsePayFeeOrder_Ali(orderBean orderBean);
}
