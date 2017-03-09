package com.cheyifu.icar.tabHome.persenter;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.BasePresenter;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.net.CheYiFuHttpCallbackAdapter;
import com.cheyifu.icar.tabHome.HomeApi;
import com.cheyifu.icar.tabHome.iView.IattestationView;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/4.
 */
public class CarAttestationPresenter extends BasePresenter<IattestationView> {
    public void ren_zheng(String token,String car,String fdj,String cjh){
        Map<String ,String > map=new HashMap<>();
        map.put("token",token);
        map.put("plate",car);
        map.put("carcode",cjh);
        map.put("cardrive",fdj);
        HomeApi.getDefault().renzheng(map, new CheYiFuHttpCallbackAdapter<BaseBean>() {
            @Override
            public void completed(BaseBean response) {
                if (response != null) {
                    if (response.getResult() == 0) {
                        getUI().responseData();
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
