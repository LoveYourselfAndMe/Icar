package com.cheyifu.icar.tabHome.persenter;

import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabHome.HomeApi;
import com.cheyifu.icar.tabHome.iView.IqureyView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
public class QuryPrensenter extends BasePresenter<IqureyView> {
    public void violation_query(String token, String plate) {
        Map<String ,String> map=new HashMap<>();
        map.put("token",token);
        map.put("plate",plate);
        HomeApi.getDefault().violation_query(map, new CheYiFuHttpCallbackAdapter<BaseBean>() {
            @Override
            public void completed(BaseBean response) {

            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }
}
