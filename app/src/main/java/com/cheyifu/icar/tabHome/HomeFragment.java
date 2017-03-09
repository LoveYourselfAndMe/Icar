package com.cheyifu.icar.tabHome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabHome.activity.HistoryActivity;
import com.cheyifu.icar.tabHome.activity.ViolationQuery;
import com.cheyifu.icar.utils.ImageLoadFresco;
import com.cheyifu.icar.utils.LocalFileUtils;
import com.cheyifu.icar.utils.ToastUtil;
import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPFragment;
import com.cheyifu.icar.tabHome.activity.AddCarActivity;
import com.cheyifu.icar.zxing.android.CaptureActivity;
import com.kcode.autoscrollviewpager.view.AutoScrollViewPager;
import com.kcode.autoscrollviewpager.view.BaseViewPagerAdapter;
import com.kevin.loopview.AdLoopView;
import com.kevin.loopview.internal.LoopData;
import com.kevin.loopview.utils.JsonTool;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 */
public class HomeFragment extends BaseMVPFragment<HomePresenter> implements IHomeView, View.OnClickListener {

    private AdLoopView mLoopView;
    private LinearLayout addCar;
    private LinearLayout history;
    private LinearLayout defy;
    private LinearLayout qr;

    //二维码扫描
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    private AutoScrollViewPager mViewPager;
    private List<String> mDatas;
    private String[] paths = {"http://www.cheyifu2016.com/images/Appimg/banner_1.jpg",
            "http://www.cheyifu2016.com/images/Appimg/banner_2.jpg",
            "http://www.cheyifu2016.com/images/Appimg/banner_3.jpg"};
    private BaseViewPagerAdapter<String> adapter;


    @Override
    protected View onCreateViewExecute(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, null);
//        mLoopView = (AdLoopView) inflate.findViewById(R.id.main_act_adloopview);
        mViewPager = (AutoScrollViewPager) inflate.findViewById(R.id.viewPager);


        addCar = (LinearLayout) inflate.findViewById(R.id.add_car);
        history = (LinearLayout) inflate.findViewById(R.id.stop_history);
        defy = (LinearLayout) inflate.findViewById(R.id.defy_query);
        qr = (LinearLayout) inflate.findViewById(R.id.qr);
        initListener();
//        initRotateView();
        initViewPager();
        return inflate;
    }

    private void initViewPager() {
        adapter = new BaseViewPagerAdapter<String>(BaseApplication.getContext(), listener) {
            @Override
            public void loadImage(ImageView view, int position, String url) {
//                new ImageLoadFresco.LoadImageFrescoBuilder(BaseApplication.getContext(),view,url).build();

                Picasso.with(BaseApplication.getContext()).load(url).into(view);
            }

            @Override
            public void setSubTitle(TextView textView, int position, String s) {
                textView.setText(s);
            }
        };
        mViewPager.setAdapter(adapter);
        //访问网络地址
        getPresenter().getImages();
//        adapter.add(datas);
//        adapter.add(initData());
    }

    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener listener = new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener<String>() {

        @Override
        public void onItemClick(int position, String url) {
//            Toast.makeText(BaseApplication.getContext(),
//                    url, Toast.LENGTH_SHORT).show();
        }
    };

    private void initListener() {
        addCar.setOnClickListener(this);
        history.setOnClickListener(this);
        defy.setOnClickListener(this);
        qr.setOnClickListener(this);
    }


    private List<String> initData() {
        List<String> data = new ArrayList<>();
        Picture picture;
        for (int i = 0; i < paths.length; i++) {
            data.add(paths[i]);
        }
        return data;
    }

    /**
     * 初始化LoopView
     *
     * @return void
     */
    private void initRotateView() {
        String json = LocalFileUtils.getStringFormAsset(BaseApplication.getContext(), "loopview_date.json");
        LoopData loopData = JsonTool.toBean(json, LoopData.class);
        if (null != loopData) {
            mLoopView.refreshData(loopData);
            mLoopView.startAutoLoop();
        }

    }


    @Override
    protected IUI getUI() {
        return HomeFragment.this;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public String getPageName() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_car:
                Intent intent = new Intent(getContext(), AddCarActivity.class);
                startActivity(intent);
                break;
            case R.id.stop_history:
                Intent history = new Intent(getContext(), HistoryActivity.class);
                startActivity(history);

                break;
            case R.id.qr:
                //扫描二维码
                Intent qr = new Intent(getContext(),
                        CaptureActivity.class);
                startActivityForResult(qr, REQUEST_CODE_SCAN);
                break;
            case R.id.defy_query:
                Intent query = new Intent(getContext(), ViolationQuery.class);
                startActivity(query);

                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                ToastUtil.showStringToast(content);

            }
        }
    }

    @Override
    public void imageSuccess(List<String> datas) {
        if (datas!=null) {
            adapter.add(datas);
        }


    }
}
