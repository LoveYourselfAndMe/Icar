package com.cheyifu.icar.tabHome.persenter;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabHome.HomeApi;
import com.cheyifu.icar.tabHome.iView.IHistoryView;
import com.cheyifu.icar.tabHome.model.CurrentBean;
import com.cheyifu.icar.tabHome.model.StopBean;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/16.
 */
public class HisPresenter extends BasePresenter<IHistoryView> {

    //正在进行
    public  void getCurrentMessage(String string) {
        Map<String ,String> map=new HashMap<>();
        map.put("token",string);
        HomeApi.getDefault().getCurrent(map, new CheYiFuHttpCallbackAdapter<CurrentBean>() {
            @Override
            public void completed(CurrentBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                        getUI().response(response);

                    } else {
                        ToastUtil.showStringToast(response.getStrError());
                        getUI().refreshFinish();
                    }
                }
            }

            @Override
            public void failure(Throwable throwable) {
                ToastUtil.showToast(R.string.net_error);
                getUI().refreshFinish();
            }
        });
    }
    //停车记录
    public void getHistory(String key, int pageNum){
        Map<String ,Object> map=new HashMap<>();
        map.put("token",key);
        map.put("pageNum",pageNum);
        HomeApi.getDefault().getHistory(map, new CheYiFuHttpCallbackAdapter<StopBean>() {
            @Override
            public void completed(StopBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                        getUI().responseStopHistory(response);
                    } else {
                        ToastUtil.showStringToast(response.getStrError());
                        getUI().refreshFinish();
                    }
                }
            }

            @Override
            public void failure(Throwable throwable) {
                ToastUtil.showToast(R.string.net_error);
                getUI().refreshFinish();

            }
        });

    }
}
