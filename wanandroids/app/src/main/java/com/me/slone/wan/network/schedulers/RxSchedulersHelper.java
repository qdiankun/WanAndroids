package com.me.slone.wan.network.schedulers;

import androidx.annotation.NonNull;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：diankun
 * Time：20-7-13 下午3:53
 * Description: 线程切换
 */
public class RxSchedulersHelper {

    @NonNull
    public static  <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
