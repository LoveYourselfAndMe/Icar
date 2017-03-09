package com.cheyifu.icar.tabHome;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabHome.model.imageBean;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/14.
 */
public class HomePresenter extends BasePresenter<IHomeView> {
    //获得图片地址
    public void getImages() {
        Map<String, String> map = new Hashtable<>();
//        map.put("token", token);
        HomeApi.getDefault().images(map, new CheYiFuHttpCallbackAdapter<imageBean>() {
            @Override
            public void completed(imageBean response) {

                if (response != null) {
                    getUI().imageSuccess(response.getUrls());
//                    if (response.getResult() == 0) {
//
//                    } else {
//                        ToastUtil.showStringToast(response.getStrError());
//                    }
                }

            }

            @Override
            public void failure(Throwable throwable) {
                ToastUtil.showToast(R.string.net_error);
            }
        });
    }
}
