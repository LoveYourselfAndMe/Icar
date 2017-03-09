package com.cheyifu.icar.tabHome.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabHome.adapter.StopListAdapter;
import com.cheyifu.icar.tabHome.adapter.StopRecordsListAdapter;
import com.cheyifu.icar.tabHome.iView.IHistoryView;
import com.cheyifu.icar.tabHome.model.CurrentBean;
import com.cheyifu.icar.tabHome.model.RecordsBean;
import com.cheyifu.icar.tabHome.model.StopBean;
import com.cheyifu.icar.tabHome.model.StopRecordsBean;
import com.cheyifu.icar.tabHome.persenter.HisPresenter;
import com.cheyifu.icar.tabHome.widget.RefreshListView;
import com.cheyifu.icar.utils.AndroidUtils;
import com.cheyifu.icar.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseMVPActivity<HisPresenter> implements IHistoryView, View.OnClickListener {


    private RelativeLayout back;
    private List<BaseBean> list = new ArrayList<BaseBean>();
    private LinearLayout record;
    private View loading1;
    private View loading2;
    private LinearLayout loading;
    private TextView tv_currnt;
    private TextView tv_his;
    private StopListAdapter adapter;
    private StopRecordsListAdapter recordAdapter;
    private String key;
    private RefreshListView mRefreshListView;
    private int pageNum = 0;
    private int button = 1;

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_history);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        key = SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, "");
        getPresenter().getCurrentMessage(key);
    }

    private void initListener() {
        back.setOnClickListener(this);
        record.setOnClickListener(this);
        loading.setOnClickListener(this);


    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("停车记录");
        back = (RelativeLayout) findViewById(R.id.iv_back);
        record = (LinearLayout) findViewById(R.id.history_record);
//        stop_list = (ListView) findViewById(R.id.stop_list);
        loading1 = findViewById(R.id.loading1);
        loading2 = findViewById(R.id.loading2);
        loading = (LinearLayout) findViewById(R.id.loading);
        tv_currnt = (TextView) findViewById(R.id.tv_currnt);
        tv_his = (TextView) findViewById(R.id.tv_his);

        mRefreshListView = (RefreshListView) findViewById(R.id.refresh_listview);
        initRefresh();
//        adapter = new StopListAdapter(HistoryActivity.this);
//        stop_list.setAdapter(adapter);
    }

    private void initRefresh() {
        // 设置允许用户下拉刷新和上拉加载更多
        mRefreshListView.setEnablePullRefresh(true);

        // 设置列表样式
        mRefreshListView.setCacheColorHint((Color.TRANSPARENT));
        mRefreshListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mRefreshListView.setFastScrollEnabled(true);
        adapter = new StopListAdapter(HistoryActivity.this);
        mRefreshListView.setAdapter(adapter);

        // 设置监听
        mRefreshListView.setOnRefreshOrLoadMoreListener(new RefreshListView.OnRefreshOrLoadMoreListener() {

            @Override
            public void refresh() {

                new Thread(new Runnable() {
                    public void run() {
                        // 下拉刷新的操作 获取数据
                        pageNum=0;
                        if (button == 1) {
                            initData();
                        } else {
                            getPresenter().getHistory(key,pageNum);
                        }
                    }
                }).start();
            }

            @Override
            public void loadMore() {
                new Thread(new Runnable() {
                    public void run() {
                        //停车记录刷新list
                        pageNum = pageNum++;
                        getPresenter().getHistory(key, pageNum);
                    }
                }).start();

            }
        });
    }

    @Override
    protected HisPresenter createPresenter() {
        return new HisPresenter();
    }

    @Override
    protected IUI getUI() {
        return HistoryActivity.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                AndroidUtils.hideSoftInput(this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                break;
            case R.id.history_record:
                loading2.setVisibility(View.VISIBLE);
                loading1.setVisibility(View.INVISIBLE);
                button = 2;
                //停车记录刷新list
                getPresenter().getHistory(key, pageNum);
                break;
            case R.id.loading:
                loading2.setVisibility(View.INVISIBLE);
                loading1.setVisibility(View.VISIBLE);
                button = 1;
                //正在进行刷新list
                getPresenter().getCurrentMessage(key);
                break;
        }

    }

    @Override
    public void response(CurrentBean currentBeen) {
// 刷新完成后隐藏下拉或上拉控件
        mRefreshListView.refreshOrLoadMoreFinish();

        if (currentBeen != null) {
            String count = currentBeen.getCount();
            tv_currnt.setText(count + " )");
//            if (adapter == null) {
                adapter = new StopListAdapter(HistoryActivity.this);
            mRefreshListView.setAdapter(adapter);
//            }
            List<RecordsBean> records = currentBeen.getRecords();
            adapter.setDataList(records);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void refreshFinish() {
        mRefreshListView.refreshOrLoadMoreFinish();
    }

    @Override
    public void responseStopHistory(StopBean bean) {
        mRefreshListView.refreshOrLoadMoreFinish();
        if (bean != null) {
            String count = bean.getCount();
            tv_his.setText(count + " )");
//            if (recordAdapter == null) {
                recordAdapter = new StopRecordsListAdapter(HistoryActivity.this);
                mRefreshListView.setAdapter(recordAdapter);
//            }
            List<StopRecordsBean> records = bean.getRecords();
            recordAdapter.setDataList(records);

        }

    }
}
