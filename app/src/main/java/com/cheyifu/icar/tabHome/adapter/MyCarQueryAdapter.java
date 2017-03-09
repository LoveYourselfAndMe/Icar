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
import com.cheyifu.icar.tabHome.activity.QueryActivity;
import com.cheyifu.icar.tabHome.model.PlatesBean;
import com.cheyifu.icar.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
public class MyCarQueryAdapter extends BaseAdapter {
    private List<PlatesBean> mList;
    private Context mContext;

    public MyCarQueryAdapter(Context context) {

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
    public View getView(final int i, View convertView, ViewGroup parent) {
        ViewHolder viewHoler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_listview_item, null);
            viewHoler = new ViewHolder();
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (ViewHolder) convertView.getTag();
        }
        viewHoler.rl_query = (RelativeLayout) convertView.findViewById(R.id.rl_query);//按钮
        viewHoler.tv_message_car = (TextView) convertView.findViewById(R.id.tv_message_car);//车字
        viewHoler.tv_message = (TextView) convertView.findViewById(R.id.tv_message);//车牌号
        viewHoler.tv_query = (TextView) convertView.findViewById(R.id.tv_query);//未认证

        final String trim = mList.get(i).getPlate();
        String substring1 = trim.substring(0, 1);
        String substring = trim.substring(1, trim.length());
        viewHoler.tv_message_car.setText(substring1);
        viewHoler.tv_message.setText(substring);

        viewHoler.tv_query.setText("查询");

//        if (mList.get(i).getAuth() == 1) {
//            viewHoler.tv_query.setText("查询");
//            viewHoler.rl_query.setVisibility(View.VISIBLE);
//        } else {
//            viewHoler.rl_query.setVisibility(View.INVISIBLE);
//            viewHoler.tv_query.setText("不可查询");
//        }


        viewHoler.rl_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.get(i).getAuth() == 1) {
                    Intent intent = new Intent(mContext, QueryActivity.class);
                    intent.putExtra("queryCar", trim);
                    mContext.startActivity(intent);
                } else {
                    ToastUtil.showStringToast("请认证后再查询");
                }

            }
        });


        return convertView;
    }


    public static class ViewHolder {
        private RelativeLayout rl_query;
        private TextView tv_message;
        private TextView tv_message_car;
        private TextView tv_query;

    }
}
