package com.cheyifu.icar.tabHome.persenter;

import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabHome.HomeApi;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;
import com.cheyifu.icar.R;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabHome.iView.IAddCarView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/15.
 */
public class AddCarPresenter extends BasePresenter<IAddCarView> {
    public void sendCarCode(String carCode){
        Map<String ,String > map=new HashMap<>();
        String token = SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, "");
        map.put("token",token);
        map.put("plate",carCode);
        HomeApi.getDefault().addCarCode(map, new CheYiFuHttpCallbackAdapter<BaseBean>() {
            @Override
            public void completed(BaseBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                        ToastUtil.showStringToast("添加成功");
                        getUI().closeAct();

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
