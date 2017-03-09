package com.cheyifu.icar.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.permissions.PermissionsManager;
import com.cheyifu.icar.tabMy.MyFragment;
import com.cheyifu.icar.tabOrder.OrderFragment;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;
import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.permissions.PermissionsResultAction;
import com.cheyifu.icar.tabHome.HomeFragment;
import com.cheyifu.icar.tabPay.PayFragment;
import com.cheyifu.icar.utils.UpdateManager;


import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements ImainView, View.OnClickListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private PayFragment payFragment;
    private MyFragment myFragment;
    private RadioButton homeButton;
    private RadioButton myButton;
    private RadioButton payButton;
    private RadioButton orderButton;
    private long exitTime = 0;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

//    public static void launch(Context mContext, String phone) {
//        Intent intent = new Intent(mContext, MainActivity.class);
////        Bundle bundle = new Bundle();
////        bundle.putString(Constants.PHONE_NUM, phone);//保存手机号
////        intent.putExtras(bundle);
//        mContext.startActivity(intent);
//    }

//    @Bind(R.id.bt_add)
//    ImageButton button;

//    @OnClick(R.id.bt_add)
//    public void setButton() {
//        ToastUtil.showStringToast("添加了");
//    }


    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        fragmentManager = this.getSupportFragmentManager();
        initView();
        initListener();
        initData();
        requestPermissions();

//        initUpdata();



    }


    private void initUpdata() {
        if (checkTodayUpdate()) {
//            checkUpdateVerson();

        //版本号不一致更新
        UpdateManager updateManager = new UpdateManager(MainActivity.this);
        //下载地址
        String URL = Constants.DownloadAPK;
        updateManager.checkUpdateInfo(URL, "为了您更好的体验！请您及时更新...");
        }
    }

    //检查权限
    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                //Toast.makeText(WxActivity.this, "All permissions have been granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permission) {
                //Toast.makeText(WxActivity.this, "Permission " + permission + " has been denied", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void initView() {
        homeButton = (RadioButton) findViewById(R.id.first);
        orderButton = (RadioButton) findViewById(R.id.second);
        payButton = (RadioButton) findViewById(R.id.three);
        myButton = (RadioButton) findViewById(R.id.four);
    }

    private void initData() {
        homeFragment = new HomeFragment();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(R.id.main_fragment, homeFragment);
        beginTransaction.commit();
        homeButton.setChecked(true);
    }

    private void initListener() {
        homeButton.setOnClickListener(this);
        orderButton.setOnClickListener(this);
        payButton.setOnClickListener(this);
        myButton.setOnClickListener(this);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected IUI getUI() {
        return MainActivity.this;
    }

    @Override
    public void onClick(View v) {
        fragmentTransaction = fragmentManager.beginTransaction();
        goneFragment(fragmentTransaction);
        switch (v.getId()) {
            case R.id.first:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.main_fragment, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case R.id.second:
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    fragmentTransaction.add(R.id.main_fragment, orderFragment);
                } else {
                    fragmentTransaction.show(orderFragment);
                }
                break;
            case R.id.three:
                if (payFragment == null) {
                    payFragment = new PayFragment();
                    fragmentTransaction.add(R.id.main_fragment, payFragment);
                } else {
                    fragmentTransaction.show(payFragment);
                }
                break;
            case R.id.four:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    fragmentTransaction.add(R.id.main_fragment, myFragment);
                } else {
                    fragmentTransaction.show(myFragment);
                }
                break;

        }
        fragmentTransaction.commit();
    }

    private void goneFragment(FragmentTransaction beginTransaction) {
        if (homeFragment != null) {
            beginTransaction.hide(homeFragment);
        }
        if (orderFragment != null) {
            beginTransaction.hide(orderFragment);
        }
        if (payFragment != null) {
            beginTransaction.hide(payFragment);
        }
        if (myFragment != null) {
            beginTransaction.hide(myFragment);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
           ToastUtil.showStringToast("再按一次退出应用");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void onEventMainThread(String event) {
        if (event.equals("finish")) {
            this.finish();
        }

    }
    /**
     * 根据日期检查是否需要进行软件升级
     *
     * @throws Exception
     */
    private boolean checkTodayUpdate() {

        String updateTime = SPUtils.getString(BaseApplication.getContext(), "UPDATE", "");

        if ("".equals(updateTime)) {  //刚安装的新版本，设置详细信息

            return true;
        }
        try {
            //判断3天内不检查升级

            if ((new Date().getTime() - simpleDateFormat.parse(updateTime).getTime()) / 1000 / 3600 / 24 < 3) {
                return false;
            } else if (updateTime.equalsIgnoreCase(simpleDateFormat.format(new Date()))) {//判断今天是否检查过升级
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
