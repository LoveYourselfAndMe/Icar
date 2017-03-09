package com.cheyifu.icar.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cheyifu.icar.R;
import com.cheyifu.icar.utils.StatusBarUtil;


/**
 * Activity基类，所有的Activity必须继承这个类.
 * @author penny
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity implements IUI {

    protected boolean mIsActivityDestoryed = false;
    private boolean isPaused;
    private boolean isStoped;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT  >  Build.VERSION_CODES.KITKAT){
//            StatusBarUtil.setColor(this, getResources().getColor(R.color.gray), 0);
//        }
//        if(DeviceUtils.isEMUI()){
//            Logger.i("BaseAct", "==华为手机==");
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        }
        mIsActivityDestoryed = false;
    }



    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
    }

    @Override
    protected void onPause() {
            super.onPause();
        isPaused = true;
    }

    @Override
    protected void onDestroy() {
        mIsActivityDestoryed = true;
        dismissWaitingDialogIfShowing();
        super.onDestroy();
    }

    @Override
    public String getPageName() {
        return getClass().getSimpleName();
    }

    /**
     * isActivityDestoryed:Activity是否已经Destory了. <br/>
     *
     * @return true, Activity已经销毁了，不要在执行任何Fragment事务、显示Dialog等操作
     */
    public boolean isActivityDestoryed() {
        return mIsActivityDestoryed;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 一定不要干掉这段代码
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isStoped = false;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isStoped = false;
    }

    @Override
    protected void onStop() {
        isStoped = true;
        super.onStop();
    }


    @Override
    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public boolean isDestoryed() {
        return isActivityDestoryed();
    }

    @Override
    public boolean isDetached() {
        return isDestoryed();
    }

    @Override
    public boolean isStoped() {
        return isStoped;
    }

    @Override
    public boolean isFragmentHidden() {
        return isDestoryed();
    }

    @Override
    public boolean isVisibleToUser() {
        return !isPaused();
    }


    @Override
    public void showWaitingDialog() {
        dismissWaitingDialogIfShowing();
        if (!isFinishing() && !isActivityDestoryed()) {

        }
    }

    @Override
    public void dismissWaitingDialogIfShowing() {
//        if (!isActivityDestoryed() && dialog != null && dialog.isShowing()) {
//        }
    }

}
