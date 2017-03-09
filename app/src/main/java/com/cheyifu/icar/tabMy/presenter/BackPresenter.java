package com.cheyifu.icar.tabMy.presenter;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabMy.MyApi;
import com.cheyifu.icar.tabMy.iview.BackView;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21.
 */
public class BackPresenter extends BasePresenter<BackView> {

    public void IdearBack(String token, String content) {
        Map<String ,String> map=new HashMap<>();
        map.put("token",token);
        map.put("content",content);
        MyApi.getDefault().idearBack(map, new CheYiFuHttpCallbackAdapter<BaseBean>() {
            @Override
            public void completed(BaseBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                        ToastUtil.showStringToast("感谢您的宝贵意见");
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
