package com.cheyifu.icar.tabHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.tabHome.activity.CarAttestationActivity;
import com.cheyifu.icar.tabHome.model.PlatesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
public class MyCarMessageAdapter extends BaseAdapter {
    private List<PlatesBean> mList;
    private Context mContext;

    public MyCarMessageAdapter(Context context) {

        this.mContext = context;
        mList = new ArrayList<>();

    }

    public void setList(List<PlatesBean> plates) {
        this.mList = plates;
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
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHoler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_listview_item, null);
            viewHoler = new ViewHolder();
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (ViewHolder) convertView.getTag();
        }
        viewHoler.rl_approve = (RelativeLayout) convertView.findViewById(R.id.rl_approve);//按钮
        viewHoler.tv_message_car = (TextView) convertView.findViewById(R.id.tv_message_car);//车字
        viewHoler.tv_message = (TextView) convertView.findViewById(R.id.tv_message);//车牌号
        viewHoler.tv_approve = (TextView) convertView.findViewById(R.id.tv_approve);//未认证

        final String trim = mList.get(i).getPlate();
        String substring1 = trim.substring(0, 1);
        String substring = trim.substring(1, trim.length());
        viewHoler.tv_message_car.setText(substring1);
        viewHoler.tv_message.setText(substring);
        if (mList.get(i).getAuth() == 1) {
            viewHoler.tv_approve.setText("已认证");
        } else {
            viewHoler.tv_approve.setText("未认证");
        }


        viewHoler.rl_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CarAttestationActivity.class);
                intent.putExtra("car", trim);
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }


    public static class ViewHolder {
        private RelativeLayout rl_approve;
        private TextView tv_message;
        private TextView tv_message_car;
        private TextView tv_approve;

    }
}
