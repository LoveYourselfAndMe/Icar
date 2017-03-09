package com.cheyifu.icar.tabMy;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabMy.bean.InfoBean;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/14.
 */
public class MyPrensenter extends BasePresenter<IMyView> {
    public void getInfo(String token) {
        Map<String ,String> map=new HashMap<>();
        map.put("token",token);
        MyApi.getDefault().getInfo(map, new CheYiFuHttpCallbackAdapter<InfoBean>() {
            @Override
            public void completed(InfoBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                       getUI().responseBean(response);
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
