package com.me.slone.wan.app;

import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.hjq.bar.TitleBar;
import com.hjq.bar.style.TitleBarLightStyle;
import com.hjq.toast.ToastInterceptor;
import com.hjq.toast.ToastUtils;
import com.me.slone.wan.BuildConfig;
import com.me.slone.wan.R;
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
        initSdk();
    }

    private void initSdk() {
        // 吐司工具类
        ToastUtils.init(this);
        // 设置 Toast 拦截器
        ToastUtils.setToastInterceptor(new ToastInterceptor() {
            @Override
            public boolean intercept(Toast toast, CharSequence text) {
                boolean intercept = super.intercept(toast, text);
                if (intercept) {
                    Log.e("Toast", "空 Toast");
                } else {
                    Log.i("Toast", text.toString());
                }
                return intercept;
            }
        });



        // 标题栏全局样式
        TitleBar.initStyle(new TitleBarLightStyle(this) {

            @Override
            public Drawable getBackground() {
                return new ColorDrawable(getColor(R.color.colorPrimary));
            }

            @Override
            public Drawable getBackIcon() {
                return getDrawable(R.mipmap.ic_back_black);
            }
        });
    }

    private void initLog() {
        KLog.init(BuildConfig.LOG_DEBUG);
    }

    private void initNetwork() {
        NetworkManager.getInstance().init();
    }
}
