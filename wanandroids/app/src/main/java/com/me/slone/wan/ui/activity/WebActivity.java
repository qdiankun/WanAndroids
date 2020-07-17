package com.me.slone.wan.ui.activity;

import android.content.Intent;
import android.text.TextUtils;

import com.me.slone.wan.R;
import com.me.slone.wan.common.Constants;
import com.me.slone.wan.base.MyActivity;
import com.me.slone.wan.view.ProgressWebview;

import butterknife.BindView;

public class WebActivity extends MyActivity {

    @BindView(R.id.web_x5_view)
    ProgressWebview mWebview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra(Constants.ARG_URL);
            if (TextUtils.isEmpty(url)) {
                url = "https://www.baidu.com";
            }
            mWebview.getSettings().setJavaScriptEnabled(true);
            mWebview.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            finish();
        }
    }
}