package com.cheyifu.icar.tabMy.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.tabMy.iview.IWebView;
import com.cheyifu.icar.tabMy.presenter.WebPresenter;
import com.cheyifu.icar.utils.AndroidUtils;

/**
 * Created by Administrator on 2017/3/2.
 */
public class WebSiteActivity extends BaseMVPActivity<WebPresenter> implements IWebView {
    private String browserUrl = "http://www.cheyifu2016.com";
    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_website);
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("官方网站");
        RelativeLayout back = (RelativeLayout) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtils.hideSoftInput(WebSiteActivity.this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
            }
        });
        WebView sitek = (WebView) findViewById(R.id.web);
        sitek.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = sitek.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        sitek.requestFocus();
        sitek.loadUrl(browserUrl);

    }

    @Override
    protected WebPresenter createPresenter() {
        return new WebPresenter();
    }

    @Override
    protected IUI getUI() {
        return WebSiteActivity.this;
    }
}
