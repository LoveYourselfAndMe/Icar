package com.cheyifu.icar.tabPay;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabHome.HomeApi;
import com.cheyifu.icar.tabHome.model.CurrentBean;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/14.
 */
public class PayPresenter extends BasePresenter<IPayView> {
    public  void getCurrentMessage(String string) {
        Map<String ,String> map=new HashMap<>();
        map.put("token",string);
        HomeApi.getDefault().getCurrent(map, new CheYiFuHttpCallbackAdapter<CurrentBean>() {
            @Override
            public void completed(CurrentBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                        getUI().PayResonse(response);

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
