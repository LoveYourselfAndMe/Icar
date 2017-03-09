package com.cheyifu.icar.tabHome.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabHome.iView.IqureyView;
import com.cheyifu.icar.tabHome.persenter.QuryPrensenter;
import com.cheyifu.icar.utils.SPUtils;

public class QueryActivity extends BaseMVPActivity<QuryPrensenter>implements IqureyView {


    private WebView webView;
    private RelativeLayout back;
    private String plate;
    private String key;

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_query);
        plate = getIntent().getStringExtra("queryCar");
        key = SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, "");
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("违章查询");
        back = (RelativeLayout) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        webView = (WebView) findViewById(R.id.query_web);
        WebSettings webSettings = webView.getSettings();

        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webView.requestFocus();
//        sitek.loadUrl("http://www.trzd.com/phone/index.html");
        webView.loadUrl("http://192.168.1.99:8889/app/illegalQuery?token="+key+"&plate="+plate);


    }

    @Override
    protected QuryPrensenter createPresenter() {
        return new QuryPrensenter();
    }

    @Override
    protected IUI getUI() {
        return QueryActivity.this;
    }
}
