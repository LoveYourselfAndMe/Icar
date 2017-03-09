package com.cheyifu.icar.net.interceptor;


import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.utils.Logger;
import com.cheyifu.icar.utils.NetStateUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetCacheInterceptor implements Interceptor {
    public static String TAG = NetCacheInterceptor.class.getSimpleName();
    @Override
    public Response intercept(Chain chain) throws IOException {
        boolean netWorkConection = NetStateUtil.hasNetWorkConection(BaseApplication.getContext());
        Request request = chain.request();
        if (!netWorkConection) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);
        if (netWorkConection) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            Logger.i(TAG, cacheControl);
            response.newBuilder()
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .header("Cache-Control", cacheControl)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 7;
            response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return response;
    }
}

