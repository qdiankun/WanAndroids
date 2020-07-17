package com.me.slone.wan.network.observer;

import android.content.Context;

/**
 * Author：diankun
 * Time：20-7-17 上午10:08
 * Description:
 */
public abstract class ProgressSubscriber<T> extends AbstractSubscriber<T> {

    public ProgressSubscriber(Context context, boolean showDialog, boolean cancelable) {
        super(context, showDialog, cancelable);
    }

    public ProgressSubscriber(Context context, boolean showDialog) {
        super(context, showDialog);
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (this.disposable!=null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
