package com.cheyifu.icar.tabHome.persenter;

import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabHome.HomeApi;
import com.cheyifu.icar.tabHome.iView.IMessageView;
import com.cheyifu.icar.tabHome.model.CarMessageBean;
import com.cheyifu.icar.tabHome.model.PlatesBean;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/15.
 */
public class MessagePresenter extends BasePresenter<IMessageView> {
    public void getListMessage(String token) {
        Map<String ,String> map=new HashMap<>();
        map.put("token",token);
        HomeApi.getDefault().queryCar(map, new CheYiFuHttpCallbackAdapter<CarMessageBean>() {
            @Override
            public void completed(CarMessageBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                        List<PlatesBean> plates = response.getPlates();
                        getUI().responseList(plates);

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
