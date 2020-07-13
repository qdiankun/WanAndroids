package com.me.slone.wan.network.observer;

import com.me.slone.wan.network.exception.ApiException;
import com.me.slone.wan.utils.KLog;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author：diankun
 * Time：20-7-13 下午4:48
 * Description:
 */
public abstract class SilenceSubscriber<T> implements Observer<T> {

    private final static String TAG = SilenceSubscriber.class.getSimpleName();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    public abstract void success(T t) ;

    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof SocketTimeoutException) {
                KLog.i(TAG, "SocketTimeoutException 网络中断，请检查您的网络状态>" + e.getMessage());
                onHandledNetError(e);
                e.printStackTrace();
            } else if (e instanceof SocketException) {
                KLog.i(TAG, "SocketException 网络中断，请检查您的网络状态>" + e.getMessage());
                onHandledNetError(e);
                e.printStackTrace();
            } else if (e instanceof ApiException) {
                int errCode = ((ApiException) e).getCode();
                KLog.i(TAG, "错误码为》" + errCode);
                onHandledError((ApiException) e);
            } else {
                e.printStackTrace();
                onHandledNetError(e);
                KLog.i(TAG, "网络请求发生了没有处理异常 网络中断，请检查您的网络状态>" + e.getMessage());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    //要处理特殊的错误码，重写这个函数，需要展示toast的调用super，不需要就不调用--wb
    public void onHandledError(ApiException apiE) {
        KLog.i(TAG, "父类onHandledError调用》" + +apiE.getCode() + " " + apiE.getDisplayMessage());
    }

    public void onHandledNetError(Throwable throwable) {
        KLog.i(TAG, "onHandledNetError》" + (throwable == null ? "null" : throwable.getMessage()));
    }

    @Override
    public void onComplete() {

    }
}
