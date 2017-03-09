package com.cheyifu.icar.tabHome.activity;

import android.os.Bundle;
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
import com.cheyifu.icar.tabHome.adapter.MyCarQueryAdapter;
import com.cheyifu.icar.tabHome.iView.IMessageView;
import com.cheyifu.icar.tabHome.iView.IqureyView;
import com.cheyifu.icar.tabHome.model.PlatesBean;
import com.cheyifu.icar.tabHome.persenter.MessagePresenter;
import com.cheyifu.icar.tabHome.persenter.QuryPrensenter;
import com.cheyifu.icar.utils.SPUtils;

import java.util.List;

public class ViolationQuery extends BaseMVPActivity<MessagePresenter> implements IMessageView,View.OnClickListener {


    private RelativeLayout back;
    private String token;
    private ListView listView;
    private MyCarQueryAdapter adapter;

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_violation_query);
        token = SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, "");
        initView();
        initListener();
        initData();


    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("违章查询");
        back = (RelativeLayout) findViewById(R.id.iv_back);
        listView = (ListView) findViewById(R.id.list_query);
        adapter = new MyCarQueryAdapter(ViolationQuery.this);
        listView.setAdapter(adapter);

    }

    private void initData() {
        getPresenter().getListMessage(token);
//       String sp= SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY,"");
//        getPresenter().violation_query(sp,"");

    }

    private void initListener() {
        back.setOnClickListener(this);

    }

    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter();
    }

    @Override
    protected IUI getUI() {
        return ViolationQuery.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }


    }

    @Override
    public void responseList(List<PlatesBean> plates) {
        if (adapter == null) {
            adapter=new MyCarQueryAdapter(ViolationQuery.this);
        }
//        this.beanList=plates;
        adapter.setList(plates);
    }
}
