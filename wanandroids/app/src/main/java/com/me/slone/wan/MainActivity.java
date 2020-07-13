package com.me.slone.wan;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.me.slone.wan.bean.Banner;
import com.me.slone.wan.network.NetworkManager;
import com.me.slone.wan.network.observer.SilenceSubscriber;
import com.me.slone.wan.network.response.ResponseTransformer;
import com.me.slone.wan.network.schedulers.RxSchedulersHelper;
import com.me.slone.wan.utils.KLog;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_baner})
    public void bannerClick(View view) {
        NetworkManager.getInstance()
                .getRequest()
                .getBanner()
                .compose(ResponseTransformer.handleResult())
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new SilenceSubscriber<List<Banner>>() {
                    @Override
                    public void success(List<Banner> banners) {
                        KLog.i(banners);
                    }
                });

    }
}