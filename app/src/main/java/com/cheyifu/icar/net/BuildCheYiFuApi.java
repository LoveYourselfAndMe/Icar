package com.cheyifu.icar.net;

import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.net.interceptor.LogInterceptor;
import com.cheyifu.icar.net.interceptor.NetCacheInterceptor;
import com.cheyifu.icar.utils.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by penny on 2016/9/8 0008.
 */
public class BuildCheYiFuApi {
    private static final String TAG = BuildCheYiFuApi.class.getSimpleName();
//    public final static String URL_BASE_RELEASE = "http://192.168.1.99:8889/app/"; // API接口调用的公共网址
    public final static String URL_BASE_RELEASE = "http://www.cheyifu2016.com:8890/app/"; // API接口调用的公共网址
//    public final static String URL_BASE_RELEASE = "http://www.cheyifu2016.com:8889/app/"; // API接口调用的公共网址
//    public final static String URL_BASE_RELEASE = "http://192.168.1.3:8898/app/"; // API接口调用的公共网址


    private static Retrofit retrofit;


    public static APIService getAPIService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE_RELEASE) //设置Base的访问路径
                    .addConverterFactory(GsonConverterFactory.create()) //设置默认的解析库：Gson
                    .client(defaultOkHttpClient())
                    .build();
        }
        return retrofit.create(APIService.class);
    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.writeTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        client.readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
        client.connectTimeout(15 * 1000, TimeUnit.MILLISECONDS);
        //设置缓存路径

        File httpCacheDirectory = new File(BaseApplication.getContext().getCacheDir(), "CheYiFu");
        Logger.i(TAG, httpCacheDirectory.toString());

        if (isDebug()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(logging);
        }


        Cache cache = new Cache(httpCacheDirectory, 50 * 1024 * 1024);
        client.cache(cache);
        //设置拦截器
        client.addInterceptor(new LogInterceptor());
        client.addNetworkInterceptor(new NetCacheInterceptor());
        client.addInterceptor(new NetCacheInterceptor());
        return client.build();
    }


    public static boolean isDebug() {
        return true;
    }
}
