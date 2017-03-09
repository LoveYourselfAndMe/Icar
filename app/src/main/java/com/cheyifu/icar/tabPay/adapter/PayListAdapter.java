package com.cheyifu.icar.tabPay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.tabHome.model.RecordsBean;
import com.cheyifu.icar.tabPay.activity.PayActivity;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
public class PayListAdapter extends BaseAdapter {
    private List<RecordsBean> mList;
    private Context mContext;
    public PayListAdapter(Context context){
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item, null);
            viewHoler = new ViewHolder();
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (ViewHolder) convertView.getTag();
        }
        viewHoler.car_code= (TextView) convertView.findViewById(R.id.history_car_code);
        viewHoler.car_name= (TextView) convertView.findViewById(R.id.car_name);
        viewHoler.car_time= (TextView) convertView.findViewById(R.id.car_time);
        viewHoler.car_state= (TextView) convertView.findViewById(R.id.car_start);
        viewHoler.wx_pay= (RelativeLayout) convertView.findViewById(R.id.wx_pay);
        viewHoler.rl_locke= (RelativeLayout) convertView.findViewById(R.id.rl_locke);


        final String plate = mList.get(i).plate;
        //车辆锁定状态
        final int lock = mList.get(i).lockStatus;

        viewHoler.wx_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,PayActivity.class);
                intent.putExtra("carCode",plate);
                mContext.startActivity(intent);
            }
        });
        viewHoler.rl_locke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showStringToast("车辆锁定状态");
//                Intent locked = new Intent(mContext, LockeActivity.class);
//                locked.putExtra("carCode",plate);
//                locked.putExtra("carCode",lock);
//                mContext.startActivity(locked);
            }
        });

        viewHoler.car_code.setText(mList.get(i).plate);
        viewHoler.car_name.setText(mList.get(i).parkingName);
        viewHoler.car_time.setText(mList.get(i).inTime);
//        viewHoler.car_state.setText(mList.get(i).lockStatus);






        return convertView;
    }


public static class ViewHolder {
    private RelativeLayout wx_pay;
    private RelativeLayout rl_locke;
    private TextView car_name;
    private TextView car_code;
    private TextView car_time;
    private TextView car_state;


}
}
