package com.cheyifu.icar.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.utils.AndroidUtils;
import com.cheyifu.icar.R;

public abstract  class layoutBaseActivity extends Activity {
    private TextView tv_titlebar;
    private RelativeLayout back;
//    private ImageView image_title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initLayout();
        initView();
        initData();

    }
    protected abstract void initLayout();
    protected abstract void initView();
    protected abstract void initData();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        getLayoutInflater().inflate(layoutResID, (ViewGroup) findViewById(R.id.layout_base_content_container));
        tv_titlebar = (TextView) findViewById(R.id.tv_titlebar);
        back = (RelativeLayout) findViewById(R.id.iv_back);
//        image_title_bar = (ImageView) findViewById(R.id.image_title_bar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTitleBackPressed();
            }
        });

        setTitle(getTitle());
    }

    protected void onTitleBackPressed() {
        AndroidUtils.hideSoftInput(this, null);
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        tv_titlebar.setText(title);
    }

//    protected void visibilityImage(boolean isIamge) {
//        if (isIamge) {
//            image_title_bar.setVisibility(View.VISIBLE);
////            image_title_bar.setImageBitmap(bitmap);
//        } else {
//            image_title_bar.setVisibility(View.GONE);
//        }
//
//    }
}
