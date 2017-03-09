package com.cheyifu.icar.tabOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPFragment;
import com.cheyifu.icar.base.IUI;

/**
 * Created by Administrator on 2017/2/14.
 */
public class OrderFragment extends BaseMVPFragment<OrderPresenter>implements IOrderView {
    @Override
    protected View onCreateViewExecute(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_order, null);
        TextView title= (TextView) inflate.findViewById(R.id.main_title);
        title.setText(R.string.cheweiyuding);

        return inflate;
    }

    @Override
    protected IUI getUI() {
        return OrderFragment.this;
    }

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter();
    }

    @Override
    public String getPageName() {
        return null;
    }
}
