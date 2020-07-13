package com.me.slone.wan.app;

import android.app.Application;

import com.me.slone.wan.BuildConfig;
import com.me.slone.wan.network.NetworkManager;
import com.me.slone.wan.utils.KLog;

/**
 * Author：diankun
 * Time：20-7-13 下午4:10
 * Description: wanandroid
 */
public class WanApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initLog();
        initNetwork();
    }

    private void initLog() {
        KLog.init(BuildConfig.LOG_DEBUG);
    }

    private void initNetwork() {
        NetworkManager.getInstance().init();
    }
}
