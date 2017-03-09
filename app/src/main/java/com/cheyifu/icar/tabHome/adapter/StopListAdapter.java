package com.cheyifu.icar.tabHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.R;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.tabHome.model.CurrentBean;
import com.cheyifu.icar.tabHome.model.RecordsBean;
import com.cheyifu.icar.tabPay.activity.LockeActivity;
import com.cheyifu.icar.tabPay.activity.PayActivity;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
public class StopListAdapter extends BaseAdapter {
    private List<RecordsBean> mList;
    private Context mContext;
    public StopListAdapter( Context context){
        this.mContext=context;
        this.mList=new ArrayList<>();


    }

    public void setDataList(List<RecordsBean> recordsBeen) {
        this.mList=recordsBeen;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        ViewHolder viewHoler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_record, null);
            viewHoler = new ViewHolder();
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (ViewHolder) convertView.getTag();
        }

        viewHoler.car_code= (TextView) convertView.findViewById(R.id.history_car_code);
        viewHoler.car_name= (TextView) convertView.findViewById(R.id.car_name);
        viewHoler.car_time= (TextView) convertView.findViewById(R.id.car_time);
        viewHoler.car_state= (TextView) convertView.findViewById(R.id.car_start);


        viewHoler.car_code.setText(mList.get(i).plate);
        viewHoler.car_name.setText(mList.get(i).parkingName);
        viewHoler.car_time.setText(mList.get(i).inTime);
//        viewHoler.car_state.setText(mList.get(i).lockStatus);






        return convertView;
    }


public static class ViewHolder {
    private TextView car_name;
    private TextView car_code;
    private TextView car_time;
    private TextView car_state;


}
}
