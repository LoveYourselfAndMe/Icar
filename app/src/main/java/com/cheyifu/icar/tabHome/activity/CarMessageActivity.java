package com.cheyifu.icar.tabHome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabHome.adapter.MyCarMessageAdapter;
import com.cheyifu.icar.tabHome.iView.IMessageView;
import com.cheyifu.icar.tabHome.model.PlatesBean;
import com.cheyifu.icar.tabHome.persenter.MessagePresenter;
import com.cheyifu.icar.utils.AndroidUtils;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;



import java.util.List;

import de.greenrobot.event.EventBus;

public class CarMessageActivity extends BaseMVPActivity<MessagePresenter>implements IMessageView,View.OnClickListener {


    private RelativeLayout back;
    private ListView listView;
    private List<PlatesBean> beanList;
    private MyCarMessageAdapter adapter;
    private RelativeLayout title_add;
    private String token;

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_message);
        EventBus.getDefault().register(this);
        token = SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, "");
        initView();
        initListener();
        initData();
    }

    private void initData() {

        getPresenter().getListMessage(token);

    }

    private void initListener() {
        back.setOnClickListener(this);
        title_add.setOnClickListener(this);
    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("车辆信息");
        title_add = (RelativeLayout) findViewById(R.id.title_add);
        title_add.setVisibility(View.VISIBLE);
        back = (RelativeLayout) findViewById(R.id.iv_back);
        listView = (ListView) findViewById(R.id.lv_list_message);
        adapter = new MyCarMessageAdapter(CarMessageActivity.this);
        listView.setAdapter(adapter);


    }

    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter();
    }

    @Override
    protected IUI getUI() {
        return CarMessageActivity.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                AndroidUtils.hideSoftInput(this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                break;
            case R.id.title_add:
                //判断几个车了
                if (beanList.size() !=0) {
                    if (beanList.size() >= 3) {
                        ToastUtil.showStringToast("车辆达上限,不能继续添加");
                        return;
                    }
                    Intent intent = new Intent(CarMessageActivity.this, AddCarActivity.class);
                    startActivity(intent);
                    this.finish();
                }

                break;
        }
    }

    @Override
    public void responseList(List<PlatesBean> plates) {
        if (adapter == null) {
            adapter=new MyCarMessageAdapter(CarMessageActivity.this);
        }
        this.beanList=plates;
        adapter.setList(plates);
    } @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void onEventMainThread(String str){
        if (str.equals("yangzheng_success")) {
            initData();
        }

    }

}
