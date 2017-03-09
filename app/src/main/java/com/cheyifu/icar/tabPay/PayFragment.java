package com.cheyifu.icar.tabPay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabHome.adapter.StopListAdapter;
import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPFragment;
import com.cheyifu.icar.tabHome.model.CurrentBean;
import com.cheyifu.icar.tabHome.model.RecordsBean;
import com.cheyifu.icar.tabPay.adapter.PayListAdapter;
import com.cheyifu.icar.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 */
public class PayFragment extends BaseMVPFragment<PayPresenter> implements IPayView {
    private List<BaseBean> list=new ArrayList<BaseBean>();
    private PayListAdapter adapter;

    @Override
    protected View onCreateViewExecute(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_pay, null);
        ListView listView= (ListView) inflate.findViewById(R.id.pay_list);
        TextView title= (TextView) inflate.findViewById(R.id.main_title);
        title.setText(R.string.tingchejiaofei);
        initData();

        adapter = new PayListAdapter(getContext());
        listView.setAdapter(adapter);
        return inflate;
    }

    private void initData() {
     String   key = SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY,"");
        getPresenter().getCurrentMessage(key);
    }

    @Override
    protected IUI getUI() {
        return PayFragment.this;
    }

    @Override
    protected PayPresenter createPresenter() {
        return new PayPresenter();
    }

    @Override
    public String getPageName() {
        return PayFragment.class.getSimpleName();
    }

    @Override
    public void PayResonse(CurrentBean currentBean) {

        if (currentBean != null) {
            String count=currentBean.getCount();
            if (adapter == null) {
                adapter=new PayListAdapter(getContext());
            }
            List<RecordsBean> records = currentBean.getRecords();
            adapter.setDataList(records);
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }

    }
}
