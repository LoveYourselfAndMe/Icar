package com.cheyifu.icar.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
//import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import cn.jpush.android.api.JPushInterface;


public class BaseApplication extends Application {
    private static Context context;
    private static Handler handler;
    public static BaseApplication myApplication;




    public BaseApplication() {

    }

    public static BaseApplication getInstance() {
        if (myApplication == null) {
            myApplication = new BaseApplication();
        }
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        handler = new Handler();
        Fresco.initialize(context);//初始化Fresco图片加载框架
//        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);



    }



    public static Context getContext() {
        return context;
    }

    /**
     * 获取handler
     *
     * @return
     */
    public static Handler getMainHandler() {
        return handler;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
