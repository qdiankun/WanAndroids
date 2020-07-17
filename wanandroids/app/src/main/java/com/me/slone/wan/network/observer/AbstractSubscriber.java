package com.me.slone.wan.network.observer;

import android.content.Context;

import com.me.slone.wan.network.exception.ApiException;
import com.me.slone.wan.network.progress.ProgressCancelListener;
import com.me.slone.wan.network.progress.ProgressDialogHandler;
import com.me.slone.wan.utils.KLog;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author：diankun
 * Time：20-7-17 上午9:42
 * Description:
 */
public abstract class AbstractSubscriber<T>  implements Observer<T> , ProgressCancelListener {

    private static final String TAG = AbstractSubscriber.class.getSimpleName();
    protected Context context;
    protected ProgressDialogHandler mProgressDialogHandler;
    protected boolean showDialog;
    protected Disposable disposable;
    /**
     * @param context
     * @param showDialog
     * @param cancelable
     */
    public AbstractSubscriber(Context context, boolean showDialog, boolean cancelable) {
        this.context = context;
        this.showDialog = showDialog;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, cancelable);
    }

    /**
     * @param context
     * @param showDialog
     */
    public AbstractSubscriber(Context context, boolean showDialog) {
        this(context, showDialog, true);
    }


    private void showProgressDialog() {
        if (mProgressDialogHandler != null && showDialog) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    protected void dismissProgressDialog() {
        if (mProgressDialogHandler != null && showDialog) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }


    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    public abstract void success(T t) ;

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        dismissProgressDialog();
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


}