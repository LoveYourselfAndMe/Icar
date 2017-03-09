package com.cheyifu.icar.login;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.tabMy.iview.AboutView;
import com.cheyifu.icar.tabMy.presenter.AboutPresenter;
import com.cheyifu.icar.utils.AndroidUtils;

public class TosActivity extends BaseMVPActivity<AboutPresenter> implements AboutView {
    private String browserUrl = "http://www.cheyifu2016.com/yitingbao_tiaokuan.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about_us);
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("服务条款");
        RelativeLayout back = (RelativeLayout) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtils.hideSoftInput(TosActivity.this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
            }
        });
        WebView sitek = (WebView) findViewById(R.id.sitek);
        WebSettings webSettings = sitek.getSettings();

        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        sitek.requestFocus();
//        sitek.loadUrl("http://www.trzd.com/phone/index.html");
        sitek.loadUrl(browserUrl);

    }

    @Override
    protected AboutPresenter createPresenter() {
        return new AboutPresenter();
    }

    @Override
    protected IUI getUI() {
        return TosActivity.this;
    }
}
